package com.promineotech.contact.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.contact.entity.Contact;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(info = @Info(title = "Contact Tracing - Contacts"))
@Tag(name="Contacts")
public interface ContactController {

	  @Operation(summary = "Returns a Contact")
//	  @RequestMapping(value="/contacts/{id}", method=RequestMethod.GET)  GetMapping vs RequestMapping with method - delete when finished
	  @GetMapping(value="/contacts/{id}")
	  @ResponseStatus(code = HttpStatus.OK)	
	  List<Contact>	fetchContact(@PathVariable int id);

	  @Operation(summary = "Create a Contact")
	  @PostMapping(value="/contacts")
	  @ResponseStatus(code = HttpStatus.CREATED)
	  Contact CreateContact(@RequestBody Contact contact);
	
}
