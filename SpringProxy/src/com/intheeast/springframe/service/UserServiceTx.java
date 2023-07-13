package com.intheeast.springframe.service;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.intheeast.springframe.domain.User;


public class UserServiceTx implements UserService {

	// 타겟 오브젝트
	UserService userService;
	PlatformTransactionManager transactionManager;

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 메소드 구현과 위임
	public void add(User user) {
		this.userService.add(user);
	}

	//메소드 구현
	public void upgradeLevels() {
		// 부가기능 수행
		TransactionStatus status = this.transactionManager
				.getTransaction(new DefaultTransactionDefinition());
		try {
			// 핵심 비즈니스 로직 위임
			userService.upgradeLevels();

			// 부가기능 수행
			this.transactionManager.commit(status);
		} catch (RuntimeException e) {
			this.transactionManager.rollback(status);
			throw e;
		}
	}

}
