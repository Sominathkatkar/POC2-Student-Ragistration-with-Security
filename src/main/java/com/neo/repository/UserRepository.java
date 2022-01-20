package com.neo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByusername(String username);
}
