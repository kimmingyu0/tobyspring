package com.intheeast.springframe.service;

import com.intheeast.springframe.domain.User;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class UserServiceTx implements UserService{
    UserService userService;
    PlatformTransactionManager transactionManager;

    public void setUserService (UserService userService) {
        this.userService = userService;
    }

    public void setTransactionManager (PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public void upgradeLevels() {
        userService.upgradeLevels();
    }

    @Override
    public void add(User user) {
        userService.add(user);
    }

    public void transaction (){
        TransactionStatus status =
                this.transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            upgradeLevels();
            this.transactionManager.commit(status);
        } catch (RuntimeException e) {
            this.transactionManager.rollback(status);
            throw e;
        }
    }

}
