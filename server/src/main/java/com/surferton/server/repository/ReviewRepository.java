package com.surferton.server.repository;


import com.surferton.server.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.major = :major AND r.part = :part AND r.gpa BETWEEN :minGpa AND :maxGpa")
    List<Review> findSimilarReviews(
            @Param("major") String major,
            @Param("part") String part,
            @Param("minGpa") double minGpa,
            @Param("maxGpa") double maxGpa);
}
