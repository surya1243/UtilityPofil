package com.pofil.repository;

import com.pofil.model.Feedback;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends MongoRepository<Feedback, String> {
	List<Feedback> findByDate();
    Feedback findByAccountNumber(String accountNumber);
    Feedback findDistinctByAccountNumber(String accountNumber);
    Feedback findByDateBetween(String a, String b);
}
