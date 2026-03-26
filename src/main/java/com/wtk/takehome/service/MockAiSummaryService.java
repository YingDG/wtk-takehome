package com.wtk.takehome.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Mock 实现的 AI 摘要服务
 * 模拟 AI 生成评论摘要的功能
 */
@Service
class MockAiSummaryService implements AiSummaryService {

    private static final Map<String, String> PREDEFINED_SUMMARIES = new HashMap<>();
    private final Random random = new Random();

    static {
        // 正面评价模板
        PREDEFINED_SUMMARIES.put("positive", "客人对酒店非常满意，特别赞赏其优质的服务和设施。");
        PREDEFINED_SUMMARIES.put("positive_location", "客人高度赞扬酒店的地理位置和周边便利性。");
        PREDEFINED_SUMMARIES.put("positive_staff", "客人对酒店员工的服务态度和专业知识印象深刻。");
        PREDEFINED_SUMMARIES.put("positive_cleanliness", "客人认为酒店非常干净整洁，卫生状况优秀。");

        // 负面评价模板
        PREDEFINED_SUMMARIES.put("negative", "客人对酒店体验不满意，存在多个需要改进的问题。");
        PREDEFINED_SUMMARIES.put("negative_cleanliness", "客人反映酒店卫生条件差，清洁工作不到位。");
        PREDEFINED_SUMMARIES.put("negative_service", "客人对酒店服务质量感到失望，响应速度慢。");
        PREDEFINED_SUMMARIES.put("negative_facilities", "客人认为酒店设施陈旧或维护不当。");

        // 中性评价模板
        PREDEFINED_SUMMARIES.put("neutral", "客人对酒店体验感觉一般，既有优点也有不足。");
        PREDEFINED_SUMMARIES.put("neutral_value", "客人认为酒店性价比合理，适合预算有限的旅客。");
    }

    @Override
    public String generateSummary(String content) {
        if (content == null || content.trim().isEmpty()) {
            return "该评论没有提供具体内容。";
        }

        String lowerContent = content.toLowerCase();

        // 简单的情感分析和关键词匹配
        int positiveScore = 0;
        int negativeScore = 0;

        // 正面关键词
        String[] positiveWords = {"excellent", "amazing", "wonderful", "perfect", "great", "best",
                "fantastic", "beautiful", "comfortable", "friendly", "helpful",
                "impressive", "stunning", "breathtaking", "enjoyed", "loved"};

        // 负面关键词
        String[] negativeWords = {"terrible", "awful", "worst", "disappointing", "poor", "bad",
                "dirty", "broken", "noisy", "uncomfortable", "rude", "slow",
                "overpriced", "disgusting", "never"};

        for (String word : positiveWords) {
            if (lowerContent.contains(word)) {
                positiveScore++;
            }
        }

        for (String word : negativeWords) {
            if (lowerContent.contains(word)) {
                negativeScore++;
            }
        }

        // 根据情感分数返回对应的摘要
        if (positiveScore > negativeScore) {
            if (lowerContent.contains("location") || lowerContent.contains("center")) {
                return PREDEFINED_SUMMARIES.get("positive_location");
            } else if (lowerContent.contains("staff") || lowerContent.contains("service") ||
                    lowerContent.contains("friendly") || lowerContent.contains("helpful")) {
                return PREDEFINED_SUMMARIES.get("positive_staff");
            } else if (lowerContent.contains("clean") || lowerContent.contains("spotless") ||
                    lowerContent.contains("immaculate")) {
                return PREDEFINED_SUMMARIES.get("positive_cleanliness");
            }
            return PREDEFINED_SUMMARIES.get("positive");
        } else if (negativeScore > positiveScore) {
            if (lowerContent.contains("dirty") || lowerContent.contains("stain") ||
                    lowerContent.contains("hair") || lowerContent.contains("mold")) {
                return PREDEFINED_SUMMARIES.get("negative_cleanliness");
            } else if (lowerContent.contains("service") || lowerContent.contains("staff") ||
                    lowerContent.contains("slow") || lowerContent.contains("rude")) {
                return PREDEFINED_SUMMARIES.get("negative_service");
            } else if (lowerContent.contains("facility") || lowerContent.contains("equipment") ||
                    lowerContent.contains("broken") || lowerContent.contains("outdated")) {
                return PREDEFINED_SUMMARIES.get("negative_facilities");
            }
            return PREDEFINED_SUMMARIES.get("negative");
        } else {
            if (lowerContent.contains("price") || lowerContent.contains("value") ||
                    lowerContent.contains("budget") || lowerContent.contains("cheap")) {
                return PREDEFINED_SUMMARIES.get("neutral_value");
            }
            return PREDEFINED_SUMMARIES.get("neutral");
        }
    }
}
