package in.jewelx.jewelxbackend.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.customerorder.CustomerOrderDto;
import in.jewelx.jewelxbackend.dto.customerorder.CustomerOrderRespDto;
import in.jewelx.jewelxbackend.entity.AccountingEntity;
import in.jewelx.jewelxbackend.entity.CustomerOrderEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.exception.NullObjectException;
import in.jewelx.jewelxbackend.mapper.CustomerOrderMapper;
import in.jewelx.jewelxbackend.repository.CustomerOrderRepository;
import in.jewelx.jewelxbackend.service.ICustomerOrderService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerOrderService implements ICustomerOrderService {

	@Autowired
	private CustomerOrderRepository customerOrderRepo;

	@Autowired
	private AccountingService accountingService;

	@Autowired
	private MetalStockService metalStockService;

	@Autowired
	private WeightDetailService weightDetailsService;

	@Override
	public String addCustomerOrder(CustomerOrderDto dto) {
		if (dto == null) {
			throw new NullObjectException("Customer Order Dto is null");
		} else {
			CustomerOrderEntity entity = CustomerOrderMapper.dtoToEntity(dto);

			// for accounting entity

			AccountingEntity accountingEntity = accountingService
					.addAccounting(CustomerOrderMapper.customerOrderDtoToAccountingDto(dto));

			// for metal stock entity
			metalStockService.addMetalStock(CustomerOrderMapper.customerOrderDtoToMetalStockDto(dto));

			// for weight details entity
			weightDetailsService.addWeightDetail(CustomerOrderMapper.cutomerOrderDtoToWeightDetailsDto(dto));

			UserEntity userEntity = new UserEntity();
			userEntity.setIdxId(dto.getUserID());

			entity.setCreatedBy(userEntity);
			entity.setUpdatedBy(userEntity);

			// setting accounting id in customer order entity
			entity.setAccounting(accountingEntity);
			customerOrderRepo.save(entity);
			return "Added Customer Order successfully !!!";
		}

	}

	@Override
	public String deleteCustomerOrderByUUID(UUID id) {
		getCustomerOrderByUUID(id);
		customerOrderRepo.deleteByOrderId(id);
		return "Customer Order Deleted Successfully";
	}

	@Override
	public CustomerOrderRespDto getCustomerOrderByUUID(UUID id) {
		CustomerOrderEntity customerOrder = customerOrderRepo.findByOrderId(id)
				.orElseThrow(() -> new IdNotFoundException("Invalid UUID"));
		return CustomerOrderMapper.entityToDto(customerOrder);
	}

	@Override
	public Page<CustomerOrderRespDto> getAllCustomerOrder(int pageNumber, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<CustomerOrderEntity> allCustomerOrders = customerOrderRepo.findAll(pageRequest);
		return allCustomerOrders.map(CustomerOrderMapper::entityToDto);
	}

	@Override
	public String updateCustomerOrderByUUID(UUID id, CustomerOrderDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
