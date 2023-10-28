package com.fullstackDevelop.springbootproject.CRUD.service;

import java.util.List;

import com.fullstackDevelop.springbootproject.CRUD.exception.TodoCollectionException;
import com.fullstackDevelop.springbootproject.CRUD.model.TodoDto;

import jakarta.validation.ConstraintViolationException;

public interface TodoService {
	
	public void createTodo(TodoDto todo) throws ConstraintViolationException,TodoCollectionException;
	
	// to retrieve the data from DB
	public List <TodoDto> getAllTodos();
	
	// to get the single to by id
	public TodoDto getSingleTodo(String id) throws TodoCollectionException;
	
	//to udpadte the todos by id
	public void updateById(String id, TodoDto todo)throws TodoCollectionException;
	
	//to delete the todos by id
	public void deleteById(String id) throws TodoCollectionException;
}
