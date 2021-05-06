package com.fwp.demo.aop.aspect;

import com.fwp.demo.aop.annotation.Login;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginAspect {
    /**
     * 切点表达式
     */
/*    @Pointcut("@annotation(com.fwp.demo.aop.annotation.Login)")
    public void loginPoincut() {

    }*/

    /**
     * 环绕通知
     *
     * 计算登陆时间
     */
    @Around("@annotation(login)")
    public Object loginAround(ProceedingJoinPoint joinPoint, Login login) throws Throwable {
        try {
            long cur = System.currentTimeMillis();
            Object obj = null;
            obj = joinPoint.proceed();
            System.out.println("登陆耗时:"+(System.currentTimeMillis()-cur));
            return obj;
        } catch (Exception e) {
            System.out.println("捕获异常");
        }

      return null;
    }
}
