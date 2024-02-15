package in.jewelx.jewelxbackend.service;

import java.util.UUID;
import in.jewelx.jewelxbackend.dto.weightdetails.WeightDetailsDto;
import in.jewelx.jewelxbackend.dto.weightdetails.WeightDetailsResponseDto;

import org.springframework.data.domain.Page;

public interface IWeightDetailService {

	String addWeightDetail(WeightDetailsDto weightDetailDto);

	Page<WeightDetailsResponseDto> getAllWeightDetails(int pageNumber, int pageSize);

	String deleteWeightDetailById(UUID id);

	WeightDetailsResponseDto getWeightDetailById(UUID id);

	String updateWeightDetail(UUID id, WeightDetailsDto weightDetailsDto);
}
