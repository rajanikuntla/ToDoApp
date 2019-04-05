package com.test.todoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.todoApp.domain.ToDo;
import com.test.todoApp.services.ToDoService;

@RestController
public class ToDoController {
	
	@Autowired
	private ToDoService toDoService;
	
	@RequestMapping(method= RequestMethod.GET, value="/todoList/{userId}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ToDo> listToDos(@PathVariable Long userId){
		return toDoService.findAllByUserId(userId);
	}
	
	@RequestMapping(method= RequestMethod.POST, value="/addToDo/{userId}")
	public ResponseEntity<String> addToDo(@RequestBody ToDo todo, @PathVariable Long userId){
		toDoService.addTodo(todo, userId);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(method= RequestMethod.POST, value="/deleteToDo/{id}")
	public ResponseEntity<String> deleteToDo(@PathVariable Long id){
		toDoService.deleteToDo(id);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(method= RequestMethod.PATCH, value="/editToDo")
	public ResponseEntity<String> editTodo(@RequestBody ToDo todo){
		toDoService.editToDos(todo);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(method= RequestMethod.GET, value="/editToDo/{id}")
	public ToDo getToDoToEdit(@PathVariable Long id){
		return toDoService.getToDo(id);
	}

}
