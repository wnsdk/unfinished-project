package com.jungto.suhaeng.service;

import com.jungto.suhaeng.domain.dto.response.FeedResDto;
import com.jungto.suhaeng.domain.entity.Feed;
import com.jungto.suhaeng.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;

    @Override
    public List<FeedResDto> getFeeds() {
        List<Feed> feeds = feedRepository.findAll();
        System.out.println(feeds.get(0));

        List<FeedResDto> feedResDtoList = new ArrayList<>();
        for (int i = 0; i < feeds.size(); i++) {
            FeedResDto feedResDto = new FeedResDto();

            feedResDto.setFeedId(feeds.get(i).getFeedId());
            feedResDto.setMemberId(feeds.get(i).getMemberId());
            feedResDto.setProfileUrl("https://bit.ly/3VjDuoG");
            feedResDto.setType(feeds.get(i).getType());
            feedResDto.setContent(feeds.get(i).getContent());
            feedResDto.setMind(feeds.get(i).getMind());
            feedResDto.setLikes(5);
            feedResDto.setLiked(false);

            feedResDtoList.add(feedResDto);
        }
        return feedResDtoList;
    }
}
