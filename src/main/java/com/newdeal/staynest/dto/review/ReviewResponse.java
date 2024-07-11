package com.newdeal.staynest.dto.review;

import com.newdeal.staynest.entity.Review;
import com.newdeal.staynest.entity.ReviewImg;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewResponse {
    private Long reviewId;
    private int star;
    private String content;
    private LocalDateTime createdAt;
    private List<ReviewImgResponse> images;

    public ReviewResponse(Long reviewId, int star, String content, LocalDateTime createdAt, List<ReviewImgResponse> images) {
        this.reviewId = reviewId;
        this.star = star;
        this.content = content;
        this.createdAt = createdAt;
        this.images = images;
    }

    // Getters
    public Long getReviewId() {
        return reviewId;
    }

    public int getStar() {
        return star;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<ReviewImgResponse> getImages() {
        return images;
    }

    // Review 엔티티를 ReviewResponse DTO로 변환하는 메서드
    public static ReviewResponse fromEntity(Review review) {
        // Review 엔티티의 images 리스트를 ReviewImgResponse DTO 리스트로 변환
        List<ReviewImgResponse> imageResponses = review.getImages().stream()
                .map(ReviewImgResponse::fromEntity)
                .collect(Collectors.toList());

        // ReviewResponse 객체 생성하여 반환
        return new ReviewResponse(
                review.getReviewId(),
                review.getStar(),
                review.getContent(),
                review.getCreatedAt(),
                imageResponses
        );
    }

    // List<Review>를 List<ReviewResponse>로 변환하는 메서드
    public static List<ReviewResponse> fromEntity(List<Review> reviews) {
        return reviews.stream()
                .map(ReviewResponse::fromEntity)
                .collect(Collectors.toList());
    }
}

class ReviewImgResponse {
    private Long id;
    private String url;

    public ReviewImgResponse(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    // Factory method to create ReviewImgResponse from ReviewImg entity
    public static ReviewImgResponse fromEntity(ReviewImg reviewImg) {
        return new ReviewImgResponse(
                reviewImg.getId(),
                reviewImg.getImgUrl() // 여기서 getUrl()을 getImgUrl()로 변경
        );
    }
}
