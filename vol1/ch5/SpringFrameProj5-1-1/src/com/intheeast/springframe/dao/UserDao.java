package com.intheeast.springframe.dao;

import java.util.List;
import java.util.Optional;

import com.intheeast.springframe.domain.User;

public interface UserDao {
	void add(User user);

	Optional<User> get(String id);

	List<User> getAll();

	void deleteAll();

	int getCount();
}
