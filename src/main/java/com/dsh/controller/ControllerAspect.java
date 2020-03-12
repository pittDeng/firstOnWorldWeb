package com.dsh.controller;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {
    @Pointcut("execution(* com.dsh.controller.*.*(..))")
    public void pointCut1(){}
    @Before(value = "pointCut1()")
    public void beforeEnterTheHome(){

    }
    @After(value = "pointCut1()")
    public void afterEnterTheHome(){
//        System.out.println("after the method");
    }
}
