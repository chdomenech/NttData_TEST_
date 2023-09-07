package com.nttdata.costconversion.domain.service;

import com.nttdata.costconversion.application.input.conversion.InputConversionVO;
import com.nttdata.costconversion.application.output.ConvertionOutputVO;

public interface ConversionService {
	
	ConvertionOutputVO createConversion(InputConversionVO data) throws Exception;
	
}
