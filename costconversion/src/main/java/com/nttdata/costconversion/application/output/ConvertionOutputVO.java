package com.nttdata.costconversion.application.output;

import java.util.List;

import com.nttdata.costconversion.application.input.ModelsVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConvertionOutputVO {	
	private Integer conversionTimelife;
	private String convertionId;
	private  List<ModelsVO> versions;
}
