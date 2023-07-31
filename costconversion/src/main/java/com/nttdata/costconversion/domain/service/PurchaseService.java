package com.nttdata.costconversion.domain.service;

import com.nttdata.costconversion.application.input.InputVO;
import com.nttdata.costconversion.application.output.PurchaseOutputVO;

public interface PurchaseService {
	
	PurchaseOutputVO savePurchase(InputVO data) throws Exception;
}
