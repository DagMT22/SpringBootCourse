package com.promineotech.contact.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Variant {
	
	private String variantId;
	private String variantName;
	private String diseaseName;
	private int infectiousTimeRange;
	
}
