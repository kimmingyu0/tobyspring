package com.intheeast.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Upperable implements InvocationHandler {
    Hello target;

    public Upperable(Hello target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String ret = (String)method.invoke(target, args);
        return ret.toUpperCase();

    }
}
