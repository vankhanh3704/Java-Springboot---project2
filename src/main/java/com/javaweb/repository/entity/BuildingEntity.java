package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "building")
public class BuildingEntity {
	@Id // set nó là khoá chính 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // sett tự động tăng dần: thay the auto-increment 
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "ward")
	private String ward;
//	
//	@Column(name = "districtid")
//	private Long districtId;
	
	@Column(name = "structure")
	private String structure;
	
	@Column(name = "floorarea")
	private Long floorarea;
	
	@Column(name = "rentprice")
	private Long rentPrice;
	
	@Column(name = "servicefee")
	private String servicefee;
	
	@Column(name = "brokeragefee")
	private String brokeragefee;
	
	@Column(name = "managername")
	private String managername;
	
	@Column(name = "managerphonenumber")
	private String managerphonenumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "districtid")
	private DistrictEntity district; 
	
	@OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
	private List<RentAreaEntity> items = new ArrayList<>();
	
	

	public List<RentAreaEntity> getItems() {
		return items;
	}
	public void setItems(List<RentAreaEntity> items) {
		this.items = items;
	}
	public DistrictEntity getDistrict() {
		return district;
	}
	public void setDistrict(DistrictEntity district) {
		this.district = district;
	}
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

//	public Long getDistrictId() {
//		return districtId;
//	}
//	public void setDistrictId(Long districtId) {
//		this.districtId = districtId;
//	}
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
