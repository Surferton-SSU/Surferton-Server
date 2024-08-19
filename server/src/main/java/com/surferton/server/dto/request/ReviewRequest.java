package com.surferton.server.dto.request;

import com.surferton.server.domain.Activity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ReviewRequest(
        String username,
        String profileImg,
        String part,
        String detailPart,
        String process,
        String major,
        double gpa,
        String enScore,
        String content,
        String comment,
        List<Activity> activityList,
        MultipartFile selfIntroduction,
        MultipartFile portfolio
) {
    public static ReviewRequest from(
            String username,
            String profileImg,
            String part,
            String detailPart,
            String process,
            String major,
            double gpa,
            String enScore,
            String content,
            String comment,
            List<Activity> activityList,
            MultipartFile selfIntroduction,
            MultipartFile portfolio
    ) {
        return new ReviewRequest(
                username,
                profileImg,
                part,
                detailPart,
                process,
                major,
                gpa,
                enScore,
                content,
                comment,
                activityList,
                selfIntroduction,
                portfolio
        );
    }
}