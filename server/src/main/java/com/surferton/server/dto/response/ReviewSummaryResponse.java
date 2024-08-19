package com.surferton.server.dto.response;

import com.surferton.server.domain.Review;

public record ReviewSummaryResponse(String username, String profileImg, String part, String detailPart, String process,
                                    String major, double gpa) {

    public static ReviewSummaryResponse of(Review review) {
        return new ReviewSummaryResponse(review.getUsername(), review.getProfileImg(), review.getPart(), review.getDetailPart(), review.getProcess(), review.getMajor(), review.getGpa());
    }
}
