package com.test.todoApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.todoApp.domain.User;
import com.test.todoApp.repositories.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	public boolean validateUser(User user) {
		User tempUser = userRepo.findByUserName(user.getUserName());
		if(tempUser.getPassword().equals(user.getPassword()))
			return true;
		return false;
	}

	public boolean userNameAlreadyExists(User user) {
		User tempUser = userRepo.findByUserName(user.getUserName());
		if(tempUser != null) {
			return true;
		}
		return false;
	}

	public void addUser(User user) {
		//userRepo.save(user);		
	}

	public User getUser(String userName) {
		return userRepo.findByUserName(userName);
	}


}
