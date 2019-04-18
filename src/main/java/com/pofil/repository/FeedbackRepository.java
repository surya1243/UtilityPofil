package com.pofil.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pofil.model.Feedback;

public interface FeedbackRepository extends MongoRepository<Feedback, String> {
	List<Feedback> findByDate();

	Feedback findByAccountNumber(String accountNumber);

	Feedback findDistinctByAccountNumber(String accountNumber);

	List<Feedback> findByDateBetween(String a, String b);
}
