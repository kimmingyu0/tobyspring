package com.intheeast.proxy;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

public class main {
    @Test
    public void simpleProxy(){
        Hello hello = new HelloTarget();
        Hello neww = (Hello) Proxy.newProxyInstance(
            getClass().getClassLoader(),
                new Class[] {Hello.class},
                new Upperable(hello)
        );

        System.out.println(neww.sayHello("mingyu"));
        System.out.println(neww.sayHi("mingyu"));
        System.out.println(neww.sayThankYou("mingyu"));
    }
}
