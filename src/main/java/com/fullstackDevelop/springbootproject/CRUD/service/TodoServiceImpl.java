package com.fullstackDevelop.springbootproject.CRUD.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstackDevelop.springbootproject.CRUD.exception.TodoCollectionException;
import com.fullstackDevelop.springbootproject.CRUD.model.TodoDto;
import com.fullstackDevelop.springbootproject.CRUD.repository.TodoRepository;

import jakarta.validation.ConstraintViolationException;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepos;

	// added an exception if todo is already exsit
	@Override
	public void createTodo(TodoDto todo) throws ConstraintViolationException, TodoCollectionException {
		Optional<TodoDto> todoOptional = todoRepos.findByTodo(todo.getTodo());
		if (todoOptional.isPresent()) {
			throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
		} else {
			todo.setCreatedAt(new Date(System.currentTimeMillis()));
			todoRepos.save(todo);
		}

	}

	// to retrieve all data from DB
	@Override
	public List<TodoDto> getAllTodos() {

		List<TodoDto> todos = todoRepos.findAll();
		if (todos.size() > 0) {
			return todos;
		} else {
			return new ArrayList<TodoDto>();
		}
	}

	// to get the single to by id
	@Override
	public TodoDto getSingleTodo(String id) throws TodoCollectionException {

		Optional<TodoDto> todoOptional = todoRepos.findById(id);
		if (!todoOptional.isPresent()) {
			throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
		} else {
			return todoOptional.get();
		}

	}

	//to udpadte the todos by id and also check for if duplicate addition while update
	@Override
	public void updateById(String id, TodoDto todo) throws TodoCollectionException {
		Optional<TodoDto> todoOptional = todoRepos.findById(id);
		// check if updating is duplicate
		Optional<TodoDto> todoOptionalDuplicate = todoRepos.findByTodo(todo.getTodo());
		if (todoOptional.isPresent()) {
			if (todoOptionalDuplicate.isPresent() && !todoOptionalDuplicate.get().getId().equals(id)) {
				throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());				
			}			
			TodoDto todoToSave = todoOptional.get();
			// set all fields
			todoToSave.setCompeletd(todo.getCompeletd());
			todoToSave.setTodo(todo.getTodo());
			todoToSave.setDescritpion(todo.getDescritpion());
			todoToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
			todoRepos.save(todoToSave);
		}else {
			throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
		}
		
	}

	//to delete the todos by id
	@Override
	public void deleteById(String id) throws TodoCollectionException {
			Optional<TodoDto> todoOptionalDel = todoRepos.findById(id);
			if(!todoOptionalDel.isPresent()) {
				
				throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
			}else {
				todoRepos.deleteById(id);
			}
	}
}
