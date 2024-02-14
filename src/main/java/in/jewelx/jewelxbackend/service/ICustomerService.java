package in.jewelx.jewelxbackend.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.UUID;

import in.jewelx.jewelxbackend.dto.customer.CustomerDto;
import in.jewelx.jewelxbackend.dto.customer.CustomerResponseDto;

public interface ICustomerService {

	String createCustomer(CustomerDto CustomerDto) throws SQLIntegrityConstraintViolationException;

	List<CustomerResponseDto> getAllCustomers();

	String deleteCustomerById(UUID id);

	CustomerResponseDto getOneCustomer(UUID id);

	String updateCustomer(UUID id, CustomerDto customerDto);
}
