package in.jewelx.jewelxbackend.repository;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import in.jewelx.jewelxbackend.entity.AccountingEntity;

public interface AccountingRepository extends JpaRepository<AccountingEntity, Long> {

	//FOR finding closing balance of last transaction
	@Query("SELECT ae.closingBalance FROM AccountingEntity ae ORDER BY ae.createdOn DESC LIMIT 1")
    BigDecimal findClosingBalance();

	AccountingEntity findByAccountingId(UUID accountingId);
}
