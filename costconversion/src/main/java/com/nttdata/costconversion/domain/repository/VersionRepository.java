package com.nttdata.costconversion.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttdata.costconversion.domain.model.ConversionEntity;
import com.nttdata.costconversion.domain.model.VersionsEntity;



public interface VersionRepository extends  JpaRepository<VersionsEntity, Long> {
	
	List<VersionsEntity> findByConversionAndVersion(ConversionEntity conversion, String version);	

}
