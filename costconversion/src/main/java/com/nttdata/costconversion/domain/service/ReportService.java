package com.nttdata.costconversion.domain.service;

import java.util.List;

import com.nttdata.costconversion.application.input.report.InputReportVO;
import com.nttdata.costconversion.application.output.ReportOutputVO;

public interface ReportService {
	
	List<ReportOutputVO> generateReport(InputReportVO data) throws Exception;
}
