package in.jewelx.jewelxbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.jewelx.jewelxbackend.entity.ArticleStockEntity;

public interface ArticleStockRepository extends JpaRepository<ArticleStockEntity, Long> {

}
