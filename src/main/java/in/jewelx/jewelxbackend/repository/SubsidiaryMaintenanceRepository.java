package in.jewelx.jewelxbackend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import in.jewelx.jewelxbackend.entity.SubsidiaryMaintenance;

public interface SubsidiaryMaintenanceRepository extends JpaRepository<SubsidiaryMaintenance, Long> {
	
	Page<SubsidiaryMaintenance> findByBrand_BrandId(Long id, Pageable page);

	Page<SubsidiaryMaintenance> findBySubsidiary_IdxId(Long id, Pageable page);
	
	Optional<SubsidiaryMaintenance> findByMaintenanceId(UUID id);
}
