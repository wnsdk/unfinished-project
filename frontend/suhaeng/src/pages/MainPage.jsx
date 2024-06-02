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
            return response.data;
        },
    });

    // const { isLoading, error, data, isFetching } = useQuery({
    //     queryKey: ['googleLogin'],
    //     queryFn: async () => {
    //         const response = await customAxios.get('feed');
    //         return response.data;
    //     },
    // });

    const [cnt, setCnt] = useState(3124);

    const loginUser = useLoginStore((state) => state);

    function login(thirdPartyId) {
        window.location.href = `http://localhost:8080/oauth2/authorization/${thirdPartyId}`;
    }

    return (
        <div className={styles.body}>
            <div className={styles.gradient}>
                <div className={styles.box2}>
                    <div className={styles.box2_1}>
                        {!isLoading && feeds.map((feed, i) => <Component1 key={i} feed={feed} />)}
                    </div>

                    {loginUser.isLogin ? (
                        <div className={styles.box2_2}>
                            <div>
                                <input type="radio" id="all" name="permission" />
                                <label htmlFor="all">전체 공개</label>
                                <input type="radio" id="group" name="permission" />
                                <label htmlFor="group">그룹 공개</label>
                                <input type="radio" id="me" name="permission" />
                                <label htmlFor="me">비공개</label>
                            </div>

                            <div className={styles.box2_2_2}>
                                <img className={styles.myProfileImg} src={loginUser.profileUrl} />
                                <div className={styles.textbox}>
                                    <textarea placeholder="2024년 5월 30일, 오늘의 수행을 도반들에게 나눠주세요."></textarea>
                                    <div className={styles.submit}>전송</div>
                                </div>
                            </div>
                        </div>
                    ) : (
                        <div
                            className={styles.loginButton}
                            onClick={() => {
                                login('google');
                            }}
                        >
                            구글 로그인
                        </div>
                    )}
                </div>

                <div className={styles.box1}>
                    <div className={styles.title}>
                        함께 <span className={styles.highlight}>수행</span>
                    </div>
                    <div>
                        혼자 하는 수행이 아닙니다
                        <br />
                        우리는 모두 <span className={styles.highlight}>연결</span>되어 있습니다
                    </div>
                    <div>
                        지금 이 순간에도, 보이지 않는 곳에서
                        <br />
                        도반님들이 함께 수행에 참여하고 있습니다.
                    </div>
                    {/* <img src={candlelight} /> */}
                    <div className={styles.box1_3}>
                        <div>
                            오늘 <span className={styles.cnt}>{cnt}</span> 명의 수행자들이 함께 하고 있습니다
                        </div>
                        <div className={styles.current_users_box}>
                            {[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1].map((x, i) => (
                                <div key={i} className={styles.current_user}>
                                    홍길동 도반님
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
