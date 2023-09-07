package com.nttdata.costconversion.infrastructure.adapter.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.costconversion.application.input.report.InputReportVO;
import com.nttdata.costconversion.application.output.ReportOutputVO;
import com.nttdata.costconversion.domain.model.PurchasesEntity;
import com.nttdata.costconversion.domain.repository.PurchaseRepository;
import com.nttdata.costconversion.domain.service.ReportService;
import com.nttdata.costconversion.infrastructure.adapter.util.CoreUtils;

@Service
public class ReportServiceImplementation implements ReportService{
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	public static final String ERROR_DATA_NOT_FOUND = "Datos no encontrados";

	Float totalCryptocurrency;
	Float totalPriceUsd;
	
	@Override
	public List<ReportOutputVO> generateReport(InputReportVO data) throws Exception {
	
		
		Date dateMin = CoreUtils.convertDateMinValue(data.getData().getDate());		
		Date dateMax = CoreUtils.convertDateMaxValue(data.getData().getDate());
		List<ReportOutputVO> report = null;		
		
		List<PurchasesEntity> purchases = purchaseRepository.
				findByDateAfterAndDateBeforeAndModelAndCryptoCurrency(dateMin,dateMax,
						data.getData().getModel(), data.getData().getCryptocurrency());
		
		
		if(purchases.isEmpty()) {
			throw new Exception(ERROR_DATA_NOT_FOUND);
		}
		
		totalCryptocurrency = 0F;
		totalPriceUsd = 0F;		
		
		purchases.stream().forEach(t -> {			
			totalCryptocurrency += t.getVersions().getPriceCryptocurrency();
			totalPriceUsd += t.getVersions().getPriceUsd();			
		});
		
		ReportOutputVO reportOutputVO = new ReportOutputVO();
		
		reportOutputVO.setCryptocurrency(data.getData().getCryptocurrency());
		reportOutputVO.setDate(data.getData().getDate());
		reportOutputVO.setModel(data.getData().getModel());
		reportOutputVO.setUsdAmount(totalPriceUsd);
		reportOutputVO.setCryptocurrencyAmount(totalCryptocurrency);
		report = new ArrayList<>();
		report.add(reportOutputVO);
		return report;		
	
	}
	

}
