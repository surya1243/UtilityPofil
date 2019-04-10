package com.pofil.service;

import com.pofil.model.Feedback;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

public interface FeedbackDetailService {

    Optional<Feedback> getFeedById(String id);

    Feedback getFeedbackByClientCode(String clientCode);

    List<Feedback> findByDate(Sort sort);

    Feedback saveFeedback(Feedback feedback);
}
