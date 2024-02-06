package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.UserDto;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.SubsidiaryEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;

public class UserMapper {
	public static ViewBrandUser mapToBrandUser(UserDto userDto) {
		ViewBrandUser viewBrandUser = new ViewBrandUser() ;
		SubsidiaryEntity subsidiaryEntity = new SubsidiaryEntity();
		subsidiaryEntity.setSubsidiaryId(userDto.getSubsidiaryId());
		
		UserEntity assignedByUser = new UserEntity();
		assignedByUser.setUserId(userDto.getAssignedBy());
		
		viewBrandUser.setBrand(new BrandEntity(
				userDto.getBrandName(), 
				userDto.getBrandDescription(),
				 userDto.getBrandImageUrl()));
		
		viewBrandUser.setUser(new UserEntity(
				userDto.getUserName(),
				userDto.getEmail(),
				userDto.getMobileNumber(),
				userDto.getPassword(),
				userDto.getUserRole(),
				subsidiaryEntity,assignedByUser));
		return  viewBrandUser;
	}
	
}


