package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.service.BuildingService;

import Model.BuildingDTO;


@Service
public class BuildingServiceImpl implements BuildingService{

	@Autowired
	private BuildingRepository buildingRepository;
	
	// autowired Converter
	@Autowired
	private BuildingDTOConverter buildingDTOConverter;
	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params,  List<String> typeCode) {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(params, typeCode);
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		
		// nhưng chuyển đổi sẽ làm phần package converter 
		for(BuildingEntity item : buildingEntities) {
			BuildingDTO buildingDTO = buildingDTOConverter.toBuildingDTO(item);
			result.add(buildingDTO);
		}
		return result;
	}

}
