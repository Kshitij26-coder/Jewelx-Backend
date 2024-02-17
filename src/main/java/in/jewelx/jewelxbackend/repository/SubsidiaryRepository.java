package in.jewelx.jewelxbackend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.jewelx.jewelxbackend.entity.SubsidiaryEntity;

@Repository
public interface SubsidiaryRepository extends JpaRepository<SubsidiaryEntity, Long> {

	@Query("SELECT s FROM SubsidiaryEntity s WHERE s.brand.brandId = :brandId")
	List<SubsidiaryEntity> findByBrandId(long brandId);

	Optional<SubsidiaryEntity> findBySubsidiaryId(UUID id);

	@Modifying
	@Query("UPDATE SubsidiaryEntity s SET s.isActive = :isActive WHERE s.id = :id")
	void updateIsActiveStatus(@Param("id") Long id, @Param("isActive") boolean isActive);
}
