package com.nttdata.costconversion.application.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.costconversion.application.input.InputVO;
import com.nttdata.costconversion.application.output.ResponseVO;
import com.nttdata.costconversion.domain.service.ConversionService;
import com.nttdata.costconversion.infrastructure.adapter.util.CoreUtils;

import reactor.core.publisher.Mono;

/**
 * The class defines a REST controller which is a primary adapter and invokes the inbound port of the application.
 */
@RestController
@RequestMapping("/api/conversion")
public class ConversionController {

	@Autowired
    private ConversionService conversionService;

    public ConversionController(ConversionService convertionService) {
        this.conversionService = convertionService;
    }

    @PostMapping("create")
    public Mono<ResponseVO> create(@RequestBody InputVO data) {    
    	  try {		
  			return Mono.justOrEmpty(CoreUtils.responseSuccess(conversionService.createConversion(data)));		
  		}catch(Exception e) {
  			 return Mono.justOrEmpty(CoreUtils.responseException(e));			
  		}
  	}
    	

}
