package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;

import model.BuildingDTO;

// do bị tách ra từ BuildingService nên dùng component 
// Component : ~ giúp đánh dấu là 1 Bean nên sẽ Autowired 
@Component
public class BuildingDTOConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO buildingDTO = modelMapper.map(item, BuildingDTO.class);
		List<RentAreaEntity> rentAreas = item.getItems();		
		String areaResult = rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		buildingDTO.setRentarea(areaResult);// add vào buildingDTO 
		// bước filter
		buildingDTO.setAddress(item.getStreet() + ", " + item.getWard() + ", " + item.getDistrict().getName()); 		
		return buildingDTO;
		
	}
}
