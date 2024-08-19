package com.surferton.server.controller;

import com.surferton.server.domain.Review;
import com.surferton.server.dto.response.ReviewSummaryResponse;
import com.surferton.server.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewDetail(@PathVariable Long reviewId) {
        Review review = reviewService.findReviewById(reviewId);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/summaries")
    public ResponseEntity<List<ReviewSummaryResponse>> getReviewSummaries() {
        List<ReviewSummaryResponse> reviewSummaries = reviewService.getReviewSummaries();
        return ResponseEntity.ok(reviewSummaries);
    }
}
