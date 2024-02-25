package in.jewelx.jewelxbackend.dto.brand;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandDto {
	private String name;
	private String description;
	//private String imageUrl;
	private MultipartFile image;
	private Long userId;
}
