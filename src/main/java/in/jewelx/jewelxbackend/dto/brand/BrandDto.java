package in.jewelx.jewelxbackend.dto.brand;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandDto {
	private String name;
	private String description;
	private String imageUrl;
	private UUID userId;
}
