package in.jewelx.jewelxbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.jewelx.jewelxbackend.entity.ItemCategoryEntity;

public interface ItemCategoryRepository extends JpaRepository<ItemCategoryEntity, Short> {

}
