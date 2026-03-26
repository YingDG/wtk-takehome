package com.wtk.takehome.controller;

import com.wtk.takehome.entity.Hotel;
import com.wtk.takehome.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 酒店控制器
 * 提供酒店查询相关的 RESTful API 接口
 *
 * @date 2026/3/26
 */
@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
public class HotelController {

    private final HotelService hotelService;

    /**
     * 根据 ID 查询酒店信息
     *
     * @param id 酒店 ID
     * @return 返回找到的酒店信息，如果未找到则返回 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        return hotelService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 根据酒店名称模糊查询酒店列表
     * 支持部分匹配，不区分大小写
     *
     * @param name 酒店名称关键词（例如："Ritz" 可匹配 "The Ritz London"）
     * @return 返回匹配的酒店列表，如果没有匹配结果则返回空列表
     */
    @GetMapping("/search/name")
    public ResponseEntity<List<Hotel>> searchHotelsByName(@RequestParam String name) {
        return ResponseEntity.ok(hotelService.findByNameContainingIgnoreCase(name));
    }

}
