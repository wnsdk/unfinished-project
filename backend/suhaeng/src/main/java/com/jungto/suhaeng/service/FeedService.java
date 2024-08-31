package com.jungto.suhaeng.service;

import com.jungto.suhaeng.domain.dto.response.FeedResDto;

import java.util.List;

public interface FeedService {
    List<FeedResDto> getFeeds();
}
