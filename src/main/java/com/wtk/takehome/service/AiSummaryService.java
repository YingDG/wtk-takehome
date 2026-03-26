package com.wtk.takehome.service;

public interface AiSummaryService {
    
    /**
     * 为评论内容生成摘要
     * @param content 评论原文
     * @return 一句话摘要
     */
    String generateSummary(String content);
}
