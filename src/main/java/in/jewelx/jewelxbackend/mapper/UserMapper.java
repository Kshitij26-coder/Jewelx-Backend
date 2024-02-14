package in.jewelx.jewelxbackend.mapper;

import java.util.UUID;

import in.jewelx.jewelxbackend.dto.user.UserAssignedByResponseDto;
import in.jewelx.jewelxbackend.dto.user.UserBrandResponseDto;
import in.jewelx.jewelxbackend.dto.user.UserDto;
import in.jewelx.jewelxbackend.dto.user.UserResponseDto;
import in.jewelx.jewelxbackend.dto.user.UserSubsidiaryResponseDto;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.SubsidiaryEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;

public class UserMapper {
	public static ViewBrandUser mapToBrandUser(UserDto userDto) {
		ViewBrandUser viewBrandUser = new ViewBrandUser();
		SubsidiaryEntity subsidiaryEntity = new SubsidiaryEntity();
		subsidiaryEntity.setSubsidiaryId(userDto.getSubsidiaryId());

		UserEntity assignedByUser = new UserEntity();
		assignedByUser.setUserId(userDto.getAssignedBy());

		viewBrandUser.setBrand(
				new BrandEntity(userDto.getBrandName(), userDto.getBrandDescription(), userDto.getBrandImageUrl()));

		viewBrandUser.setUser(new UserEntity(userDto.getUserName(), userDto.getEmail(), userDto.getMobileNumber(),
				userDto.getPassword(), userDto.getUserRole(), subsidiaryEntity, assignedByUser));
		return viewBrandUser;
	}

	public static UserResponseDto UserToUserResponseDto(UserEntity user) {
		UserResponseDto userDto = new UserResponseDto();
		UUID userId = null;
		String email = null;
		String userName = null;

		if (user.getAssignedBy() != null) {
			email = user.getAssignedBy().getEmail();
			userId = user.getAssignedBy().getUserId();
			userName = user.getAssignedBy().getUsername();
		}

		String subsidaryName = null;
		UUID subsidiaryId = null;

		if (user.getSubsidiary() != null) {
			subsidaryName = user.getSubsidiary().getSubsidiaryName();
			subsidiaryId = user.getSubsidiary().getSubsidiaryId();
		}

		UserAssignedByResponseDto userAssignedByDto = new UserAssignedByResponseDto(userId, userName, email);
		UserSubsidiaryResponseDto userSubsidiaryDto = new UserSubsidiaryResponseDto(subsidiaryId, subsidaryName);
		UserBrandResponseDto userBrandResponseDto = new UserBrandResponseDto(user.getBrand().getBrandId(),
				user.getBrand().getName());

		userDto.setAssignedBy(userAssignedByDto);
		userDto.setActive(user.isActive());
		userDto.setLoggedIn(user.isLoggedIn());
		userDto.setUserId(user.getUserId());
		userDto.setUserName(user.getUserName());
		userDto.setUserRole(user.getUserRole());
		userDto.setSubsidiary(userSubsidiaryDto);
		userDto.setMobileNumber(user.getMobileNumber());
		userDto.setEmail(user.getEmail());
		userDto.setBrand(userBrandResponseDto);

		return userDto;
	}

}
