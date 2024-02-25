package in.jewelx.jewelxbackend.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.accounting.AccountRespDto;
import in.jewelx.jewelxbackend.dto.accounting.AccountingDto;
import in.jewelx.jewelxbackend.entity.AccountingEntity;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import in.jewelx.jewelxbackend.exception.NullObjectException;
import in.jewelx.jewelxbackend.mapper.AccountingMapper;
import in.jewelx.jewelxbackend.repository.AccountingRepository;
import in.jewelx.jewelxbackend.service.IAccountingService;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class AccountingService implements IAccountingService {

	@Autowired
	private AccountingRepository accountingRepo;

	@Override
	public AccountingEntity addAccounting(AccountingDto dto) {
		if (dto == null) {
			throw new NullObjectException(" Accounting Dto is null");
		} else {
			BigDecimal lastClosingAmount = accountingRepo.findClosingBalance() == null ? new BigDecimal("0")
					: accountingRepo.findClosingBalance();

			BigDecimal transactionAmount = new BigDecimal("0");
			AccountingEntity entity = AccountingMapper.dtoToEntity(dto);
			if (dto.getChequeAmount() != null) {
				transactionAmount = transactionAmount.add(dto.getChequeAmount());
				entity.setChequeAmount(dto.getChequeAmount());
				entity.setChequeNo(dto.getChequeNo());
			}
			if (dto.getCashAmount() != null) {
				transactionAmount = transactionAmount.add(dto.getCashAmount());
				entity.setCashAmount(dto.getCashAmount());
			}
			if (dto.getNetBankingAmount() != null) {
				transactionAmount = transactionAmount.add(dto.getNetBankingAmount());
				entity.setNetBankingAmount(dto.getNetBankingAmount());
				entity.setNetBankingUTR(dto.getNetBankingUTR());
			}
			// BigDecimal transactionAmount = dto.getTransactionAmount();

			// for closing amount
			if (transactionAmount.compareTo(BigDecimal.ZERO) < 0) {
				BigDecimal closingAmount = lastClosingAmount.subtract(transactionAmount.abs());
				entity.setClosingBalance(closingAmount);
			} else {
				BigDecimal closingAmount = lastClosingAmount.add(transactionAmount.abs());
				entity.setClosingBalance(closingAmount);
			}
			entity.setTransactionAmount(transactionAmount);
			entity.setOpenigBalance(lastClosingAmount);
			UserEntity userEntity = new UserEntity();
			userEntity.setIdxId(dto.getUserId());
			// System.out.println(dto.getUserId());
			entity.setCreatedBy(userEntity);
			entity.setUpdatedBy(userEntity);
			// System.out.println(entity.getUpdatedBy());
			BrandEntity brand = new BrandEntity(dto.getBrandId());
			entity.setBrand(brand);
			AccountingEntity savedEntity = accountingRepo.save(entity);
			return savedEntity;
		}
	}

	@Override
	public Page<AccountRespDto> getAllAccountings(int pageNumber, int pageSize, Long brandId, Long subsidiaryId,
			String role) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<AccountingEntity> allAccountings = null;
		if (role.equals("O")) {
			allAccountings = accountingRepo.findByBrand_BrandId(brandId, pageRequest);
		} else if (role.equals("A") || role.equals("E")) {
			allAccountings = accountingRepo.findBySubsidiary_IdxId(subsidiaryId, pageRequest);
		}
		return allAccountings.map(AccountingMapper::entityToDto);
	}

	@Override
	public AccountRespDto getAccountingByUUID(UUID id) {
		AccountingEntity entity = accountingRepo.findByAccountingId(id);
		AccountRespDto dto = AccountingMapper.entityToDto(entity);
		return dto;
	}

	public BigDecimal dailyTransaction(String role, Long brandId, Long subsidiaryId) {
		List<AccountingEntity> accountingEntityList = null;
		BigDecimal amount = new BigDecimal("0");
		if (role.equals("O")) {
			// System.out.println(brandId);
			accountingEntityList = accountingRepo.findByTransactionDateAndBrand_BrandId(Date.valueOf(LocalDate.now()),
					brandId);
		} else if (role.equals("A") || role.equals("E")) {
			// System.out.println("req reached 2");
			accountingEntityList = accountingRepo
					.findByTransactionDateAndSubsidiary_IdxId(Date.valueOf(LocalDate.now()), subsidiaryId);
		}
		for (AccountingEntity e : accountingEntityList) {

			amount = amount.add(e.getTransactionAmount());
		}
		// System.out.println(accountingEntityList+"*******");
		return amount;
	}

	public List<Double> fiveDayTransaction(Long brandId, Long subsidiaryId) {
		List<AccountingEntity> accountingEntityList = accountingRepo
				.findTop5ByBrand_BrandIdAndSubsidiary_IdxIdOrderByCreatedOnDesc(brandId, subsidiaryId);
		List<Double> amount = new ArrayList<Double>();

		for (AccountingEntity e : accountingEntityList) {
			System.out.println(e.getTransactionAmount().toString() + "*******");
			amount.add(e.getTransactionAmount().doubleValue());
		}
		// System.out.println(accountingEntityList+"*******");
		return amount;
	}

}
