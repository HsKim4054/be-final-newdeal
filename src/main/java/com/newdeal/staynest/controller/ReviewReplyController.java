package com.newdeal.staynest.controller;

import com.newdeal.staynest.dto.hostReply.HostReplyRequest;
import com.newdeal.staynest.dto.review.ReviewRequest;
import com.newdeal.staynest.dto.review.ReviewResponse;
import com.newdeal.staynest.entity.Reservation;
import com.newdeal.staynest.entity.Review;
import com.newdeal.staynest.entity.accommodation.Accommodation;
import com.newdeal.staynest.repository.ReviewRepository;
import com.newdeal.staynest.service.ReviewReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/review")
public class ReviewReplyController {
    private final ReviewReplyService reviewReplyService;
    private final ReviewRepository reviewRepository;

    @GetMapping("/insertReview/{reservationId}/{accomId}")
    public ModelAndView insertReview(
            @PathVariable Long reservationId,
            @PathVariable Long accomId
//            @RequestHeader("Authorization") String token
    ) {
        // 여기에 토큰을 검증하는 로직을 추가할 수 있습니다.
//        if (!isTokenValid(token)) {
//            // 토큰이 유효하지 않은 경우 처리 로직 추가 (예: 에러 페이지로 이동)
//            ModelAndView mav = new ModelAndView("error/unauthorized");
//            return mav;
//        }
        Reservation reservation = reviewReplyService.getReservationByReservation(reservationId);
        Review review = reviewReplyService.findByReservationId(reservationId);
        Accommodation accom = reviewReplyService.getAccomodationByAccomodation(accomId);
        ModelAndView mav = new ModelAndView("review/insertReview");
        mav.addObject("reservation", reservation);
        mav.addObject("accom", accom);
        mav.addObject("review", review);
        return mav;
    }

    private boolean isTokenValid(String token) {
        // 실제 토큰 검증 로직을 구현합니다.
        return true; // 임시로 항상 유효한 것으로 설정
    }

    @PostMapping("/insertReview/{reservationId}/{accomId}")
    public ResponseEntity<Map<String, String>> insertReview(
            @RequestBody ReviewRequest reviewRequest,
            @PathVariable Long reservationId,
            @PathVariable Long accomId
    ) {
        reviewReplyService.ReviewSave(reviewRequest, reservationId, accomId);

        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", "/review/fullReview/" + accomId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fullReview/{accommId}")
    public ModelAndView fullReview(@PathVariable Long accommId) {
        List<ReviewResponse> reviewResponses = reviewReplyService.getReviewsByAccommodationId(accommId);
        ModelAndView mav = new ModelAndView("review/fullReview");
        mav.addObject("reviewResponses", reviewResponses);
        return mav;
    }

    @PostMapping("/insertReply/{reviewId}/{accomId}")
    public ResponseEntity<Map<String, String>> insertReply(
            @RequestBody HostReplyRequest hostReplyRequest,
            @PathVariable Long reviewId,
            @PathVariable Long accomId
    ) {
        reviewReplyService.ReplySave(hostReplyRequest, reviewId);

        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", "/review/fullReview/" + accomId);
        return ResponseEntity.ok(response);
    }
}
