package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.filefilter.AndFileFilter;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaweb.repository.BuildingRepository;

import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionJDBCUtil;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;

import Model.BuildingDTO;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

	// do trong 1 hàm không thể dồn quá 1 công việc

	// nên ta sẽ tạo 1 hàm join để xử lý
	public static void joinTable(Map<String, Object> params, List<String> typeCode, StringBuilder sql) {
		// những thằng cần join typecode,area, staffid

		// đầu tiên sẽ join bảng nhân viên bằng staffid
		String staffId = (String) params.get("staffid");

		// nếu là số thì kiểm tra check xem có khác null hay không
		// sau khi học xog thì lúc này ta sẽ dùng StringUtil
		if (StringUtil.checkString(staffId)) {
			sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
		}
		// còn nếu là xâu thì check != null trước rồi mới check != rỗng
		// nếu có key k có value thì nó sẽ trả về xâu rỗng
		// còn nếu không có cả 2 sẽ trả về null
		if (typeCode != null && typeCode.size() != 0) {
			// append bảng trung gian để lấy renttypeid
			sql.append(" INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
			// bảng cần lấy dữ liệu sau khi join bảng trung gian
			sql.append(" INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeid ");
		}

		// rentarea : có 2 giá trị
		String rentAreaTo = (String) params.get("areaTo");
		String rentAreaFrom = (String) params.get("areaFrom");
//		// chỉ cần 1 trong 2 là được có thể lấy dữ liệu
//		if ((StringUtil.checkString(rentAreaTo)) || StringUtil.checkString(rentAreaFrom)) {
//			// join tới bảng rentarea
//			sql.append(" INNER JOIN rentarea ON b.id = rentarea.buildingid ");
//		}

		// dùng EXITS -> không cần join

	}

	// join xog mới tới query
	// hàm xử lý các câu query
	// normal : xử lý những thằng bình thường như like , 1= 1 , số
	// special : những thằng dùng IN , hay >= <= , hoặc là những thằng của bảng khác
	public static void queryNormal(Map<String, Object> params, StringBuilder where) {
		// loại những thằng special đi là được
		// duyệt map
		for (Map.Entry<String, Object> it : params.entrySet()) {
			if (!it.getKey().equals("staffid") && !it.getKey().equals("typeCode") && !it.getKey().startsWith("area")
					&& !it.getKey().startsWith("rentPrice")) {
				String value = it.getValue().toString();
				if (StringUtil.checkString(value)) {
					if (NumberUtil.isNumber(value)) {
						where.append(" AND b." + it.getKey() + " = " + value);
					}
				} else {
					where.append(" AND b." + it.getKey() + " LIKE '%" + value + "%' ");
				}
			}
		}
	}

	public static void querySpecial(Map<String, Object> params, List<String> typeCode, StringBuilder where) {
		String staffId = (String) params.get("staffid");
		if (StringUtil.checkString(staffId)) {
			where.append(" AND assignmentbuilding.staffid = " + staffId);
		}

		// bang? rentarea
		String rentAreaTo = (String) params.get("areaTo");
		String rentAreaFrom = (String) params.get("areaFrom");
		// chỉ cần 1 trong 2 là được có thể lấy dữ liệu
		if ((StringUtil.checkString(rentAreaTo)) || StringUtil.checkString(rentAreaFrom)) {
//
//			if (StringUtil.checkString(rentAreaTo)) {
//				where.append(" AND rentarea.value <= " + rentAreaTo);
//			}
//			if (StringUtil.checkString(rentAreaFrom)) {
//				where.append(" AND rentarea.value >= " + rentAreaFrom);
//			}
			where.append(" AND EXISTS (SELECT * FROM rentarea r where b.id = r.buildingid ");

			if (StringUtil.checkString(rentAreaTo)) {
				where.append(" AND r.value <= " + rentAreaTo);
			}
			if (StringUtil.checkString(rentAreaFrom)) {
				where.append(" AND r.value >= " + rentAreaFrom);
			}
			where.append(" ) ");
		}
		// bang? renPrice
		String rentPriceTo = (String) params.get("rentPriceTo");
		String rentPriceFrom = (String) params.get("rentPriceFrom");
		// chỉ cần 1 trong 2 là được có thể lấy dữ liệu
		// EXITS
		if ((StringUtil.checkString(rentPriceTo)) || StringUtil.checkString(rentPriceFrom)) {
			if (StringUtil.checkString(rentPriceTo)) {
				where.append(" AND b.rentprice <= " + rentPriceTo);
			}
			if (StringUtil.checkString(rentPriceFrom)) {
				where.append(" AND b.rentprice >= " + rentPriceFrom);
			}

		}

		// typeCode
		// dùng java 7

//		if (typeCode != null && typeCode.size() != 0) {
//			List<String> list = new ArrayList<>();
//			for (String item : typeCode) {
//				list.add("'" + item + "'");
//			}
//			where.append(" AND renttype.code IN(" + String.join(",", list) + ") ");
//		}

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
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCode) {
		// neu tim kiem nhung k co truong du lieu thi hien thi het ra
		StringBuilder sql = new StringBuilder(
				"SELECT b.id, b.name, b.street, b.ward, b.districtid, b.structure, b.numberofbasement,"
						+ "b.floorarea, b.rentprice, b.managername, b.managerphonenumber, b.servicefee, b.brokeragefee "
						+ "\nFROM building b ");

		// gọi hàm để thực thi
		joinTable(params, typeCode, sql);
		// dùng 1 StringBuilder ở đây để có thể dễ dàng xử lý các câu lệnh SQL được dễ
		// dàng
		// xử lý phần WHERE
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		queryNormal(params, sql);
		querySpecial(params, typeCode, sql);
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
				building.setDistrictId(rs.getLong("districtid"));
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
