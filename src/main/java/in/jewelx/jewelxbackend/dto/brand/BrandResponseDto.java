package in.jewelx.jewelxbackend.dto.brand;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandResponseDto {
	private Long brandId;
	private String name;
	private String description;
	private String imageUrl;
}
