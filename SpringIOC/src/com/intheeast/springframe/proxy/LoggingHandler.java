package com.intheeast.springframe.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class LoggingHandler implements InvocationHandler {
    private Object target;
    private String[] pattern;

    public void setTarget(Object target) {
        this.target = target;
    }

    public void setPattern(String[] pattern) {
        this.pattern = pattern;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Arrays.asList(pattern).contains(method.getName())){
            return invokeInLogging(method, args);
        } else {
            return method.invoke(target, args);
        }

    }

    private Object invokeInLogging(Method method, Object[] args) throws Throwable {
        Date nowDate = new Date();
        SimpleDateFormat timestamp= new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");

        System.out.println(timestamp.format(nowDate)+" "+target.getClass().getSimpleName()+"의 "+method.getName()+" 메서드 실행됨");
        Object ret = method.invoke(target, args);
        return ret;
    }
}
