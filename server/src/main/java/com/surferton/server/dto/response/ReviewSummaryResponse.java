package com.surferton.server.dto.response;

public record ReviewSummaryResponse(String profileImg,
                                    String part,
                                    String detailPart,
                                    String process,
                                    String major,
                                    double gpa) {
}
