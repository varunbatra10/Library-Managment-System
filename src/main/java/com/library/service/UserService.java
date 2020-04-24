package com.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.model.User;
import com.library.repository.UserRepository;

@Transactional
@Service(value = "userService")
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> save(List<User> users) {
		for(User user:users) {
			userRepository.save(user);
		}
		return users;
	}

	public String delete() {
		List<User> users = userRepository.findAll();
		for(User user:users) {
			userRepository.delete(user);
		}
		return "Users deleted";
	}


}
