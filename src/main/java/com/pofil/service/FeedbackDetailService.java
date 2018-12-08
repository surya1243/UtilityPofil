package com.pofil.service;

import com.pofil.model.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackDetailService {

    Optional<Feedback> getFeedById(String id);

    Feedback getFeedbackByClientCode(String clientCode);

    List<Feedback> getallFeedbacks();

    Feedback saveFeedback(Feedback feedback);
}
