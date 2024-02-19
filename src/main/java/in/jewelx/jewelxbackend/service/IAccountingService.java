package in.jewelx.jewelxbackend.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import in.jewelx.jewelxbackend.dto.accounting.AccountRespDto;
import in.jewelx.jewelxbackend.dto.accounting.AccountingDto;
import in.jewelx.jewelxbackend.entity.AccountingEntity;

public interface IAccountingService {

	AccountingEntity addAccounting(AccountingDto dto);

	// for transaction
	Page<AccountRespDto> getAllAccountings(int pageNumber, int pageSize);

	AccountRespDto getAccountingByUUID(UUID id);
}
