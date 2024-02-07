package in.jewelx.jewelxbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.jewelx.jewelxbackend.dto.LoginDto;
import in.jewelx.jewelxbackend.dto.UserDto;
import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.mapper.UserMapper;
import in.jewelx.jewelxbackend.mapper.ViewBrandUser;
import in.jewelx.jewelxbackend.repository.UserRepository;
import in.jewelx.jewelxbackend.service.IUserService;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BrandService brandService;
	
	@Override
	public String createUser(UserDto userDto) {
		ViewBrandUser viewBrandUser = UserMapper.mapToBrandUser(userDto);
		BrandEntity createdBrand = brandService.createBrand(viewBrandUser.getBrand());
		
		if(viewBrandUser.getUser() != null) {
			viewBrandUser.getUser().setBrand(createdBrand);	
			
			if(viewBrandUser.getUser().getAssignedBy().getUserName()==null) {
				viewBrandUser.getUser().setAssignedBy(null);
			}
			
			if(viewBrandUser.getUser().getSubsidiary().getSubsidiaryId()==null) {
				viewBrandUser.getUser().setSubsidiary(null);
			}
			
			 userRepo.save(viewBrandUser.getUser());
			 return "Succesfully saved user data";
			}
		throw new NullPointerException("User Entity does not contains any data");
	}

	@Override
	public String createLogin(LoginDto loginDto) {
		
		return null;
	}
}
