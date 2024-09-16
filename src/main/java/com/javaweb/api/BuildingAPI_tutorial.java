//package com.javaweb.api;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fasterxml.jackson.annotation.JacksonInject.Value;
//import com.javaweb.service.BuildingService;
//
//import Model.BuildingDTO;
//import Model.ErrorResponseDTO;
//import custumeExeption.FieldRequiredException;
//
//@RestController
//public class BuildingAPI_tutorial {
//	// chuyển đổi data từ server trả ra cho client thành JSON
//
////	nhan bang value
////	@RequestMapping(value = "/api/building/", method = RequestMethod.GET)
////	public void getMapping(@RequestParam(value = "name", required = false) String name,
////							@RequestParam(value ="numberofBasement" , required = false) Integer numberofBasement) {
////		System.out.println(name + " " + numberofBasement);
////	}
//
////	nhận bằng map
////	@RequestMapping(value = "/api/building/", method = RequestMethod.PUT)
////	public void getMapping2(@RequestParam Map<String,String> params) {
////		System.out.println("ok");
////	}
////	nhận bằng java 
////	@RequestMapping(value = "/api/building/", method = RequestMethod.POST)
////	public void getMapping3(@RequestBody BuildingDTB buildingDTB) {
////		System.out.println("ok");
////	}
//
////	 cách khai báo 
////	@GetMapping(value = "/api/building/")
////	 chuyển đổi data từ server trả ra cho client thành JSON
////	@ResponseBody // phải để ngay đầu hàm 
////	public BuildingDTB getBuilding(@RequestParam(value = "name", required = false) String nameBuilding,
////			@RequestParam(value ="numberOfBasement" , required = false) Integer numberofBasement,
////			@RequestParam(value ="ward" , required = false) String ward
////			) {
////		BuildingDTB building = new BuildingDTB();
////		building.setName(nameBuilding);
////		building.setNumberOfBasement(numberofBasement);
////		building.setWard(ward);
////		return building;
////	}
////	
////	@DeleteMapping(value = "/api/building/{id}")
////	public void deleteBuilding(@PathVariable Integer id) {
////		System.out.println("Da xoa toa nha co id la 1!");
////	}
//
//	// List ve Java bean
////	@GetMapping(value = "/api/building/")
////	public List<BuildingDTB> getBuilding(@RequestParam(value = "name", required = false) String nameBuilding,
////			@RequestParam(value ="numberOfBasement" , required = false) Integer numberofBasement,
////			@RequestParam(value ="ward" , required = false) String ward){
////			List<BuildingDTB> listBuildings = new ArrayList<>();
////			
////			BuildingDTB building1 = new BuildingDTB();
////			building1.setName("ABC Building");
////			building1.setNumberOfBasement(4);
////			building1.setWard("Tan Mai");
////			
////			BuildingDTB building2 = new BuildingDTB();
////			building2.setName("XYZ Building");
////			building2.setNumberOfBasement(10);
////			building2.setWard("Tran Phu");
////			
////			listBuildings.add(building1);
////			listBuildings.add(building2);
////			return listBuildings;
////	}
//
//	// nếu có lỗi trả cho FE lỗi: dùng try catch
////	@GetMapping(value = "/api/building/")
////	public Object getBuilding(@RequestParam(value = "name", required = false) String nameBuilding,
////	@RequestParam(value ="numberOfBasement" , required = false) Integer numberofBasement,
////	@RequestParam(value ="ward" , required = false) String ward
////	) {
////		try {
////			System.out.println(5/0);
////		} catch (Exception e) {
////			// TODO: handle exception
////			ErrorResponseDTO err = new ErrorResponseDTO();
////			// set cai error
////			err.setError(e.getMessage());
////			List<String> details = new ArrayList<>();
////			details.add("lỗi chia cho số 0");
////			err.setDetail(details);
////			return err;
////		}
////		
////	BuildingDTB building = new BuildingDTB();
////	building.setName(nameBuilding);
////	building.setNumberOfBasement(numberofBasement);
////	building.setWard(ward);
////	return null;
////	}
//
////	@GetMapping(value = "/api/building/")
////	public Object getBuilding(@RequestBody BuildingDTB buildingDTB
////	){
////		try {
////				validate(buildingDTB);
////		} catch (FieldRequiredException e) {
////			// TODO: handle exception
////			ErrorResponseDTO err = new ErrorResponseDTO();
////			// set cai error
////			err.setError(e.getMessage());
////			List<String> details = new ArrayList<>();
////			details.add("check lai dau vao");
////			err.setDetail(details);
////			return err;
////		}
//
////		
////	BuildingDTB building = new BuildingDTB();
////	building.setName(nameBuilding);
////	building.setNumberOfBasement(numberofBasement);
////	building.setWard(ward);
////		validate(buildingDTB);
////		System.out.println(5/0);
////	return null;
////	}
//
//	// hướng dẫn phần: MVC mô hình 3 layer
//	// không nên làm
////	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic?autoReconnect=true&useSSL=false";
////	static final String username = "root";
////	static final String password = "Hoangkhanhvan703";
////	@GetMapping(value = "/api/building")
////	
////										// them dieu kien @requestparam
////	public List<BuildingDTB> getBuilding(@RequestParam (name = "name") String name){
////		String sql = "SELECT * FROM building WHERE name like '% "+name+"' ";
////		List<BuildingDTB> result = new ArrayList<>();
////		try(Connection con = DriverManager.getConnection(DB_URL,username,password);
////				Statement stmt = con.createStatement();
////				ResultSet rs = stmt.executeQuery(sql);
////				) {
////			while(rs.next()) {
////				BuildingDTB building = new BuildingDTB();
////				building.setName(rs.getString("name"));
////				building.setNumberOfBasement(rs.getInt("numberofbasement"));
////				building.setStreet(rs.getString("street"));
////				building.setWard(rs.getString("ward"));
////				result.add(building);
////			}
////		} catch (SQLException e) {
////		
////			e.printStackTrace();
////			System.out.println("Connected database failed...");
////		}
////		return result;
////	}
//
//	
//	
//	// gọi hàm Service
//	@Autowired
//	private BuildingService buildingService;
//	@GetMapping(value = "/api/building")
//										// them dieu kien @requestparam
//	public List<BuildingDTO> getBuilding(@RequestParam(name = "name") String name) {
//		List<BuildingDTO> result = buildingService.findAll(name);
//		return result;
//	}
//
//	public void validate(BuildingDTO buildingDTB) {
//		if (buildingDTB.getName() == null || buildingDTB.getNumberOfBasement() == null
//				|| buildingDTB.getName().equals("")) {
//			throw new FieldRequiredException("nume or numberofbasement is null");
//		}
//	}
//
//}