package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;

@Component
public class BuildingSearchBuilderConverter {
	@Autowired
	private ModelMapper modelMapper;
	
//	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params,
//															List<String> typeCode)
//	{
////		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder
////		returnBuilder null;
//	}
}
