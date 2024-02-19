package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.customer.CustomerDto;
import in.jewelx.jewelxbackend.dto.customer.CustomerResponseDto;
import in.jewelx.jewelxbackend.dto.customer.CustomerShortDto;
import in.jewelx.jewelxbackend.entity.CustomerEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;

public class CustomerMapper {

	public static CustomerEntity dtoToCustomerEntity(CustomerDto customerDto) {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setName(customerDto.getName());
		customerEntity.setAddress(customerDto.getAddress());
		customerEntity.setAadharId(customerDto.getAadharId());
		customerEntity.setAnniversaryDate(customerDto.getAnniversaryDate());
		customerEntity.setDateOfBirth(customerDto.getDateOfBirth());
		customerEntity.setMobileNumber(customerDto.getMobileNumber());
		customerEntity.setOpeningBalance(customerDto.getOpeningBalance());
		customerEntity.setPanId(customerDto.getPanId());
		customerEntity.setPincode(customerDto.getPincode());

		UserEntity userEntity = new UserEntity();
		userEntity.setIdxId(customerDto.getUserID());
		customerEntity.setCreatedBy(userEntity);
		customerEntity.setUpdatedBy(userEntity);
		return customerEntity;
	}

	public static CustomerResponseDto customerEntityToCustomerRespDto(CustomerEntity customerEntity) {
		CustomerResponseDto customerRespDto = new CustomerResponseDto();
		customerRespDto.setIdx_id(customerEntity.getIdxId());
		customerRespDto.setCustomerID(customerEntity.getCustomerId());
		customerRespDto.setName(customerEntity.getName());
		customerRespDto.setAadharId(customerEntity.getAadharId());
		customerRespDto.setAddress(customerEntity.getAddress());
		customerRespDto.setAnniversaryDate(customerEntity.getAnniversaryDate());
		customerRespDto.setCreatedOn(customerEntity.getCreatedOn());
		customerRespDto.setDateOfBirth(customerEntity.getDateOfBirth());
		customerRespDto.setMobileNumber(customerEntity.getMobileNumber());
		customerRespDto.setOpeningBalance(customerEntity.getOpeningBalance());
		customerRespDto.setPanId(customerEntity.getPanId());
		customerRespDto.setPincode(customerEntity.getPincode());
		customerRespDto.setUpdatedOn(customerEntity.getUpdatedOn());
		return customerRespDto;
	}
	
	public static CustomerShortDto entityToCustomerShortDto(CustomerEntity entity) {
		CustomerShortDto dto = new CustomerShortDto();
		dto.setIdx_id(entity.getIdxId());
		dto.setName(entity.getName());
		dto.setMobileNumber(entity.getMobileNumber());
		return dto;
	}
}
