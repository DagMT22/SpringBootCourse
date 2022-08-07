package com.promineotech.contact.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.contact.entity.Individual;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Individuals")
public interface IndividualController {

  	@Operation(summary = "Create an Individual")
	@PostMapping(value = "/individual")
	@ResponseStatus(code = HttpStatus.CREATED)
	Individual createIndividual(@RequestBody Individual individual);
	
}

//Delete

//@Validated
//@RequestMapping("/addIndividual")
//public interface IndividualController {
//
//  @Operation(
//      summary = "Add an Individual to the database",
//	  description = "Returns the Individual",
//	  responses = {
//	      @ApiResponse(
//	          responseCode = "201", 
//	          description = "The created Individual is returned", 
//	          content = @Content(
//	              mediaType = "application/json", 
//	              schema = @Schema(implementation = Individual.class))),
//	      @ApiResponse(
//	          responseCode = "400", 
//	          description = "The request parameters are invalid", 
//	          content = @Content(mediaType = "application/json")),
//	      @ApiResponse(
//	          responseCode = "404", 
//	          description = "An Individual was not found with the input criteria", 
//	          content = @Content(mediaType = "application/json")),
//	      @ApiResponse(
//	          responseCode = "500", 
//	          description = "An unplanned error occurred", 
//	          content = @Content(mediaType = "application/json"))
//  },
//  parameters = {
//      @Parameter(
//          name = "Individual", 
//          required = true, 
//          description = "The Individual as JSON")
//      }
//  )
//  
//	@PostMapping
//	@ResponseStatus(code = HttpStatus.CREATED)
//	Individual createIndividual(@RequestBody Individual individual);
//	
//}