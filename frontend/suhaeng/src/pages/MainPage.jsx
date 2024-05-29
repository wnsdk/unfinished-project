import styles from './MainPage.module.scss';
import Component1 from '@/components/Component1';
import { useQuery } from '@tanstack/react-query';
import { customAxios } from '../apis/customAxios';

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

    return (
        <div className={styles.body}>
            <div className={styles.gradient}>
                <div className={styles.box2}>
                    <div className={styles.box2_1}>
                        {!isLoading && feeds.map((feed, i) => <Component1 key={i} feed={feed} />)}
                    </div>
                    <div className={styles.button}>로그인하기</div>
                </div>

                <div className={styles.box1}>
                    <div>
                        <div>혼자 하는 수행이 아닙니다</div>
                        <div>우리는 모두 연결되어 있습니다</div>
                    </div>
                    <div className={styles.date}>2024 05 28 13:23</div>
                    <div>
                        <div>3,154 명의 수행자들이 함께 하고 있습니다</div>
                        <div>ㅇㅇㅇ</div>
                    </div>
                </div>
            </div>
        </div>
    );
}
