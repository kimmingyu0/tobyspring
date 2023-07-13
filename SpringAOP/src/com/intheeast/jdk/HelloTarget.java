package com.intheeast.jdk;

public class HelloTarget implements Hello{
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @Override
    public String sayHi(String name) {
        return "Hi " + name;
    }

    @Override
    public String satThankYou(String name) {
        return "Thank You " + name;
    }
}
