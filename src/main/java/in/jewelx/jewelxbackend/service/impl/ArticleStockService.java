package in.jewelx.jewelxbackend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import in.jewelx.jewelxbackend.dto.articlestock.ArticleStockDto;
import in.jewelx.jewelxbackend.dto.articlestock.ArticleStockRespDto;
import in.jewelx.jewelxbackend.entity.ArticleStockEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.exception.HuidExistedException;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.exception.NullObjectException;
import in.jewelx.jewelxbackend.mapper.ArticleStockMapper;
import in.jewelx.jewelxbackend.repository.ArticleStockRepository;
import in.jewelx.jewelxbackend.service.IArticleStockService;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class ArticleStockService implements IArticleStockService {

	@Autowired
	ArticleStockRepository articleStockRepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public String addArticleStock(ArticleStockDto articleStockDto) {
		if (articleStockDto == null) {
			throw new NullObjectException("Article Stock Dto is null");
		} else {

			if (!(articleStockDto.getHuid() == null || articleStockDto.getHuid().equals(""))) {

				ArticleStockEntity articleEntitity = articleStockRepo.findByHuid(articleStockDto.getHuid());
				if (articleEntitity != null)
					throw new HuidExistedException("HUID already present");

			}
			ArticleStockEntity entity = ArticleStockMapper.dtoToEntity(articleStockDto);
			UserEntity userEntity = new UserEntity();
			userEntity.setIdxId(articleStockDto.getUserIdx());
			entity.setCreatedBy(userEntity);
			entity.setUpdatedBy(userEntity);
			ArticleStockEntity addedEntity = articleStockRepo.save(entity);
			String text = addedEntity.getTagId().toString() + addedEntity.getCategory().getCategoryId().toString()
					+ addedEntity.getSubsidiary().getIdxId().toString()
					+ addedEntity.getBrand().getBrandId().toString();
			addedEntity.setBarcode(text);
			articleStockRepo.save(addedEntity);
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

	// Helper function
	public ArticleStockEntity getArticleStockByIdHelper(Long id) {
		return articleStockRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Invalid Id"));
	}

	@Override
	public String updateArticleStockById(Long id, ArticleStockDto articleStockDto) {

		if (!(articleStockDto.getHuid() == null || articleStockDto.getHuid().equals(""))) {

			ArticleStockEntity articleEntitity = articleStockRepo.findByHuid(articleStockDto.getHuid());

			if (articleEntitity != null && articleEntitity.getTagId() != id)
				throw new HuidExistedException("HUID already present");

		}

		ArticleStockEntity existingEntity = this.getArticleStockByIdHelper(id);

		ArticleStockEntity updatedEntity = ArticleStockMapper.dtoToEntity(articleStockDto);
		updatedEntity.setUpdatedBy(new UserEntity(articleStockDto.getUserIdx()));

		modelMapper.map(updatedEntity, existingEntity);
		articleStockRepo.save(existingEntity);
		return "Article Stock Updated successfully";
	}

	@Override
	public Page<ArticleStockRespDto> getAllArticleStocks(int pageNumber, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<ArticleStockEntity> allArticleStocks = articleStockRepo.findAll(pageRequest);
		return allArticleStocks.map(ArticleStockMapper::entityToDto);
	}
	
	@Override
	public void updatedArtifactStatus(Long tagId, String status) {
		articleStockRepo.updateArticleStatusByTagId(tagId, status);
	}

}
