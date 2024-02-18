package in.jewelx.jewelxbackend.service.impl;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.metalstock.MetalStockDto;
import in.jewelx.jewelxbackend.dto.metalstock.MetalStockRepoDto;
import in.jewelx.jewelxbackend.entity.MetalEntity;
import in.jewelx.jewelxbackend.entity.MetalStockEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.exception.NullObjectException;
import in.jewelx.jewelxbackend.mapper.MetalStockMapper;
import in.jewelx.jewelxbackend.repository.MetalStockRepository;
import in.jewelx.jewelxbackend.service.IMetalStockService;
import jakarta.transaction.Transactional;

@Service
public class MetalStockService implements IMetalStockService {

	@Autowired
	private MetalStockRepository metalStockRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	@Transactional
    public String addMetalStock(MetalStockDto metalStockDto) {
		
        if (metalStockDto == null) {
            throw new NullObjectException("Metal Stock Dto is null");
        } else {
           // BigDecimal openingWeight = metalStockDto.getOpeningWeight(); // Add this line
        	BigDecimal weight = metalStockRepo.findClosingWeightByMetalId(metalStockDto.getMetalId());
            System.out.println("******************************** : "+weight);
            BigDecimal transactionWeight = metalStockDto.getWeight();
            MetalStockEntity entity = MetalStockMapper.dtoToEntity(metalStockDto);

            if (transactionWeight.compareTo(BigDecimal.ZERO) < 0) {
                BigDecimal closingWeight = weight.subtract(transactionWeight.abs());
                entity.setClosingWeight(closingWeight);
            } else {
                BigDecimal closingWeight = weight.add(transactionWeight);
                entity.setClosingWeight(closingWeight);
            }
            entity.setTransactionWeight(transactionWeight);
            BigDecimal openingWeight = weight;
            entity.setOpeningWeight(openingWeight); // Set the opening weight
            UserEntity userEntity = new UserEntity();
            userEntity.setIdxId(metalStockDto.getUserId());
            entity.setCreatedBy(userEntity);
            entity.setUpdatedBy(userEntity);
            metalStockRepo.save(entity);
            return "Successfully Added Metal Stock";
        }
    }

	@Override
	public String deleteById(Long id) {
		getMetalStockById(id);
		metalStockRepo.deleteById(id);
		return "Metal Stock Deleted Successfully";
	}

	@Override
	public MetalStockRepoDto getMetalStockById(Long id) {
		MetalStockEntity entity = this.getMetalStockByIdHelper(id);
		return MetalStockMapper.entityToResponseDto(entity);
	}
	//This helper method
	public MetalStockEntity getMetalStockByIdHelper(Long id) {
		return metalStockRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Invalid Id"));
	}

	@Override
	public String updateMetalStockById(Long id, MetalStockDto metalStockDto) {
		MetalStockEntity existingEntity = this.getMetalStockByIdHelper(id);
		MetalStockEntity updatedEntity = MetalStockMapper.dtoToEntity(metalStockDto);
		// Because every time object is created it will have an UUID, we want to retain
		// the UUID which is already in DB
		UserEntity updatedBy = new UserEntity();
		updatedBy.setIdxId(metalStockDto.getUserId());
		updatedBy.setUserId(null);
		updatedEntity.setUpdatedBy(updatedBy);
	//	BigDecimal weight = metalStockRepo.findClosingWeightByMetalId(metalStockDto.getMetalId());
		existingEntity.setOpeningWeight(updatedEntity.getOpeningWeight());
		existingEntity.setClosingWeight(updatedEntity.getClosingWeight());
		existingEntity.setUpdatedBy(updatedEntity.getUpdatedBy());
		
		MetalEntity metalEntity = new MetalEntity();
		metalEntity.setMetalId(metalStockDto.getMetalId());
		updatedEntity.setMetal(metalEntity);
		existingEntity.setMetal(updatedEntity.getMetal());
		existingEntity.setTransactionWeight(metalStockDto.getWeight());
		//Model Mapper for avoiding null values
		modelMapper.map(updatedEntity, existingEntity);
		
		metalStockRepo.save(existingEntity);
		return "updated successfully";
	}

	@Override
	public Page<MetalStockRepoDto> getAllMetalStocks(int pageNumber, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<MetalStockEntity> allMetalStocks  = metalStockRepo.findAll(pageRequest);
		return allMetalStocks.map(MetalStockMapper::entityToResponseDto);
	}

}
