package in.jewelx.jewelxbackend.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.accounting.AccountingDto;
import in.jewelx.jewelxbackend.dto.itemsale.ItemSaleDto;
import in.jewelx.jewelxbackend.dto.itemsale.ItemSaleResponse;
import in.jewelx.jewelxbackend.dto.sale.SaleDto;
import in.jewelx.jewelxbackend.dto.sale.SaleResponseById;
import in.jewelx.jewelxbackend.dto.sale.SaleResponseDto;
import in.jewelx.jewelxbackend.entity.AccountingEntity;
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

	@Override
	public String addSale(SaleDto dto) {
		if (dto != null) {
			AccountingDto accountingDto = new AccountingDto(dto.getTransactionType(), dto.getTransactionDescription(),
					dto.getTransactionMode(), dto.getChequeNo(), dto.getChequeAmount(), dto.getCashAmount(),
					dto.getNetBankingUTR(), dto.getNetBankingAmount(), dto.getUserId(), dto.getBrandId(),
					dto.getSubsidiaryId());

			AccountingEntity account = accountingService.addAccounting(accountingDto);

			SaleEntity saleEntity = SaleMapper.dtoToEntity(dto);
			saleEntity.setAccounting(account);
			UserEntity user = new UserEntity(dto.getUserId());
			saleEntity.setCreatedBy(user);
			saleEntity.setUpdatedBy(user);

			SaleEntity addedSaleEntity = saleRepo.save(saleEntity);
			System.out.println(dto.getSaleItems() + "==========================");
			for (ItemSaleDto eachItemDto : dto.getSaleItems()) {
				// set article stock stauts to sold
				articleStockService.updatedArtifactStatus(eachItemDto.getTagId(), "sold");
				// need to add sale id in saleItem Dto
				eachItemDto.setSaleId(addedSaleEntity.getIdxId());
				eachItemDto.setUserId(dto.getUserId());
				itemSaleService.addItemSale(eachItemDto);
			}
			return "Sale Successfully Added !!!";
		} else {
			throw new NullObjectException("Sale Dto is Null");
		}
	}

	// page, size, role, brand, subsidiaryId
	@Override
	public Page<SaleResponseDto> getAllSalesByUser(int pageNumber, int pageSize, String role, Long brandId,
			Long subsidiaryId) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<SaleEntity> allSales = null;
		System.out.println("req reached");
		if (role.equals("O")) {
			// System.out.println(brandId);
			allSales = saleRepo.findByBrand_BrandId(brandId, pageRequest);
		} else if (role.equals("A") || role.equals("E")) {
			// System.out.println("req reached 2");
			allSales = saleRepo.findBySubsidiary_IdxId(subsidiaryId, pageRequest);
		}
		// System.out.println("req reached 3");
		return allSales.map(SaleMapper::entityToDto);

	}

	@Override
	public SaleResponseById getSaleByUUID(UUID saleId) {
		SaleEntity findEntity = saleRepo.findBySaleId(saleId);
		List<ItemSaleResponse> allItemsBySaleId = itemSaleService.getItemSaleBySaleId(findEntity.getIdxId());
		SaleResponseById dto = SaleMapper.entityToResponseDtoByUUID(findEntity);
		dto.setItemSaleList(allItemsBySaleId);
		return dto;

	}
}
