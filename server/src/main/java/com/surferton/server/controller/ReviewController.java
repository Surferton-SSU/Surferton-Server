package com.surferton.server.controller;

import com.surferton.server.domain.Review;
import com.surferton.server.dto.request.ReviewRequest;
import com.surferton.server.dto.response.ReviewSummaryResponse;
import com.surferton.server.service.ReviewService;
import com.surferton.server.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final S3Service s3Service;

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

    @GetMapping("/similar")
    public ResponseEntity<List<ReviewSummaryResponse>> getSimilarReviews(
            @RequestParam String major,
            @RequestParam String part,
            @RequestParam double gpa
    ) {
        List<ReviewSummaryResponse> similarReviews = reviewService.getSimilarReviews(major, part, gpa);
        return ResponseEntity.ok(similarReviews);
    }


    @PostMapping
    public ResponseEntity<String> createReview(@ModelAttribute ReviewRequest reviewRequestDTO) throws IOException {
        // 파일 업로드 처리 (selfIntroduction, portfolio)
        String selfIntroductionFilePath = uploadFile(reviewRequestDTO.selfIntroduction(), "selfIntroduction/");
        String portfolioFilePath = uploadFile(reviewRequestDTO.portfolio(), "portfolio/");

        // Review 생성 및 저장
        Review review = reviewService.createReview(reviewRequestDTO, selfIntroductionFilePath, portfolioFilePath);

        return ResponseEntity.ok("Review created successfully with ID: " + review.getId());
    }

    // 파일 업로드 처리 로직
    private String uploadFile(MultipartFile file, String directory) throws IOException {
        if (file != null && !file.isEmpty()) {
            return s3Service.uploadFile(directory, file);
        }
        return null;
    }


}
