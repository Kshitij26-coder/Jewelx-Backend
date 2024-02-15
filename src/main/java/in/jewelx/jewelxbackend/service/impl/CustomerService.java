package in.jewelx.jewelxbackend.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	public String createCustomer(CustomerDto customerDto) {
		if (customerDto == null) {
			throw new NullObjectException("CustomerDto is null");
		}
		customerRepo.save(CustomerMapper.dtoToCustomerEntity(customerDto));
		return "Customer Created Successfully";
	}

	@Override
	public Page<CustomerResponseDto> getAllCustomers(int pageNumber, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<CustomerEntity> allCustomers = customerRepo.findAll(pageRequest);
		return allCustomers.map(CustomerMapper::customerEntityToCustomerRespDto);
	}

	@Override
	public String deleteCustomerById(UUID id) {
		getOneCustomer(id);
		customerRepo.deleteByCustomerId(id);
		return "Customer deleted Successfully";
	}

	@Override
	public CustomerResponseDto getOneCustomer(UUID id) {
		CustomerEntity foundCutomer = customerRepo.findByCustomerId(id)
				.orElseThrow(() -> new IdNotFoundException("Invalid Customer id"));
		return CustomerMapper.customerEntityToCustomerRespDto(foundCutomer);
	}

	@Override
	public String updateCustomer(UUID id, CustomerDto customerDto) {
		CustomerEntity updatedCustomer = CustomerMapper.dtoToCustomerEntity(customerDto);
		CustomerEntity foundCustomer = customerRepo.findByCustomerId(id)
				.orElseThrow(() -> new IdNotFoundException("Invalid Id"));

		foundCustomer.setAadharId(updatedCustomer.getAadharId());

		foundCustomer.setAddress(updatedCustomer.getAddress());

		foundCustomer.setAnniversaryDate(updatedCustomer.getAnniversaryDate());

		foundCustomer.setDateOfBirth(updatedCustomer.getDateOfBirth());

		foundCustomer.setMobileNumber(updatedCustomer.getMobileNumber());

		foundCustomer.setName(updatedCustomer.getName());

		foundCustomer.setOpeningBalance(updatedCustomer.getOpeningBalance());

		foundCustomer.setPanId(updatedCustomer.getPanId());

		foundCustomer.setPincode(updatedCustomer.getPincode());

		UserEntity userEntity = new UserEntity();
		userEntity.setIdxId(customerDto.getUserID());
		foundCustomer.setUpdatedBy(userEntity);
		customerRepo.save(foundCustomer);
		return "Customer updated Successfully";
	}
}
