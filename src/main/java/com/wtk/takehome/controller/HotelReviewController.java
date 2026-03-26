package com.wtk.takehome.controller;

import com.wtk.takehome.dto.ReviewDto;
import com.wtk.takehome.entity.Review;
import com.wtk.takehome.service.AiSummaryService;
import com.wtk.takehome.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 酒店评论控制器
 * 提供酒店评论查询和管理相关的 RESTful API 接口
 *
 * @author zdm
 * @date 2026/3/26
 */
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
public class HotelReviewController {

    private final ReviewService reviewService;

    private final AiSummaryService aiSummaryService;

    /**
     * 根据酒店 ID 查询该酒店的所有评论列表
     *
     * @param hotelId 酒店 ID
     * @return 返回该酒店的所有评论列表，如果酒店没有评论则返回空列表
     */
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Review>> getReviewsByHotelId(@PathVariable Long hotelId) {
        List<Review> reviews = reviewService.findByHotelId(hotelId);
//        reviews.forEach(review -> review.setHotel(null));
        return ResponseEntity.ok(reviews);
    }

    /**
     * 新增一条酒店评论
     *
     * @param review 评论对象，需包含以下字段：
     *               - hotel: 酒店对象（至少包含 id）
     *               - reviewer: 评论者姓名
     *               - rating: 评分（1-5）
     *               - content: 评论内容
     *               - createdAt: 评论日期（格式：yyyy-MM-dd）
     * @return 返回新创建的评论对象
     */
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewDto review) {
        return ResponseEntity.ok(reviewService.save(review));
    }

    /**
     * 为指定评论重新生成摘要
     *
     * @param reviewId 评论 ID
     * @return 生成的摘要内容
     */
    @PostMapping("/{reviewId}/summary")
    public ResponseEntity<String> regenerateSummary(@PathVariable Long reviewId) {
        return reviewService.findById(reviewId)
                .map(review -> {
                    String summary = aiSummaryService.generateSummary(review.getContent());
                    return ResponseEntity.ok(summary);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
