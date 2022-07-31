package com.promineotech.contact.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.contact.entity.Case;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Validated
@RequestMapping("/addCase")
public interface CaseController {

	  @Operation(
		      summary = "Add a Case to the database",
			  description = "Returns the Case",
			  responses = {
			      @ApiResponse(
			          responseCode = "201", 
			          description = "The created Case is returned", 
			          content = @Content(
			              mediaType = "application/json", 
			              schema = @Schema(implementation = Case.class))),
			      @ApiResponse(
			          responseCode = "400", 
			          description = "The request parameters are invalid", 
			          content = @Content(mediaType = "application/json")),
			      @ApiResponse(
			          responseCode = "404", 
			          description = "A Case was not found with the input criteria", 
			          content = @Content(mediaType = "application/json")),
			      @ApiResponse(
			          responseCode = "500", 
			          description = "An unplanned error occurred", 
			          content = @Content(mediaType = "application/json"))
		  },
		  parameters = {
		      @Parameter(
		          name = "Case", 
		          required = true, 
		          description = "The Case as JSON")
		      }
		  )	
	  
	  @PostMapping
	  @ResponseStatus(code = HttpStatus.CREATED)
	  Case createCase(@RequestBody Case inputCase);
	
	
	
	
}
