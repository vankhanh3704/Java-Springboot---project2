package com.javaweb.service;

import java.util.List;
import java.util.Map;

import Model.BuildingDTO;

public interface BuildingService {
	List<BuildingDTO> findAll(Map<String,Object> params, List<String> typeCode );
}
