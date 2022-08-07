package com.promineotech.contact.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.contact.entity.Case;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cases")
public interface CaseController {

	@Operation(summary = "Create a Case")
	@PostMapping(value = "/case")
	@ResponseStatus(code = HttpStatus.CREATED)
	Case createCase(@RequestBody Case inputCase);
}

