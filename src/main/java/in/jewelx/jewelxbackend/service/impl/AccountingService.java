package in.jewelx.jewelxbackend.service.impl;

import java.math.BigDecimal;
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
			//System.out.println(dto.getUserId());
			entity.setCreatedBy(userEntity);
			entity.setUpdatedBy(userEntity);
			//System.out.println(entity.getUpdatedBy());
			BrandEntity brand = new BrandEntity(dto.getBrandId());
			entity.setBrand(brand);
			AccountingEntity savedEntity = accountingRepo.save(entity);
			return savedEntity;
		}
	}

	@Override
	public Page<AccountRespDto> getAllAccountings(int pageNumber, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<AccountingEntity> allAccountings = accountingRepo.findAll(pageRequest);
		return allAccountings.map(AccountingMapper::entityToDto);
	}
	@Override
	public AccountRespDto getAccountingByUUID(UUID id) {
		AccountingEntity entity = accountingRepo.findByAccountingId(id);
		AccountRespDto dto = AccountingMapper.entityToDto(entity);
		return dto;
	}

}
