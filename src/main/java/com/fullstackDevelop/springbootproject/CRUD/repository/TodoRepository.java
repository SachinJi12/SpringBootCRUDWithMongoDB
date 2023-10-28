package com.fullstackDevelop.springbootproject.CRUD.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fullstackDevelop.springbootproject.CRUD.model.TodoDto;

@Repository
public interface TodoRepository extends MongoRepository<TodoDto, String> {
	
	// setting a query with index 0(that is 1st arg or method todo)
	@Query("{'todo': ?0}")
	Optional<TodoDto> findByTodo(String todo);

}
