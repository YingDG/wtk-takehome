package com.wtk.takehome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private Long hotelId;

    private String reviewer;

    private Integer rating;

    private String content;

    private String summary;

//    private LocalDate createdAt;
}
