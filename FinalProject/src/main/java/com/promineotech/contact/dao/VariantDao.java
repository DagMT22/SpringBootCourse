package com.promineotech.contact.dao;

import java.util.List;

import com.promineotech.contact.entity.Variant;

public interface VariantDao {

	List<Variant> readVariants(String variantId);
	
}
