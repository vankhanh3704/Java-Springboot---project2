package com.javaweb.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingRepository {
	// tham so bay h la 1 buildingsearchbuilder
	List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);
	
}
