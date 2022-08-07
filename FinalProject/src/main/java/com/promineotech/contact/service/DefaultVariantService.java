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
	public List<Variant> readVariants(String variantId) {
		log.info("Service: Variant Id = {}", variantId);
		
		List<Variant> variant = variantDao.readVariants(variantId);
		
		return variant;
	}

}
