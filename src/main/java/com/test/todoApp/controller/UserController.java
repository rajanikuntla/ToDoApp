package com.test.todoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.todoApp.domain.User;
import com.test.todoApp.services.UserService;



@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public Long login(@RequestBody User user) {
		if(userService.validateUser(user)) {
			user = userService.getUser(user.getUserName());
			return user.getUserId();
		}	
		return null;		
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody User user) {
		if(userService.userNameAlreadyExists(user)) {
			return  new ResponseEntity<String>(HttpStatus.IM_USED);
		} else {
			userService.addUser(user);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		}
	}


}
