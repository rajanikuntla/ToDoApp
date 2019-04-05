package com.test.todoApp.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="user_name", nullable=false, unique=true)
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@JsonManagedReference
	@OneToMany(targetEntity= ToDo.class, mappedBy="user", cascade = CascadeType.PERSIST)
	private List<ToDo> toDo;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<ToDo> getToDo() {
		return toDo;
	}
	public void setToDo(List<ToDo> toDo) {
		this.toDo = toDo;
	}	

}
