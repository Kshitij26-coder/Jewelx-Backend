package in.jewelx.jewelxbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.jewelx.jewelxbackend.dto.brand.BrandDto;
import in.jewelx.jewelxbackend.dto.brand.BrandResponseDto;
import in.jewelx.jewelxbackend.exception.IdNotFoundException;
import in.jewelx.jewelxbackend.mapper.BrandMapper;
import in.jewelx.jewelxbackend.service.impl.BrandService;

@RestController
@RequestMapping("/brand")
public class BrandController {
	@Autowired
	BrandService brandService;

	@PutMapping
	public ResponseEntity<?> createBrand(@RequestBody BrandDto brandDto) {
		try {
			brandService.createBrand(BrandMapper.dtoToBrandEntity(brandDto));
			return ResponseEntity.ok("Brand Created Successfully");
		} catch (Exception err) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
		}
	}

	@GetMapping
	public List<BrandResponseDto> getAllBrands() {
		return brandService.getAllBrands();
	}

	@GetMapping("/{brandId}")
	public ResponseEntity<?> getOneBrand(@PathVariable("brandId") Integer id) {
		try {
			return ResponseEntity.ok(brandService.getOneBrand(id));
		} catch (IdNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PutMapping("/{brandId}")
	public ResponseEntity<String> updateBrand(@PathVariable("brandId") Integer id, @RequestBody BrandDto brandDto) {
		try {
			return ResponseEntity.ok(brandService.updateBrand(id, brandDto));
		} catch (IdNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/{brandId}")
	public ResponseEntity<String> deleteUserById(@PathVariable("brandId") Integer id) {
		try {
			return ResponseEntity.ok(brandService.deleteBrandById(id));
		} catch (IdNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
