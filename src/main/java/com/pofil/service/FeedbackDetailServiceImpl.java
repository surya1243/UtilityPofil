package com.pofil.service;

import com.pofil.model.Feedback;
import com.pofil.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FeedbackDetailServiceImpl implements FeedbackDetailService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FeedbackDetailService feedbackDetailService;

    @Override
    public Optional<Feedback> getFeedById(String id) {
        return feedbackDetailService.getFeedById(id);
    }

    @Override
    public Feedback getFeedbackByClientCode(String clientCode) {
        return feedbackDetailService.getFeedbackByClientCode(clientCode);
    }

    @Override
    public List<Feedback> getallFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
        return feedbackRepository.save(feedback);
    }
}
