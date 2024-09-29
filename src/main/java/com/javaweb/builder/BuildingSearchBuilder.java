package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {
	private String name;
	private Long floorArea;
	private String ward;
	private String street;
	private String districtid;
	private Long numberofbasement;
	private List<String> typeCode = new ArrayList<>();
	private Long level;
	private String managername;
	private String managerphonenumber;
	private String servicefee;
	private String staffid;
	private Long areaTo;
	private Long areaFrom;
	private Long rentPriceTo;
	private Long rentPriceFrom;
	
	// hàm constructor dựa vào hàm con
	private BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.floorArea = builder.floorArea;
		this.ward = builder.ward;
		this.street = builder.street;
		this.districtid = builder.districtid;
		this.numberofbasement = builder.numberofbasement;
		this.typeCode = builder.typeCode;
		this.level = builder.level;
		this.managername = builder.managername;
		this.managerphonenumber = builder.managerphonenumber;
		this.servicefee = builder.servicefee;
		this.staffid = builder.staffid;
		this.areaTo = builder.areaTo;
		this.areaFrom = builder.areaFrom;
		this.rentPriceFrom = builder.rentPriceFrom;
		this.rentPriceTo = builder.rentPriceTo;
	}
	
	
	public String getName() {
		return name;
	}
	public Long getFloorArea() {
		return floorArea;
	}
	public String getWard() {
		return ward;
	}
	public String getStreet() {
		return street;
	}
	public String getDistrictId() {
		return districtid;
	}
	public Long getNumberofbasement() {
		return numberofbasement;
	}
	public List<String> getTypeCode() {
		return typeCode;
	}
	public Long getLevel() {
		return level;
	}
	public String getManagername() {
		return managername;
	}
	public String getManagerphonenumber() {
		return managerphonenumber;
	}
	public String getServicefee() {
		return servicefee;
		
	}
	public String getStaffid() {
		return staffid;
	}
	public Long getAreaTo() {
		return areaTo;
	}
	public Long getAreaFrom() {
		return areaFrom;
	}
	public Long getRentPriceTo() {
		return rentPriceTo;
	}
	public Long getRentPriceFrom() {
		return rentPriceFrom;
	}
	
	
	// class con 
	public static class Builder{
		private String name;
		private Long floorArea;
		private String ward;
		private String street;
		private String districtid;
		private Long numberofbasement;
		private List<String> typeCode = new ArrayList<>();
		private Long level;
		private String managername;
		private String managerphonenumber;
		private String servicefee;
		private String staffid;
		private Long areaTo;
		private Long areaFrom;
		private Long rentPriceTo;
		private Long rentPriceFrom;
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		public Builder setFloorArea(Long floorArea) {
			this.floorArea = floorArea;
			return this;
		}
		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}
		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}
		public Builder setDistrictId(String districtid) {
			this.districtid = districtid;
			return this;
		}
		public Builder setNumberofbasement(Long numberofbasement) {
			this.numberofbasement = numberofbasement;
			return this;
		}
		public Builder setTypeCode(List<String> typeCode) {
			this.typeCode = typeCode;
			return this;
		}
		public Builder setLevel(Long level) {
			this.level = level;
			return this;
		}
		public Builder setManagername(String managername) {
			this.managername = managername;
			return this;
		}
		public Builder setManagerphonenumber(String managerphonenumber) {
			this.managerphonenumber = managerphonenumber;
			return this;
		}
		public Builder setServicefee(String servicefee) {
			this.servicefee = servicefee;
			return this;
		}
		public Builder setStaffid(String staffid) {
			this.staffid = staffid;
			return this;
		}
		public Builder setAreaTo(Long areaTo) {
			this.areaTo = areaTo;
			return this;
		}
		public Builder setAreaFrom(Long areaFrom) {
			this.areaFrom = areaFrom;
			return this;
		}
		public Builder setRentPriceTo(Long rentPriceTo) {
			this.rentPriceTo = rentPriceTo;
			return this;
		}
		public Builder setRentPriceFrom(Long rentPriceFrom) {
			this.rentPriceFrom = rentPriceFrom;
			return this;	
		}
		
		
		// hàm gọi hàm khởi tạo 
		public BuildingSearchBuilder build() {
			// đẩy this vào là gọi tới các tham số trong class con 
			return new BuildingSearchBuilder(this);
		}
		
	}


	
}
