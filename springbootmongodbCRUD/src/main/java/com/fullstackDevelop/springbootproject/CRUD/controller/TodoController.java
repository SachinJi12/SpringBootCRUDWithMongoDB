package com.fullstackDevelop.springbootproject.CRUD.controller;

//import java.util.Date;
import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fullstackDevelop.springbootproject.CRUD.exception.TodoCollectionException;
import com.fullstackDevelop.springbootproject.CRUD.model.TodoDto;
//import com.fullstackDevelop.springbootproject.CRUD.repository.TodoRepository;
import com.fullstackDevelop.springbootproject.CRUD.service.TodoService;

import jakarta.validation.ConstraintViolationException;

@RestController
public class TodoController {

	/*
	 * commenting this repos as now all methods will come from service
	 * @Autowired private TodoRepository todoRepos;
	 */

	@Autowired
	private TodoService todoService;

	// to retrieve all data from DB by adding service implementation method
	@GetMapping("/todos")
	public ResponseEntity<?> getAllTodo() {
		// now we will call a service method to get all todos
		List<TodoDto> todos = todoService.getAllTodos();
		return new ResponseEntity<>(todos, todos.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
		/*
		 * List<TodoDto> todos = todoRepos.findAll(); if(todos.size() > 0) { return new
		 * ResponseEntity<List<TodoDto>>(todos, HttpStatus.OK); }else { return new
		 * ResponseEntity<>("No todos Available", HttpStatus.NOT_FOUND); }
		 */

	}

	@PostMapping("/todos")
	public ResponseEntity<?> createTodo(@RequestBody TodoDto todo) {
		try {
			// now we will call a service method to add todo
			todoService.createTodo(todo);
			return new ResponseEntity<TodoDto>(todo, HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (TodoCollectionException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
		}
		/*
		 * //todo.setCreatedAt(new Date(System.currentTimeMillis()));
		 * todoRepos.save(todo);
		 */

	}

	// to get the single to by id
	@GetMapping("/todos/{id}")
	public ResponseEntity<?> getSingletodo(@PathVariable("id") String id) {
		// now we will call a service method to get the todo by id
		try {
			return new ResponseEntity<>(todoService.getSingleTodo(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		/*
		 * Optional<TodoDto> todoOptional = todoRepos.findById(id); if
		 * (todoOptional.isPresent()) { return new ResponseEntity<>(todoOptional.get(),
		 * HttpStatus.OK); } else { return new ResponseEntity<>("Todo not found an Id",
		 * HttpStatus.NOT_FOUND); }
		 */

	}

	@PutMapping("/todos/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody TodoDto todo) {
		// now we will call a service method to update the todo by id 
		try {
			todoService.updateById(id, todo);
			return new ResponseEntity<>("Updated the Item with:-"+""+ id, HttpStatus.OK);
			}
		catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		/*
		 * Optional<TodoDto> todoOptional = todoRepos.findById(id); if
		 * (todoOptional.isPresent()) { TodoDto todoToSave = todoOptional.get(); // set
		 * all fields todoToSave.setCompeletd(todo.getCompeletd() != null ?
		 * todo.getCompeletd() : todoToSave.getCompeletd());
		 * todoToSave.setTodo(todo.getTodo() != null ? todo.getTodo() :
		 * todoToSave.getTodo()); todoToSave.setDescritpion( todo.getDescritpion() !=
		 * null ? todo.getDescritpion() : todoToSave.getDescritpion());
		 * todoToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
		 * todoRepos.save(todoToSave);
		 * 
		 * return new ResponseEntity<>(todoToSave, HttpStatus.OK); } else { return new
		 * ResponseEntity<>("Todo not found an Id", HttpStatus.NOT_FOUND); }
		 */

	}

	@DeleteMapping("/todos/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
		try {
			todoService.deleteById(id);
			return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
		} catch (TodoCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
