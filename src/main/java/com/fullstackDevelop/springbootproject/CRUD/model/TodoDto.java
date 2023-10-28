package com.fullstackDevelop.springbootproject.CRUD.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="todos")
public class TodoDto {
	
	@Id
	private String id;
	
	@NotNull(message="Todo cannot be null")
	private String todo;
	
	@NotNull(message="Descritpion cannot be null")
	private String descritpion;
	
	@NotNull(message="Compeletd cannot be null")
	private Boolean compeletd;
	
	private Date createdAt;
	
	private Date updatedAt;
	

}
