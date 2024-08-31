package com.jungto.suhaeng.domain.entity;

import com.jungto.suhaeng.domain.LikesId;
import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "Likes")
public class Likes {
    @EmbeddedId
    private LikesId id;

    @MapsId("memberId")
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @MapsId("feedId")
    @ManyToOne
    @JoinColumn(name = "feed_id")
    private Feed feedId;

}
