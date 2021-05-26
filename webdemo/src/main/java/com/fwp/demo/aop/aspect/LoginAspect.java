package com.fwp.demo.aop.aspect;

import com.fwp.demo.aop.annotation.Login;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@EnableAspectJAutoProxy(exposeProxy = true)
public class LoginAspect {

    /**
     * 环绕通知
     *
     * 计算登陆时间
     */
    @Around(value = "@annotation(com.fwp.demo.aop.annotation.Login)")
    public Object loginAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long cur = System.currentTimeMillis();
        Object obj = null;
        obj = joinPoint.proceed();
        System.out.println("登陆耗时:"+(System.currentTimeMillis()-cur));
        return obj;
    }


    /**
     * 后置通知
     * @param point
     * @param login
     * @param res
     */
    @AfterReturning(value = "@annotation(login)",returning = "res")
    public void afterReturning(JoinPoint point, Login login, Object res) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        System.out.println("method = " + method);
        System.out.println("res = " + res);
    }

}
