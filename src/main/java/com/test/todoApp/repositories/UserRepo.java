package com.test.todoApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.todoApp.domain.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	
	public User findByUserName(String userName);
	
	
	public User findByUserId(Long userId);

}
