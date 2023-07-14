package com.intheeast.learningtest.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;


public class classNamePointcutAdvisor {
    @Test
    public void classNamePointcutAdvisor(){
        NameMatchMethodPointcut classMethodPointcut = new NameMatchMethodPointcut(){
            public ClassFilter getClassFilter(){
                //boolean matches(Class<?> clazz);
                return clazz -> clazz.getSimpleName().startsWith("HelloT");
//                new ClassFilter() {
//                    @Override
//                    public boolean matches(Class<?> clazz) {
//                        return clazz.getSimpleName().startsWith("HelloT");
//                    }
//                };
            }
        };
        // method name pattern
        classMethodPointcut.setMappedName("sayH*");

        class HelloWorld extends  HelloTarget {};
        checkAdviced(new HelloWorld(), classMethodPointcut, false);

        class HelloToby extends HelloTarget {};
        checkAdviced(new HelloToby(), classMethodPointcut, true);
    }

    private void checkAdviced(Object target, Pointcut pointcut, boolean adviced) {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(target);
        pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));
        Hello proxiedHello = (Hello) pfBean.getObject();

        if(adviced) {
            Assertions.assertEquals(proxiedHello.sayHello("Toby"), "HELLO TOBY");
            Assertions.assertEquals(proxiedHello.sayHi("Toby"), "HI TOBY");
            Assertions.assertEquals(proxiedHello.sayThankYou("Toby"), "Thank You Toby");
        } else {
            Assertions.assertEquals(proxiedHello.sayHello("Toby"), "Hello Toby");
            Assertions.assertEquals(proxiedHello.sayHi("Toby"), "Hi Toby");
            Assertions.assertEquals(proxiedHello.sayThankYou("Toby"), "Thank You Toby");
        }
    }


}
