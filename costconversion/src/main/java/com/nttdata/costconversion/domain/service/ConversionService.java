package com.nttdata.costconversion.domain.service;

import com.nttdata.costconversion.application.input.InputVO;
import com.nttdata.costconversion.application.output.ConvertionOutputVO;

public interface ConversionService {
	
	ConvertionOutputVO createConversion(InputVO data) throws Exception;
	
}
