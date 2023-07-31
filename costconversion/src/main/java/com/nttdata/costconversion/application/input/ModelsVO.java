package com.nttdata.costconversion.application.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModelsVO {
	private String model;
	private String version;		
	private Float priceUsd;
	private Float priceCryptocurrency;	
	private String cryptocurrency;
}
