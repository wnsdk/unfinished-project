package com.jungto.suhaeng.domain.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FeedResDto {
    private Long memberId;
    private Long feedId;
    private String profileUrl;
    private String type;           //피드의 글 종류
    private String content;
    private String mind;
    private int likes;          //피드의 좋아요 개수
    private boolean isLiked;    //내가 이 피드에 좋아요를 눌렀는가?
}
