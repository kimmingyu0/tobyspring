package com.kimmingyu0.springframe.dao;

public class DuplicateUserIdExceotion extends RuntimeException {
    public DuplicateUserIdExceotion (Throwable cause){
        super(cause);
    }
}
