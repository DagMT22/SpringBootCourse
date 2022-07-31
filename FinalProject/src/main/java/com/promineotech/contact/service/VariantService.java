package com.promineotech.contact.service;

import java.util.List;

import com.promineotech.contact.entity.Variant;

public interface VariantService {

	List<Variant> fetchVariants(String variantID);
	
}
