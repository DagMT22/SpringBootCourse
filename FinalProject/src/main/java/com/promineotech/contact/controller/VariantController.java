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
