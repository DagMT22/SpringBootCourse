package com.promineotech.contact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.contact.entity.Variant;
import com.promineotech.contact.service.VariantService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultVariantController implements VariantController {
	
	@Autowired
	private VariantService variantService;

	@Override
	public List<Variant> fetchVariant(String variantId) {
		log.debug("variant Id = {}", variantId);
		return variantService.fetchVariants(variantId);
	}

}
