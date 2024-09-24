package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

import Model.BuildingDTO;

// do bị tách ra từ BuildingService nên dùng component 
// Component : ~ giúp đánh dấu là 1 Bean nên sẽ Autowired 
@Component
public class BuildingDTOConverter {
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
//		BuildingDTO buildingDTO = new BuildingDTO();
//		buildingDTO.setName(item.getName());
		BuildingDTO buildingDTO = modelMapper.map(item, BuildingDTO.class);
		// in ra quận : autowired tới repo 
		DistrictEntity districtEntity = districtRepository.findNameById(item.getDistrictId());
		
		// rent area: tuong tu nhu tren (dựa vào repo)
		List<RentAreaEntity> rentAreas = rentAreaRepository.getValueByBuildingId(item.getDistrictId());
		// do nó đang ở dạng List nên muốn chuyển sang xâu thì : dùng Java Stream 
		// Java Stream (Java 8): đơn giản là giúp mình duyệt 
		//Map method
		// giải thích : chuyển các it ban đầu thành String sau đó gom lại thành 1 string và giãn cách bởi dấu ,
		String areaResult = rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		buildingDTO.setRentarea(areaResult);// add vào buildingDTO 
		
		
		
		// bước filter
		buildingDTO.setAddress(item.getStreet() + ", " + item.getWard() + ", " + districtEntity.getName()); 
		
		// để không phải set nhiều Field  những thằng k cần filter( trùng tên, trùng kiểu dữ liệu)
		// dùng modelmapper :thêm vào pom.xml : maven repository 
		// sau đó config đối tượng ModelMapperConfig 
		
		
		
		return buildingDTO;
		
	}
}
