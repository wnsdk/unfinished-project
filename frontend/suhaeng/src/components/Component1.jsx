import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart as regularHeart } from '@fortawesome/free-regular-svg-icons';
import { faHeart as solidHeart } from '@fortawesome/free-solid-svg-icons';
import defaultProfile from '@/assets/profiledefault.png'; //디폴트 프로필 이미지

import styles from './Component1.module.scss';
import { useState } from 'react';

export default function Component1(props) {
    const [content, setContent] = useState(props.feed.content);
    const [isLiked, setIsLiked] = useState(props.feed.liked);
    const [likes, setLikes] = useState(props.feed.likes);
    const [mind, setMind] = useState(props.feed.mind);
    const [profileUrl, setProfileUrl] = useState(props.feed.profileUrl);
    const [type, setType] = useState(props.feed.type);
    const [memberId, setMemberId] = useState(props.feed.memberId);
    const [memberName, setmemberName] = useState(props.feed.memberName);

    // const nameOfType = ['', '마음 나누기', '수행 나누기', '수행 일기', '경전 읽기'];

    //피드 좋아요 누르기
    function likeFeed() {
        setIsLiked(!isLiked);
        if (isLiked) {
            setLikes(likes > 0 ? likes - 1 : 0);
        } else {
            setLikes(likes + 1);
        }
    }

    return (
        <div className={styles.body}>
            <div className={styles.box1}>
                <img className={styles.user_profile} src={profileUrl ? profileUrl : defaultProfile} />
                <div className={styles.triangle} />
            </div>
            <div className={styles.box2}>
                <div className={styles.top_metadata}>
                    <span>
                        <span className={styles.user_name}>{memberName}</span> 도반님<span>D+1224</span>
                    </span>
                    <span className={styles.heart} onClick={() => likeFeed()}>
                        {isLiked ? <FontAwesomeIcon icon={solidHeart} /> : <FontAwesomeIcon icon={regularHeart} />}
                        {likes}
                    </span>
                </div>
                <div className={styles.speech_bubble}>
                    {/* <div className={styles.content_type}>{nameOfType[type]}</div> */}
                    <div className={styles.content_1}>{content}</div>
                    <div className={styles.content_2}>지금 마음은 {mind}</div>
                </div>
                <div className={styles.bottom_metadata}>
                    <span>2024/05/28 13:22</span>
                    <span className={styles.edit_box}>
                        <span>수정</span>
                        <span>삭제</span>
                    </span>
                </div>
            </div>
        </div>
    );
}
