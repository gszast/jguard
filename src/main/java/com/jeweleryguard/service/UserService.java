package com.jeweleryguard.service;

import com.jeweleryguard.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
