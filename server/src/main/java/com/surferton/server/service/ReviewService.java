package com.surferton.server.service;

import com.surferton.server.common.dto.ErrorMessage;
import com.surferton.server.domain.Review;
import com.surferton.server.dto.request.ReviewRequest;
import com.surferton.server.dto.response.ReviewSummaryResponse;
import com.surferton.server.exception.CustomizedException;
import com.surferton.server.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public Review findReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomizedException(ErrorMessage.REVIEW_NOT_FOUND_BY_ID_EXCEPTION));
    }

    @Transactional
    public Review createReview(ReviewRequest reviewRequestDTO, String selfIntroductionPath, String portfolioPath) {
        // Review 객체 생성
        Review review = Review.builder()
                .username(reviewRequestDTO.username())
                .profileImg(reviewRequestDTO.profileImg())
                .part(reviewRequestDTO.part())
                .detailPart(reviewRequestDTO.detailPart())
                .process(reviewRequestDTO.process())
                .major(reviewRequestDTO.major())
                .gpa(reviewRequestDTO.gpa())
                .enScore(reviewRequestDTO.enScore())
                .content(reviewRequestDTO.content())
                .comment(reviewRequestDTO.comment())
                .activityList(reviewRequestDTO.activityList())
                .selfIntroduction(selfIntroductionPath)  // 업로드된 파일 경로 저장
                .portfolio(portfolioPath)  // 업로드된 파일 경로 저장
                .build();

        // DB에 저장
        return reviewRepository.save(review);
    }

    public List<ReviewSummaryResponse> getReviewSummaries() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(ReviewSummaryResponse::of)
                .collect(Collectors.toList());
    }

    public List<ReviewSummaryResponse> getSimilarReviews(String major, String part, double gpa) {
        double minGpa = gpa - 0.2;
        double maxGpa = gpa + 0.2;
        List<Review> similarReviews = reviewRepository.findSimilarReviews(major, part, minGpa, maxGpa);
        return similarReviews.stream()
                .map(ReviewSummaryResponse::of)
                .collect(Collectors.toList());
    }
}
