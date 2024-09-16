package com.javaweb.service;

import java.util.List;


import Model.BuildingDTO;

public interface BuildingService {
	List<BuildingDTO> findAll(String name, Integer distristId);
}
