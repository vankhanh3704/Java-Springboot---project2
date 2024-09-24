package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.utils.ConnectionJDBCUtil;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {

	@Override
	public List<RentAreaEntity> getValueByBuildingId(Long id) {
		// SELECT tới bản rentarea
		String sql = "SELECT * FROM rentarea WHERE rentarea.buildingid = " + id;
		List<RentAreaEntity> rentAreas = new ArrayList<>();
		try (Connection con = ConnectionJDBCUtil.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);){
			// bên trong hàm while này mình sẽ set những cái cần để trả về
				while (rs.next()) {
					RentAreaEntity areaEntity = new RentAreaEntity();
					areaEntity.setValue(rs.getLong("value"));
					// thêm vào rentAreas 
					rentAreas.add(areaEntity);
					// sau đó sang service xử lý 
				}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return rentAreas;
	}

}
