package com.nttdata.costconversion.domain.service;

import com.nttdata.costconversion.application.input.purchase.InputPurchaseVO;
import com.nttdata.costconversion.application.output.PurchaseOutputVO;

public interface PurchaseService {
	
	PurchaseOutputVO savePurchase(InputPurchaseVO data) throws Exception;
}
