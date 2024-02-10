package in.jewelx.jewelxbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandResponseDto {
	private Integer brandId;
	private String name;
	private String description;
	private String imageUrl;
}
