package com.javaweb.repository.entity;

public class RentAreaEntity {
	// mặc dù có 3 Field nhưng mà chỉ cần lấy field value 
	// nhưng phải đủ Field để map 1 : 1 vs DB 
	private Long id;
	private Long buildingid;
	private Long value;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBuildingId() {
		return buildingid;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingid = buildingId;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	
}
