package com.hand.report.aop.aspect;


/**
 * @author Fang WeiPing
 * @date 2020/6/1 10:39
 */
import com.hand.report.vo.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Autho fangweiping
 * @Date: 2020/6/1 10:39
 */
@Aspect
@Component
public class AspectDemo {


    @Pointcut("execution(public * com.hand.report.controller.AspectController..*(..))")
    public void pointcut() {

    }

    /**
     * 前置
     */
    @Before("pointcut()")
    public void before() {
        System.out.println("before");
    }

    /**
     * 后置
     * @param point
     * @param res
     */
    @AfterReturning(pointcut = "pointcut()", returning = "res")
    public void afterReturning(JoinPoint point,Object res) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        User user = (User) res;
        System.out.println("user = " + user);
    }

    /**
     * 异常
     * @param point
     */
    @AfterThrowing("pointcut()")
    public void AfterThrowing(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        System.out.println("throw");
    }


    /**
     * 最终
     */
    @After("pointcut()")
    public void after() {
        System.out.println("after");
    }

    public void demo() {
/*        try{
            try{
                doBefore();//对应@Before注解的方法切面逻辑
                method.invoke();
            }finally{
                doAfter();//对应@After注解的方法切面逻辑
            }
            doAfterReturning();//对应@AfterReturning注解的方法切面逻辑
        }catch(Exception e){
            doAfterThrowing();//对应@AfterThrowing注解的方法切面逻辑
        }*/
    }
}
