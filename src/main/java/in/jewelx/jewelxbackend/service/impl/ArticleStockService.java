package in.jewelx.jewelxbackend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.articlestock.ArticleStockDto;
import in.jewelx.jewelxbackend.dto.articlestock.ArticleStockRespDto;
import in.jewelx.jewelxbackend.entity.ArticleStockEntity;
import in.jewelx.jewelxbackend.entity.ItemCategoryEntity;
import in.jewelx.jewelxbackend.entity.SubsidiaryEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.exception.NullObjectException;
import in.jewelx.jewelxbackend.mapper.ArticleStockMapper;
import in.jewelx.jewelxbackend.repository.ArticleStockRepository;
import in.jewelx.jewelxbackend.service.IArticleStockService;

@Service
public class ArticleStockService implements IArticleStockService{
	
	@Autowired
	ArticleStockRepository articleStockRepo;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public String addArticleStock(ArticleStockDto articleStockDto) {
		if(articleStockDto == null) {
			throw new NullObjectException("Article Stock Dto is null");
		}
		else {
			ArticleStockEntity entity = ArticleStockMapper.dtoToEntity(articleStockDto);
			UserEntity userEntity = new UserEntity();
			userEntity.setIdxId(articleStockDto.getUserIdx());
			entity.setCreatedBy(userEntity);
			entity.setUpdatedBy(userEntity);
			articleStockRepo.save(entity);
			return "Article Stock Successfully Added !!!";
		}
	}

	@Override
	public String deleteByArticleStockId(Long articleStockId) {
		getArticleStockById(articleStockId);
		articleStockRepo.deleteById(articleStockId);
		return "Article Stock Deleted Successfully !!!";
	}

	@Override
	public ArticleStockRespDto getArticleStockById(Long articleStockId) {
		ArticleStockEntity entity = this.getArticleStockByIdHelper(articleStockId);
		return ArticleStockMapper.entityToDto(entity);
	}
	
	//Helper function
	public ArticleStockEntity getArticleStockByIdHelper(Long id) {
		return articleStockRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Invalid Id"));
	}

	@Override
	public String updateArticleStockById(Long id, ArticleStockDto articleStockDto) {
		ArticleStockEntity existingEntity = this.getArticleStockByIdHelper(id);
		
		existingEntity.setBarcode(articleStockDto.getBarcode());
		existingEntity.setGrossWeight(articleStockDto.getGrossWeight());
		existingEntity.setHuid(articleStockDto.getHuid());
		existingEntity.setNetWeight(articleStockDto.getNetWeight());
		existingEntity.setPurity(articleStockDto.getPurity());
		existingEntity.setStoneWeight(articleStockDto.getStoneWeight());
		
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(null);
		userEntity.setIdxId(articleStockDto.getUserIdx());
		existingEntity.setUpdatedBy(userEntity);
		
		SubsidiaryEntity subsidaryEntity = new SubsidiaryEntity();
		subsidaryEntity.setSubsidiaryId(null);
		subsidaryEntity.setIdxId(articleStockDto.getSubsidiaryId());
		existingEntity.setSubsidiary(subsidaryEntity);
		
		
		ItemCategoryEntity itemCategoryEntity = new ItemCategoryEntity();
		itemCategoryEntity.setCategoryId(articleStockDto.getCategory());
		existingEntity.setCategory(itemCategoryEntity);
		
		modelMapper.map(existingEntity, articleStockDto);
		articleStockRepo.save(existingEntity);
		return "Article Stock Updated successfully";
	}

	@Override
	public Page<ArticleStockRespDto> getAllArticleStocks(int pageNumber, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<ArticleStockEntity> allArticleStocks  = articleStockRepo.findAll(pageRequest);
		return allArticleStocks.map(ArticleStockMapper::entityToDto);
	}
	
}
