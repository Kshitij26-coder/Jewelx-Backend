package in.jewelx.jewelxbackend.service.impl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.customer.CustomerDto;
import in.jewelx.jewelxbackend.dto.customer.CustomerResponseDto;
import in.jewelx.jewelxbackend.entity.CustomerEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.exception.NullObjectException;
import in.jewelx.jewelxbackend.mapper.CustomerMapper;
import in.jewelx.jewelxbackend.repository.CustomerRepository;
import in.jewelx.jewelxbackend.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	CustomerRepository customerRepo;

	@Override
	public String createCustomer(CustomerDto customerDto) throws SQLIntegrityConstraintViolationException{
		if (customerDto == null) {
			throw new NullObjectException("CustomerDto is null");
		}
		customerRepo.save(CustomerMapper.dtoToCustomerEntity(customerDto));
		return "Customer Created Successfully";
	}

	@Override
	public List<CustomerResponseDto> getAllCustomers() {
		List<CustomerEntity> allCustomers = customerRepo.findAll();
		return allCustomers.stream().map(CustomerMapper::customerEntityToCustomerRespDto).collect(Collectors.toList());
	}

	@Override
	public String deleteCustomerById(UUID id) {
		getOneCustomer(id);
		customerRepo.deleteById(id);
		return "Customer deleted Successfully";
	}

	@Override
	public CustomerResponseDto getOneCustomer(UUID id) {
		CustomerEntity foundCutomer = customerRepo.findById(id)
				.orElseThrow(() -> new IdNotFoundException("Invalid id"));
		return CustomerMapper.customerEntityToCustomerRespDto(foundCutomer);
	}

	@Override
	public String updateCustomer(UUID id, CustomerDto customerDto) {
		CustomerEntity updatedCustomer = CustomerMapper.dtoToCustomerEntity(customerDto);
		CustomerEntity foundCustomer = customerRepo.findById(id)
				.orElseThrow(() -> new IdNotFoundException("Invalid Id"));
		if (updatedCustomer.getAadharId() != null)
			foundCustomer.setAadharId(updatedCustomer.getAadharId());

		if (updatedCustomer.getAddress() != null)
			foundCustomer.setAddress(updatedCustomer.getAddress());

		if (updatedCustomer.getAnniversaryDate() != null)
			foundCustomer.setAnniversaryDate(updatedCustomer.getAnniversaryDate());

		if (updatedCustomer.getDateOfBirth() != null)
			foundCustomer.setDateOfBirth(updatedCustomer.getDateOfBirth());

		if (updatedCustomer.getMobileNumber() != null)
			foundCustomer.setMobileNumber(updatedCustomer.getMobileNumber());

		if (updatedCustomer.getName() != null)
			foundCustomer.setName(updatedCustomer.getName());

		if (updatedCustomer.getOpeningBalance() != null)
			foundCustomer.setOpeningBalance(updatedCustomer.getOpeningBalance());

		if (updatedCustomer.getPanId() != null)
			foundCustomer.setPanId(updatedCustomer.getPanId());

		if (updatedCustomer.getPincode() != null)
			foundCustomer.setPincode(updatedCustomer.getPincode());

		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(customerDto.getUserID());
		foundCustomer.setUpdatedBy(userEntity);
		customerRepo.save(foundCustomer);
		return "Customer updated Successfully";
	}

}
