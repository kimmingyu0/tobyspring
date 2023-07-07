package com.kimmingyu0.springframe.dao;

import java.util.List;
import java.util.Optional;

import com.kimmingyu0.springframe.domain.User;

public interface UserDao {
	void add(User user);

	Optional<User> get(String id);

	List<User> getAll();

	void deleteAll();

	int getCount();
}
