package com.promineotech.contact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.contact.dao.VariantDao;
import com.promineotech.contact.entity.Variant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultVariantService implements VariantService {
	
	@Autowired
	private VariantDao variantDao;

	@Override
	public List<Variant> fetchVariants(String variantId) {
		log.info("variant ID = {}", variantId);
		
		List<Variant> variant = variantDao.fetchVariants(variantId);
		
		return variant;
	}

}
