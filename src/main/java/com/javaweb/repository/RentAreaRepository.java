package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.RentAreaEntity;

public interface RentAreaRepository {
	// do cái này mình muốn trả 1 list các String : vi du : 100, 200, 300
	// 1 cái RentAreaEntity
	List<RentAreaEntity> getValueByBuildingId(Long id);
	
}
