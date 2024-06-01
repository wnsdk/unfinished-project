import styles from './MainPage.module.scss';
import Component1 from '@/components/Component1';
import { useQuery } from '@tanstack/react-query';
import { customAxios } from '../apis/customAxios';
// import candlelight from '@/assets/다운로드-removebg-preview.png';
import { useState } from 'react';
import { useLoginStore } from '@/store/memberStore';

export default function MainPage() {
    const {
        isLoading,
        error,
        data: feeds,
        isFetching,
    } = useQuery({
        queryKey: ['getFeeds'],
        queryFn: async () => {
            const response = await customAxios.get('feed');
            console.log(response.data);
            console.log(typeof response.data);
            return response.data;
        },
    });

    const [cnt, setCnt] = useState(3124);

    const myProfileUrl = useLoginStore((state) => state.profileUrl);

    return (
        <div className={styles.body}>
            <div className={styles.gradient}>
                <div className={styles.box2}>
                    <div className={styles.box2_1}>
                        {!isLoading && feeds.map((feed, i) => <Component1 key={i} feed={feed} />)}
                    </div>
                    <div className={styles.box2_2}>
                        <div>
                            <input type="radio" id="all" name="permission" />
                            <label for="all">전체 공개</label>
                            <input type="radio" id="group" name="permission" />
                            <label for="group">그룹 공개</label>
                            <input type="radio" id="me" name="permission" />
                            <label for="me">비공개</label>
                        </div>
                        <div>
                            <img className={styles.myProfileImg} src={myProfileUrl} />
                            <div>
                                <textarea placeholder="2024년 5월 30일, 오늘의 수행을 도반들에게 나눠주세요."></textarea>
                                <div>전송</div>
                            </div>
                        </div>
                    </div>
                    {/* <div className={styles.button}>로그인하기</div> */}
                </div>

                <div className={styles.box1}>
                    <div>
                        <div>혼자 하는 수행이 아닙니다</div>
                        <div>우리는 모두 연결되어 있습니다</div>
                    </div>
                    {/* <img src={candlelight} /> */}
                    <div>
                        <div>
                            오늘 <span>{cnt}</span> 명의 수행자들이 함께 하고 있습니다
                        </div>
                        <div>ㅇㅇㅇ</div>
                    </div>
                </div>
            </div>
        </div>
    );
}
