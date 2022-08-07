package com.promineotech.contact.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.contact.entity.Variant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Variants")
public interface VariantController {
	
	
	@Operation(summary = "Returns a Variant")
	@GetMapping (value = "/variant/{variantId}")
	@ResponseStatus(code = HttpStatus.OK)	
	List<Variant> readVariant(@PathVariable String variantId);

}

//DELETE
//@RequestMapping("/variant")
//public interface VariantController {
//	
//	@Operation(
//		summary = "Returns a variant",
//		description = "Given a variant id, returns a Variant with ID, Name, Disease Name and Infectious Time Range",
//		responses = {
//			@ApiResponse(
//				responseCode = "200",
//				description = "A Variant is returned",
//				content = @Content(
//					mediaType = "application/json",
//					schema = @Schema(implementation = Variant.class))),
//			@ApiResponse(
//				responseCode = "400",
//				description = "The request parameter is invalid",
//				content = @Content(mediaType = "application/json")),
//			@ApiResponse(
//				responseCode = "404",
//				description = "No Variant was found with that ID",
//				content = @Content(mediaType = "application/json")),
//			@ApiResponse(
//				responseCode = "500",
//				description = "An unplanned error occurred",
//				content = @Content(mediaType = "application/json"))
//		},
//		parameters = {
//			@Parameter(
//				name = "variantId",
//				allowEmptyValue = false,
//				required = true,
//				description = "Variant ID Code")
//		}
//	)
//	
//	@GetMapping
//	@ResponseStatus(code = HttpStatus.OK)	
//	List<Variant> fetchVariant(@RequestParam String variantId);
//
//}