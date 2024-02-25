package in.jewelx.jewelxbackend.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import in.jewelx.jewelxbackend.entity.AccountingEntity;

public interface AccountingRepository extends JpaRepository<AccountingEntity, Long> {

	// FOR finding closing balance of last transaction
	@Query("SELECT ae.closingBalance FROM AccountingEntity ae ORDER BY ae.createdOn DESC LIMIT 1")
	BigDecimal findClosingBalance();

	AccountingEntity findByAccountingId(UUID accountingId);

	// Method to find daily transactions by transaction date and brandId
	List<AccountingEntity> findByTransactionDateAndBrand_BrandId(Date transactionDate, Long brandId);

	List<AccountingEntity> findByTransactionDateAndSubsidiary_IdxId(Date transactionDate, Long brandId);

	// Method to find top 5 accounting entities ordered by createdOn descending,
	// considering brandId and subsidiaryId
	List<AccountingEntity> findTop5ByBrand_BrandIdAndSubsidiary_IdxIdOrderByCreatedOnDesc(Long brandId,
			Long subsidiaryId);

//    @Query(value = "SELECT * FROM accounting WHERE transaction_date = ?1 AND brand_id = ?2", nativeQuery = true)
//    List<AccountingEntity> findByTransactionDateAndBrandId(Date transactionDate, Long brandId);

	Page<AccountingEntity> findByBrand_BrandId(Long brandId,Pageable page);
	
	Page<AccountingEntity> findBySubsidiary_IdxId(Long brandId,Pageable page);
}
