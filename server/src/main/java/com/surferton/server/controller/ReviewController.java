package com.surferton.server.controller;

import com.surferton.server.domain.Review;
import com.surferton.server.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewDetail(@PathVariable Long reviewId) {
        Review review = reviewService.findReviewById(reviewId);
        return ResponseEntity.ok(review);
    }
}
