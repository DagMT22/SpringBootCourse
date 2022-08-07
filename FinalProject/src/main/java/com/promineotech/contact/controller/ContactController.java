package com.promineotech.contact.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.contact.entity.Contact;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(info = @Info(title = "Contact Tracing"))
@Tag(name="Contacts")
public interface ContactController {

	  @Operation(summary = "Returns a Contact")
	  @GetMapping(value="/contact/{ContactId}")
	  @ResponseStatus(code = HttpStatus.OK)	
	  List<Contact>	readContact(@PathVariable int ContactId);

	  @Operation(summary = "Create a Contact")
	  @PostMapping(value="/contact")
	  @ResponseStatus(code = HttpStatus.CREATED)
	  Contact createContact(@RequestBody Contact contact);
	  
	  @Operation(summary = "Update a Contact")
	  @PutMapping(value = "/contact")
	  @ResponseStatus(code = HttpStatus.OK)
	  Contact updateContact(@RequestBody Contact contact);
	  
	  @Operation(summary = "Delete a Contact")
	  @DeleteMapping(value = "/contact/{ContactId}")
	  @ResponseStatus(code = HttpStatus.OK)
	  int deleteContact(@PathVariable int ContactId);
	  
	  
}
