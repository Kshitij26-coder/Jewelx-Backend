package in.jewelx.jewelxbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.jewelx.jewelxbackend.entity.ArticleStockEntity;

public interface ArticleStockRepository extends JpaRepository<ArticleStockEntity, Long> {
	
	   @Query(value = "SELECT * FROM article_stock WHERE huid = :huid", nativeQuery = true)
    ArticleStockEntity findByHuid(String huid);
}
