package com.nttdata.costconversion.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttdata.costconversion.domain.model.ConversionEntity;

public interface ConversionRepository extends JpaRepository<ConversionEntity, Long> {

	List<ConversionEntity> findByConversionIdAndModelAndCryptoCurrency(String conversionId,String model, String cryptoCurrency);
	
}
