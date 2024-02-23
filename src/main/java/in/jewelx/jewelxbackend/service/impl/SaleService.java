package in.jewelx.jewelxbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.accounting.AccountingDto;
import in.jewelx.jewelxbackend.dto.itemsale.ItemSaleDto;
import in.jewelx.jewelxbackend.dto.sale.SaleDto;
import in.jewelx.jewelxbackend.entity.SaleEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.exception.NullObjectException;
import in.jewelx.jewelxbackend.mapper.SaleMapper;
import in.jewelx.jewelxbackend.repository.SaleRepository;
import in.jewelx.jewelxbackend.service.ISaleService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SaleService implements ISaleService {

	@Autowired
	private SaleRepository saleRepo;

	@Autowired
	private ArticleStockService articleStockService;

	@Autowired
	private ItemSaleService itemSaleService;

	@Autowired
	private AccountingService accountingService;

	public String addSale(SaleDto dto) {
		if (dto != null) {
			AccountingDto accountingDto = new AccountingDto(dto.getTransactionType(), dto.getTransactionDescription(),
					dto.getTransactionMode(), dto.getChequeNo(), dto.getChequeAmount(), dto.getCashAmount(),
					dto.getNetBankingUTR(), dto.getNetBankingAmount(), dto.getUserId(), dto.getBrandId(),
					dto.getSubsidiaryId());

			accountingService.addAccounting(accountingDto);

			SaleEntity saleEntity = SaleMapper.dtoToEntity(dto);

			UserEntity user = new UserEntity(dto.getUserId());
			saleEntity.setCreatedBy(user);
			saleEntity.setUpdatedBy(user);

			SaleEntity addedSaleEntity = saleRepo.save(saleEntity);

			for (ItemSaleDto eachItemDto : dto.getSaleItems()) {
				articleStockService.updatedArtifactStatus(eachItemDto.getTagId(), "sold");
				// need to add sale id in saleItem Dto
				eachItemDto.setSaleId(addedSaleEntity.getIdxId());
				itemSaleService.addItemSale(eachItemDto);
			}
			return "Sale Successfully Added !!!";
		} else {
			throw new NullObjectException("Sale Dto is Null");
		}
	}
}
