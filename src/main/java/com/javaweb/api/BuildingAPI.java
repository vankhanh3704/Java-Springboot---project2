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

import Model.BuildingDTO;
import Model.ErrorResponseDTO;
import custumeExeption.FieldRequiredException;

@RestController
public class BuildingAPI {

	
	// gọi hàm Service
	@Autowired
	private BuildingService buildingService;
	@GetMapping(value = "/api/building")
										// them dieu kien @requestparam
	public List<BuildingDTO> getBuilding(@RequestParam(name = "name", required = false) String name,
										@RequestParam(name = "districtid", required = false) Integer distristId,
										@RequestParam(name = "districtid", required = false) List<String> typeCode) {
		List<BuildingDTO> result = buildingService.findAll(name, distristId);
		return result;
	}

	public void validate(BuildingDTO buildingDTB) {
		if (buildingDTB.getName() == null || buildingDTB.getNumberOfBasement() == null
				|| buildingDTB.getName().equals("")) {
			throw new FieldRequiredException("nume or numberofbasement is null");
		}
	}

}