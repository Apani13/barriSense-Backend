package com.barriSenseBack.barrisense_feedback_api.repository;


import com.barriSenseBack.barrisense_feedback_api.entity.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {
}
