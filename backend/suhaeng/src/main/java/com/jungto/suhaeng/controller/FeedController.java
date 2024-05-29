package com.jungto.suhaeng.controller;

import com.jungto.suhaeng.domain.dto.response.FeedResDto;
import com.jungto.suhaeng.service.FeedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "feed", description = "피드 API")
@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @Operation(summary = "피드 조회")
    @GetMapping
    public ResponseEntity<List<FeedResDto>> getFeeds(){
        List<FeedResDto> feedResDtoList = feedService.getFeeds();
        return ResponseEntity.ok(feedResDtoList);
    }

}
