package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionJDBCUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class JDBCBuildingRepositoryImpl implements BuildingRepository {

	// do trong 1 hàm không thể dồn quá 1 công việc

	// nên ta sẽ tạo 1 hàm join để xử lý
	public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {

		String staffId = buildingSearchBuilder.getStaffid().toString();

		if (StringUtil.checkString(staffId)) {
			sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
		}
		List<String> typeCode = buildingSearchBuilder.getTypeCode();

		if (typeCode != null && typeCode.size() != 0) {

			sql.append(" INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
			sql.append(" INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeid ");
		}

	}

	public static void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		try {
			// field của reflection
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field item : fields) {
				// item là 1 field của đối tượng đó gồm có name, value
				item.setAccessible(true); // giúp lấy được các value
				// để bằng true thì nó mới có quyền truy cập vào value

				String fieldName = item.getName();
				if (!fieldName.equals("staffid") && !fieldName.equals("typeCode") && !fieldName.startsWith("area")
						&& !fieldName.startsWith("rentPrice")) {
					Object value = item.get(buildingSearchBuilder);
					if (value != null) {
						if (item.getType().getName().equals("java.lang.Long")
								|| item.getType().getName().equals("java.lang.Integer")) {
							where.append(" AND b." + fieldName + " = " + value);
						} else if (item.getType().getName().equals("java.lang.String")) {
							where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		String staffId = buildingSearchBuilder.getStaffid().toString();
		if (StringUtil.checkString(staffId)) {
			where.append(" AND assignmentbuilding.staffid = " + staffId);
		}

		// bang? rentarea
		Long rentAreaTo = buildingSearchBuilder.getAreaTo();
		Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
		// chỉ cần 1 trong 2 là được có thể lấy dữ liệu
		if (rentAreaTo != null || rentAreaFrom != null) {

			where.append(" AND EXISTS (SELECT * FROM rentarea r where b.id = r.buildingid ");

			if (rentAreaTo != null) {
				where.append(" AND r.value <= " + rentAreaTo);
			}
			if (rentAreaFrom != null) {
				where.append(" AND r.value >= " + rentAreaFrom);
			}
			where.append(" ) ");
		}
		// bang? renPrice
		Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
		Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
		// chỉ cần 1 trong 2 là được có thể lấy dữ liệu
		// EXITS
		if (rentPriceTo != null || rentAreaFrom != null) {
			if (rentPriceTo != null) {
				where.append(" AND b.rentprice <= " + rentPriceTo);
			}
			if (rentAreaFrom != null) {
				where.append(" AND b.rentprice >= " + rentPriceFrom);
			}

		}
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		// dùng java 8
		if (typeCode != null && typeCode.size() != 0) {
			where.append(" AND (");
			String sql = typeCode.stream().map(it -> "renttype.code LIKE " + " '%" + it + "%' ")
					.collect(Collectors.joining(" OR "));
			where.append(sql);
			where.append(" ) ");
		}
	}

	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		// neu tim kiem nhung k co truong du lieu thi hien thi het ra
		StringBuilder sql = new StringBuilder(
				"SELECT b.id, b.name, b.street, b.ward, b.districtid, b.structure, b.numberofbasement,"
						+ "b.floorarea, b.rentprice, b.managername, b.managerphonenumber, b.servicefee, b.brokeragefee "
						+ "\nFROM building b ");

		// gọi hàm để thực thi
		joinTable(buildingSearchBuilder, sql);
		// dùng 1 StringBuilder ở đây để có thể dễ dàng xử lý các câu lệnh SQL được dễ
		// dàng
		// xử lý phần WHERE
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		queryNormal(buildingSearchBuilder, where);
		querySpecial(buildingSearchBuilder, where);
		// không bị trùng thì group by theo b.id
		where.append(" GROUP BY b.id;");
		sql.append(where);

		System.out.print(sql);
		// kiem tra dieu kien

		List<BuildingEntity> result = new ArrayList<>();
		try (Connection con = ConnectionJDBCUtil.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());) {
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getLong("id"));
				building.setName(rs.getString("name"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setFloorarea(rs.getLong("floorarea"));
				building.setRentPrice(rs.getLong("rentprice"));
				building.setServicefee(rs.getString("servicefee"));
				building.setBrokeragefee(rs.getString("brokeragefee"));
				building.setManagername(rs.getString("managername"));
				building.setManagerphonenumber(rs.getString("managerphonenumber"));
				result.add(building);
			}
		} catch (SQLException e) {

			e.printStackTrace();
//			System.out.println("Connected database failed...");
		}
		return result;

	}

}
