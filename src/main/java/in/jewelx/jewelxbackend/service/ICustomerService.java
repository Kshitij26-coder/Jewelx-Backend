package in.jewelx.jewelxbackend.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import in.jewelx.jewelxbackend.dto.customer.CustomerDto;
import in.jewelx.jewelxbackend.dto.customer.CustomerResponseDto;

public interface ICustomerService {

	String createCustomer(CustomerDto customerDto);

	String deleteCustomerById(UUID id);

	CustomerResponseDto getOneCustomer(UUID id);

	String updateCustomer(UUID id, CustomerDto customerDto);

	Page<CustomerResponseDto> getAllCustomers(int pageNumber, int pageSize, Long brand, Long subsidiary, String role);
}
