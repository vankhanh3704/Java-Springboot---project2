package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.StringUtil;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepository{
	
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
	
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		
		
		
		// JPQL
//		String sql = "FROM BuildingEntity b";
//		Query query = entityManager.createQuery(sql,BuildingEntity.class);
//		return query.getResultList();
	
		// SQL Native 
		StringBuilder sql = new StringBuilder( "SELECT * FROM building b WHERE b.name LIKE '%building%' ");
		joinTable(buildingSearchBuilder, sql);
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		queryNormal(buildingSearchBuilder, where);
		querySpecial(buildingSearchBuilder, where);
		// không bị trùng thì group by theo b.id
		where.append(" GROUP BY b.id;");
		sql.append(where);
		String sqlfinal  = sql.toString();
		System.out.print(sqlfinal);
		Query query = entityManager.createQuery(sqlfinal,BuildingEntity.class);
		return query.getResultList();
	}
	
}
