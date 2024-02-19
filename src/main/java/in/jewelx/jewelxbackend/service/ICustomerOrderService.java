package in.jewelx.jewelxbackend.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import in.jewelx.jewelxbackend.dto.customerorder.CustomerOrderDto;
import in.jewelx.jewelxbackend.dto.customerorder.CustomerOrderRespDto;

public interface ICustomerOrderService {
	
	String addCustomerOrder(CustomerOrderDto dto);
	
	String deleteCustomerOrderByUUID(UUID id);
	
	CustomerOrderRespDto getCustomerOrderByUUID(UUID id);
	
	Page<CustomerOrderRespDto> getAllCustomerOrder(int pageNumber, int pageSize);
	
	String updateCustomerOrderByUUID(UUID id, CustomerOrderDto dto);
	
}
