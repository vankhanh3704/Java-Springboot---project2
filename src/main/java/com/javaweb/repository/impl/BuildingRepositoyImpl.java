package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.AndFileFilter;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaweb.repository.BuildingRepositoty;
import com.javaweb.repository.entity.BuildingEntity;

import Model.BuildingDTO;



@Repository
public class BuildingRepositoyImpl implements BuildingRepositoty{

	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic?autoReconnect=true&useSSL=false";
	static final String username = "root";
	static final String password = "Hoangkhanhvan703";
	
	@Override
	public List<BuildingEntity> findAll(String name, Integer distristId) {
//		String sql = "SELECT * FROM building b WHERE b.name LIKE '% " + name + " %'" ;
		
		// neu tim kiem nhung k co truong du lieu thi hien thi het ra
		StringBuilder sql = new StringBuilder("SELECT * FROM building b WHERE 1 = 1 ");
		
		// kiem tra dieu kien
		if(name != null && !name.equals("")) {
			sql.append("AND b.name LIKE '%" + name + "%' ");
		}
		if(distristId != null) {
			sql.append("AND b.distristid = " + distristId + " ");
		}
		
		
		List<BuildingEntity> result = new ArrayList<>();
		try(Connection con = DriverManager.getConnection(DB_URL,username,password);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());
				) {
			while(rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setName(rs.getString("name"));
				building.setNumberOfBasement(rs.getInt("numberofbasement"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				result.add(building);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
//			System.out.println("Connected database failed...");
		}
		return result;
		
	}
	
}
