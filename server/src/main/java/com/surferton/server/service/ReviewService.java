package com.surferton.server.service;

import com.surferton.server.common.dto.ErrorMessage;
import com.surferton.server.domain.Review;
import com.surferton.server.exception.CustomizedException;
import com.surferton.server.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public Review findReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomizedException(ErrorMessage.REVIEW_NOT_FOUND_BY_ID_EXCEPTION));
    }
}
