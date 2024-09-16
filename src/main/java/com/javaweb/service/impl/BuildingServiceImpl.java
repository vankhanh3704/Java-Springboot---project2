package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.repository.BuildingRepositoty;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

import Model.BuildingDTO;


@Service
public class BuildingServiceImpl implements BuildingService{

	@Autowired
	private BuildingRepositoty buildingRepositoty;
	@Override
	public List<BuildingDTO> findAll(String name, Integer distristId) {
		List<BuildingEntity> buildingEntities = buildingRepositoty.findAll(name, distristId);
		
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		for(BuildingEntity item : buildingEntities) {
			BuildingDTO buildingDTO = new BuildingDTO();
			buildingDTO.setName(item.getName());
			buildingDTO.setNumberOfBasement(item.getNumberOfBasement());
			// bước filter
			buildingDTO.setAddress(item.getStreet() + ", " + item.getWard() );
			result.add(buildingDTO);
			
		}
		return result;
	}

}
