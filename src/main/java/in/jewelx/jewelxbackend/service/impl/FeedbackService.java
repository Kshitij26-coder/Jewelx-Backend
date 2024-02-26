package in.jewelx.jewelxbackend.service.impl;

import in.jewelx.jewelxbackend.mapper.FeedbackMapper;
import in.jewelx.jewelxbackend.dto.feedback.FeedbackDto;
import in.jewelx.jewelxbackend.dto.feedback.FeedbackRespDto;
import in.jewelx.jewelxbackend.entity.FeedbackEntity;
import in.jewelx.jewelxbackend.repository.FeedbackRepository;
import in.jewelx.jewelxbackend.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService implements IFeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public FeedbackRespDto submitFeedback(FeedbackDto feedbackDto) {
        FeedbackEntity feedbackEntity = FeedbackMapper.dtoToEntity(feedbackDto);
        feedbackEntity = feedbackRepository.save(feedbackEntity);
       // return "Feedback submited";
        return FeedbackMapper.entityToDto(feedbackEntity);
    }
}
