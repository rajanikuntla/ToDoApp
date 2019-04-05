package com.test.todoApp.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.todoApp.domain.ToDo;
import com.test.todoApp.domain.User;
import com.test.todoApp.domain.WorkItem;
import com.test.todoApp.repositories.ToDoRepo;
import com.test.todoApp.repositories.UserRepo;

@Service
public class ToDoService {

	@Autowired
	private ToDoRepo toDoRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	
	public List<ToDo> findAllByUserId(Long userId){	
		User user = userRepo.findByUserId(userId);
		List<ToDo> todos = toDoRepo.findAllByUser(user);
		return todos;		
	}

	public void addTodo(ToDo todo, Long userId) {
		todo.setUser(userRepo.getOne(userId));
		toDoRepo.save(todo);
	}

	public void deleteToDo(Long id) {
		toDoRepo.deleteToDo(true, id);	
	}
	
	public ToDo getToDo(Long todoId) {
		return toDoRepo.getOne(todoId);
	}

	public void editToDos(ToDo todo) {
		
		
		ToDo temp = toDoRepo.getOne(todo.getId());
		temp.setName(todo.getName());
		
		List<WorkItem> tmp = todo.getWorkItems().subList(temp.getWorkItems().size(), todo.getWorkItems().size());
		
		
		temp.updateWorkItems(tmp);
		toDoRepo.save(temp);
		
	}
	
	
}
