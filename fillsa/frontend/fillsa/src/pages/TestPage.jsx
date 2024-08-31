import { useMutation, useQuery } from '@tanstack/react-query';
import { customTestAxios } from '../apis/customTestAxios';
import { useTestStore } from '../store/store';

export default function TestPage() {
    const counter = useTestStore((state) => state.counter);
    const plusCounter = useTestStore((state) => state.plusCounter);
    const resetCounter = useTestStore((state) => state.resetCounter);

    const { isLoading, data } = useQuery({
        queryKey: ['getPost'],
        queryFn: async () => {
            const response = await customTestAxios.get('posts/1');
            return response.data;
        },
    });

    const { mutate, isError, error, isSuccess } = useMutation({
        mutationFn: async () => {
            return await customTestAxios.delete('posts/1');
        },
    });
    return (
        <>
            <div>
                <h3>Zustand 테스트</h3>
                <div>counter : {counter}</div>
                <button onClick={plusCounter}>더하기</button>
                <button onClick={resetCounter}>초기화</button>
            </div>
            <div>
                <h3>Axios, React Query 테스트</h3>
                {!isLoading && (
                    <>
                        <div>{data.title}</div>
                        <div>{data.content}</div>
                    </>
                )}
                <button onClick={() => mutate()}>글 삭제</button>
                {isError && <div>삭제 실패 {error.message}</div>}
                {isSuccess && <div>삭제 성공</div>}
            </div>
        </>
    );
}
