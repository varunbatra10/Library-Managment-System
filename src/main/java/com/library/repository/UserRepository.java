package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.User;


public interface UserRepository extends JpaRepository<User, Integer>{

	User findByName(String name);
}
