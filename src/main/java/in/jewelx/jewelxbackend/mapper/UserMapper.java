package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.brand.BrandShortDetailsDto;
import in.jewelx.jewelxbackend.dto.subsidiary.SubsidiaryShortDetailsDto;
import in.jewelx.jewelxbackend.dto.user.UserRequestDto;
import in.jewelx.jewelxbackend.dto.user.UserResponseDto;
import in.jewelx.jewelxbackend.dto.user.UserShortDetailsDto;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.SubsidiaryEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;

public class UserMapper {

	/*
	 * Used to convert userDto to ViewBrandUser object
	 */
	public static ViewBrandUser mapToBrandUser(UserRequestDto userDto) {

		// will set Brand object in user later after creating it in database
		ViewBrandUser viewBrandUser = new ViewBrandUser();

		SubsidiaryEntity subsidiaryEntity = new SubsidiaryEntity();
		subsidiaryEntity.setIdxId(userDto.getSubsidiaryId());

		BrandEntity brandEntity = new BrandEntity();
		brandEntity.setBrandId(userDto.getBrandId());

		UserEntity assignedByUser = new UserEntity();
		assignedByUser.setUserId(userDto.getAssignedBy());

		viewBrandUser.setBrand(
				new BrandEntity(userDto.getBrandName(), userDto.getBrandDescription(), userDto.getBrandImageUrl()));

		viewBrandUser.setUser(new UserEntity(userDto.getUserName(), userDto.getEmail(), userDto.getMobileNumber(),
				userDto.getPassword(), userDto.getUserRole(), subsidiaryEntity, assignedByUser, brandEntity));

		return viewBrandUser;
	}

	/*
	 * Used to convert UserEntity to UserResponseDto object
	 */
	public static UserResponseDto UserToUserResponseDto(UserEntity user) {
		UserResponseDto userDto = new UserResponseDto();

		UserShortDetailsDto userAssignedByDto = UserMapper.UserEntityToUserShortDetailsDto(user.getAssignedBy());
		SubsidiaryShortDetailsDto userSubsidiaryDto = SubsidiaryMapper
				.mapToSubsidiaryShortDetailsDto(user.getSubsidiary());
		BrandShortDetailsDto userBrandResponseDto = BrandMapper.brandEntitytoBrandShortDetails(user.getBrand());
		userDto.setIdxId(user.getIdxId());
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

	/*
	 * Used to convert UserEntity to UserShortDetailsDto
	 */
	public static UserShortDetailsDto UserEntityToUserShortDetailsDto(UserEntity user) {
		if (user != null) {
			UserShortDetailsDto dto = new UserShortDetailsDto();
			dto.setIdxId(user.getIdxId());
			dto.setUserId(user.getUserId());
			dto.setUsername(user.getUserName());
			dto.setEmail(user.getEmail());
			return dto;
		} else {
			return null; // Or handle the case where the user is null
		}
	}

}
