package com.intheeast.springframe.service;



import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.intheeast.springframe.dao.TestDaoFactory;
import com.intheeast.springframe.dao.UserDao;
import com.intheeast.springframe.domain.Level;
import com.intheeast.springframe.domain.User;



import static com.intheeast.springframe.service.UserService.MIN_LOGCOUNT_FOR_SILVER;
import static com.intheeast.springframe.service.UserService.MIN_RECCOMEND_FOR_GOLD;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestServiceFactory.class})
public class UserServiceTest {
	@Autowired 	UserService userService;	
	@Autowired UserDao userDao;
//	User user;

	List<User> users;	// test fixture

	/*
	* bean() ->
	* Autowired 한 Bean 객체가 잘 주입됐는지 확인 용도
	* */
	@Test
	public void bean(){
		assertNotEquals(this.userService, null);
	}

	@BeforeEach
	public void setUp() {	
//		user = new User();

		users = Arrays.asList(
				new User("bumjin", "박범진", "p1", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER-1, 0),
				new User("joytouch", "강명성", "p2", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0),
				new User("erwins", "신승한", "p3", Level.SILVER, 60, MIN_RECCOMEND_FOR_GOLD-1),
				new User("madnite1", "이상호", "p4", Level.SILVER, 60, MIN_RECCOMEND_FOR_GOLD),
				new User("green", "오민규", "p5", Level.GOLD, 100, Integer.MAX_VALUE)
				);
	}

//	@Test
//	public void upgradeLevel(){
//		Level[] levels = Level.values();
//		//=> Enum 열거체의 모든 상수를 저장한 배열을 생성하여 반환합니다.
//		for(Level level : levels){
//			if(level.nextLevel() == null) continue;
//			user.setLevel(level);
//			user.upgradeLevel();
//			assertEquals(user.getLevel(), level.nextLevel());
//		}
//	}

	@Test
	public void upgradeLevels() {
		userDao.deleteAll();
		for(User user : users) userDao.add(user);
		
		userService.upgradeLevels();
		
		checkLevelUpgraded(users.get(0), false);
		checkLevelUpgraded(users.get(1), true);
		checkLevelUpgraded(users.get(2), false);
		checkLevelUpgraded(users.get(3), true);
		checkLevelUpgraded(users.get(4), false);
	}
	
	private void checkLevelUpgraded(User user, boolean upgraded) {
		Optional<User> optionalUser = userDao.get(user.getId());
		if (!optionalUser.isEmpty()) {
			User userUpdate = optionalUser.get();
			if (upgraded) {
				assertEquals(userUpdate.getLevel(), user.getLevel().nextLevel());
			}
			else {
				assertEquals(userUpdate.getLevel(), (user.getLevel()));
			}			
		}		
	}
	
	@Test
	public void add() {
		userDao.deleteAll();
		
		User userWithLevel = users.get(4);	  // GOLD ����  
		User userWithoutLevel = users.get(0);  
		userWithoutLevel.setLevel(null);
		
		userService.add(userWithLevel);	  
		userService.add(userWithoutLevel);
		
		Optional<User> optionalUserWithLevelRead = userDao.get(userWithLevel.getId());
		if (!optionalUserWithLevelRead.isEmpty()) {
			User userWithLevelRead = optionalUserWithLevelRead.get();
			assertEquals(userWithLevelRead.getLevel(), userWithLevel.getLevel()); 
		}		
		
		Optional<User> optionalUserWithoutLevelRead = userDao.get(userWithoutLevel.getId());
		if (!optionalUserWithoutLevelRead.isEmpty()) {
			User userWithoutLevelRead = optionalUserWithoutLevelRead.get();
			assertEquals(userWithoutLevelRead.getLevel(), Level.BASIC);
		}		
	}
}
