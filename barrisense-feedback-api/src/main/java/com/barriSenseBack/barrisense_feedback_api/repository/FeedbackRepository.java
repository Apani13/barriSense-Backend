package com.barriSenseBack.barrisense_feedback_api.repository;


import com.barriSenseBack.barrisense_feedback_api.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
}
