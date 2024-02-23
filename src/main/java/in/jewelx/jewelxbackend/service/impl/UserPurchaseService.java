package in.jewelx.jewelxbackend.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.accounting.AccountingDto;
import in.jewelx.jewelxbackend.dto.metalstock.MetalStockDto;
import in.jewelx.jewelxbackend.dto.userpurchase.UserPurchaseDto;
import in.jewelx.jewelxbackend.dto.userpurchase.UserPurchaseResponseDto;
import in.jewelx.jewelxbackend.entity.AccountingEntity;
import in.jewelx.jewelxbackend.entity.MetalStockEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.entity.UserPurchaseEntity;
import in.jewelx.jewelxbackend.exception.NullObjectException;
import in.jewelx.jewelxbackend.mapper.UserPurchaseMapper;
import in.jewelx.jewelxbackend.repository.UserPurchaseRepository;
import in.jewelx.jewelxbackend.service.IUserPurchaseService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserPurchaseService implements IUserPurchaseService {

	@Autowired
	UserPurchaseRepository userPurchaseRepo;

	@Autowired
	MetalStockService metalStockService;

	@Autowired
	AccountingService accountingService;

	public String addUserPurchase(UserPurchaseDto dto) {
		if (dto != null) {
			UserPurchaseEntity userPurchase = UserPurchaseMapper.dtoToEntity(dto);

			MetalStockEntity metalStock = metalStockService.addMetalStock(new MetalStockDto(dto.getNetWeight(),
					dto.getUserId(), dto.getMetalId(), dto.getBrandId(), dto.getSubsidiaryId()));
			userPurchase.setMetalStock(metalStock);

			// Amount should recieve using '-' sign from frontend
			AccountingEntity accounting = accountingService
					.addAccounting(
							new AccountingDto(dto.getTransactionType(),
									"Transaction on User Purchase items : " + dto.getArticleDescription(),
									dto.getTransactionMode(), dto.getChequeNo(),
									((dto.getChequeAmount() == null) ? new BigDecimal("0")
											: dto.getChequeAmount().multiply(new BigDecimal("-1"))),
									dto.getCashAmount() == null ? new BigDecimal("0")
											: dto.getCashAmount().multiply(new BigDecimal("-1")),
									dto.getNetBankingUTR(),
									dto.getNetBankingAmount() == null ? new BigDecimal("0")
											: dto.getNetBankingAmount().multiply(new BigDecimal("-1")),
									dto.getUserId(), dto.getBrandId(), dto.getSubsidiaryId()));
			userPurchase.setAccounting(accounting);

			UserEntity user = new UserEntity(dto.getUserId());
			userPurchase.setCreatedBy(user);
			userPurchase.setUpdatedBy(user);

			userPurchaseRepo.save(userPurchase);
			return "User Purchase Successfully Completed !!!";
		} else
			throw new NullObjectException("UserPurchase Dto is Empty");

	}

	public Page<UserPurchaseResponseDto> getAllUserPurchase(int pageNumber, int pageSize, Long brandId,
			Long subsidiaryId, String role) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<UserPurchaseEntity> allUserPurchase = null;
		if (role.equals("O")) {
			allUserPurchase = userPurchaseRepo.findByBrand_BrandId(brandId, pageRequest);
		} else if (role.equals("A") || role.equals("E")) {
			allUserPurchase = userPurchaseRepo.findBySubsidiary_IdxId(subsidiaryId, pageRequest);
		}
		return allUserPurchase.map(UserPurchaseMapper::entityToDto);
	}

}
