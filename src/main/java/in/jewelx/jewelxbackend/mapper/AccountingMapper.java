package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.accounting.AccountRespDto;
import in.jewelx.jewelxbackend.dto.accounting.AccountingDto;
import in.jewelx.jewelxbackend.entity.AccountingEntity;
import in.jewelx.jewelxbackend.entity.SubsidiaryEntity;

public class AccountingMapper {

	public static AccountingEntity dtoToEntity(AccountingDto dto) {
		AccountingEntity entity = new AccountingEntity();
		entity.setTransactionType(dto.getTransactionType());
		entity.setDescription(dto.getDescription());
		entity.setTransactionMode(dto.getTransactionMode());
		SubsidiaryEntity subsidiary = new SubsidiaryEntity(dto.getSubsidiaryId());
		entity.setSubsidiary(subsidiary);
		return entity;
	}

	public static AccountRespDto entityToDto(AccountingEntity entity) {
		AccountRespDto dto = new AccountRespDto(entity.getIdxId(), entity.getAccountingId(), entity.getOpenigBalance(),
				entity.getClosingBalance(), entity.getTransactionAmount(), entity.getTransactionType(),
				entity.getTransactionDate(), entity.getDescription(), entity.getTransactionMode(), entity.getChequeNo(),
				entity.getChequeAmount(), entity.getCashAmount(), entity.getNetBankingUTR(), entity.getNetBankingAmount());
		return dto;
	}
}
