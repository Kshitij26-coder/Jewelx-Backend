package in.jewelx.jewelxbackend.dto.brand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandShortDetailsDto {
	private Long brandId;
	private String name;
	private String imageUrl;
}
