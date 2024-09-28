package com.javaweb.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rentarea")
public class RentAreaEntity {
	// mặc dù có 3 Field nhưng mà chỉ cần lấy field value 
	// nhưng phải đủ Field để map 1 : 1 vs DB 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	
//	@Column(name = "buildingid")
//	private Long buildingid;
	
	@Column(name = "value")
	private Long value;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "buildingid")
	private BuildingEntity building;
	
	
	
	
	
//	public Long getBuildingid() {
//		return buildingid;
//	}
	public BuildingEntity getBuilding() {
		return building;
	}
//	public void setBuildingid(Long buildingid) {
//		this.buildingid = buildingid;
//	}
	public void setBuilding(BuildingEntity building) {
		this.building = building;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public Long getBuildingId() {
//		return buildingid;
//	}
//	public void setBuildingId(Long buildingId) {
//		this.buildingid = buildingId;
//	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	
}
