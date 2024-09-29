package com.javaweb.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.javaweb.service.BuildingService;

import model.BuildingDTO;
import model.ErrorResponseDTO;
import custumeExeption.FieldRequiredException;

@RestController
public class BuildingAPI {

	
	// gọi hàm Service
	@Autowired
	private BuildingService buildingService;
	@GetMapping(value = "/api/building")
										// them dieu kien @requestparam
	public List<BuildingDTO> getBuilding(@RequestParam Map<String, Object> params,
										@RequestParam(name = "typeCode", required = false) List<String> typeCode) {
		List<BuildingDTO> result = buildingService.findAll(params, typeCode);
		return result;
	}

//	public void validate(BuildingDTO buildingDTB) {
//		if (buildingDTB.getName() == null || buildingDTB.getNumberOfBasement() == null
//				|| buildingDTB.getName().equals("")) {
//			throw new FieldRequiredException("name or numberofbasement is null");
//		}
//	}
	
	
	@GetMapping(value = "/api/building/")
	public void createBuilding(@RequestBody Building)
	
}