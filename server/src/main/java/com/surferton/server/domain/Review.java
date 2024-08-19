package com.surferton.server.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewid")
    private Long id;

    private String username;
    private String profileImg;
    private String part;
    private String detailPart;
    private String process;
    private String major;
    private double gpa; // 학점
    private String enScore;
    private String content;
    private String comment;
    private String selfIntroduction;
    private String portfolio;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "review_id")
    private List<Activity> activityList;

    @Builder
    public Review(String username, String profileImg, String part, String detailPart, String process, String major, double gpa, String enScore,
                  String content, String comment, String selfIntroduction, String portfolio, Status status, List<Activity> activityList) {
        this.username = username;
        this.profileImg = profileImg;
        this.part = part;
        this.detailPart = detailPart;
        this.process = process;
        this.major = major;
        this.gpa = gpa;
        this.enScore = enScore;
        this.content = content;
        this.comment = comment;
        this.selfIntroduction = selfIntroduction;
        this.portfolio = portfolio;
        this.status = status;
        this.activityList = activityList;
    }

    public static Review create(String username, String profileImg, String part, String detailPart, String process, String major, double gpa, String enScore, List<Activity> activityList,
                                String content, String comment, String selfIntroduction, String portfolio, Status status) {
        return Review.builder()
                .username(username)
                .profileImg(profileImg)
                .part(part)
                .detailPart(detailPart)
                .process(process)
                .major(major)
                .gpa(gpa)
                .enScore(enScore)
                .activityList(activityList) // 활동 리스트 추가
                .content(content)
                .comment(comment)
                .selfIntroduction(selfIntroduction)
                .portfolio(portfolio)
                .status(status)
                .build();
    }
}
