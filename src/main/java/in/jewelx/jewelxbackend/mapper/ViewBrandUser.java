package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.entity.BrandEntity;
import in.jewelx.jewelxbackend.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ViewBrandUser {
	private BrandEntity brand;
	private UserEntity user;

}
