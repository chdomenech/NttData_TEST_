package com.nttdata.costconversion.application.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConvertionInputVO {		
	private String model;
	private String cryptocurrency;
	private String convertionId;
	private String fullName;
	private String version;
	private String date;
}