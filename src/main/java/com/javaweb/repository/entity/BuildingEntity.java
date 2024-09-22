package com.javaweb.repository.entity;

public class BuildingEntity {
	private Long id;
	private String name;
	private String street;
	private String ward;
	private Long districtId;
	private String structure;
	private Long floorarea;
	private Long rentPrice;
	private String servicefee;
	private String brokeragefee;
	private String managername;
	private String managerphonenumber;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getStreet() {
		return street;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getStructure() {
		return structure;
	}
	public void setStructure(String structure) {
		this.structure = structure;
	}
	public Long getFloorarea() {
		return floorarea;
	}
	public void setFloorarea(Long floorarea) {
		this.floorarea = floorarea;
	}
	public Long getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Long rentPrice) {
		this.rentPrice = rentPrice;
	}
	public String getServicefee() {
		return servicefee;
	}
	public void setServicefee(String servicefee) {
		this.servicefee = servicefee;
	}
	public String getBrokeragefee() {
		return brokeragefee;
	}
	public void setBrokeragefee(String brokeragefee) {
		this.brokeragefee = brokeragefee;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public String getManagerphonenumber() {
		return managerphonenumber;
	}
	public void setManagerphonenumber(String managerphonenumber) {
		this.managerphonenumber = managerphonenumber;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
}
