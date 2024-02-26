package in.jewelx.jewelxbackend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.jewelx.jewelxbackend.entity.ArticleStockEntity;

public interface ArticleStockRepository extends JpaRepository<ArticleStockEntity, Long> {

	@Query(value = "SELECT * FROM article_stock WHERE huid = :huid", nativeQuery = true)
	ArticleStockEntity findByHuid(String huid);

	@Modifying
	@Query(value = "UPDATE article_stock SET article_status = :newStatus WHERE tag_id = :tagId", nativeQuery = true)
	void updateArticleStatusByTagId(Long tagId, String newStatus);

	Page<ArticleStockEntity> findByBrand_BrandId(Long id, Pageable page);

	Page<ArticleStockEntity> findBySubsidiary_IdxId(Long id, Pageable page);

	List<ArticleStockEntity> findByBrand_BrandId(Long id);
}
