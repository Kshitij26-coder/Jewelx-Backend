package in.jewelx.jewelxbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.repository.BrandRepository;
import in.jewelx.jewelxbackend.service.IBrandService;
import jakarta.transaction.Transactional;

@Service
public class BrandService implements IBrandService {
	
	@Autowired
	private BrandRepository brandRepo;
	
	@Override
    @Transactional
	public BrandEntity createBrand(BrandEntity brandEntity) {
		
		if (brandEntity != null) {
			BrandEntity createdBrand = brandRepo.save(brandEntity);
			return createdBrand;
		}
		throw new NullPointerException("Brand Entity does not contains any data");
		
	}

}
