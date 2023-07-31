package com.nttdata.costconversion.application.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.costconversion.application.input.InputVO;
import com.nttdata.costconversion.application.output.ResponseVO;
import com.nttdata.costconversion.domain.service.PurchaseService;
import com.nttdata.costconversion.infrastructure.adapter.util.CoreUtils;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {
	
	@Autowired
    private PurchaseService purchaseService;
	
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("save")
    public Mono<ResponseVO> create(@RequestBody InputVO data) {
    	  try {		
  			return Mono.justOrEmpty(CoreUtils.responseSuccess(purchaseService.savePurchase(data)));		
  		}catch(Exception e) {
  			 return Mono.justOrEmpty(CoreUtils.responseException(e));			
  		}
  	}
}
