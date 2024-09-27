package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;

@Component
public class BuildingSearchBuilderConverter {
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params,
															List<String> typeCode)
	{
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
																			.setName(MapUtil.getObject(params, "name",String.class))
																			.setFloorArea(MapUtil.getObject(params, "floorArea", Long.class))
																			.setWard(MapUtil.getObject(params, "ward", String.class))
																			.setTypeCode(typeCode)
																			.setStaffid(MapUtil.getObject(params, "staffId", Long.class))
																			.setAreaFrom(MapUtil.getObject(params, "areaFrom", Long.class))
																			.setAreaTo(MapUtil.getObject(params, "areaTo", Long.class))
																			.setDistrictId(MapUtil.getObject(params, "districtId", String.class))
																			.setManagername(MapUtil.getObject(params, "managername", String.class))
																			.setManagerphonenumber(MapUtil.getObject(params, "managerphonenumber", String.class))
																			.setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", Long.class))
																			.setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", Long.class))
																			.setServicefee(MapUtil.getObject(params, "servicefee", String.class))
																			.setStreet(MapUtil.getObject(params, "street", String.class))
																			.build();
		return buildingSearchBuilder;
	}
}
