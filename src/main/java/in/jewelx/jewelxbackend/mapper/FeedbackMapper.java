package in.jewelx.jewelxbackend.mapper;

import in.jewelx.jewelxbackend.dto.feedback.FeedbackDto;
import in.jewelx.jewelxbackend.dto.feedback.FeedbackRespDto;
import in.jewelx.jewelxbackend.entity.FeedbackEntity;

public class FeedbackMapper {

    public static FeedbackEntity dtoToEntity(FeedbackDto feedbackDto) {
        FeedbackEntity entity = new FeedbackEntity();
        entity.setName(feedbackDto.getName());
        entity.setEmail(feedbackDto.getEmail());
        entity.setMobileNumber(feedbackDto.getMobileNumber());
        entity.setDescription(feedbackDto.getDescription());
        return entity;
    }

    public static FeedbackRespDto entityToDto(FeedbackEntity entity) {
    	FeedbackRespDto dto = new FeedbackRespDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setMobileNumber(entity.getMobileNumber());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}
