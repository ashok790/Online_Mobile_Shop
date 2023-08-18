package com.cdac.service;

import com.cdac.entity.User;

public interface UserService {
	public User saveUser(User user);
	public User validateUser(User user);
}
