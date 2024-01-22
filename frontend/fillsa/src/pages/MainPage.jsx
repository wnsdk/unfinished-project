import { useMutation, useQuery } from '@tanstack/react-query';
import { customTestAxios } from '../apis/customTestAxios';

export default function MainPage() {
    // const { isLoading, error, data, isFetching } = useQuery({
    //     queryKey: ['getPost'],
    //     queryFn: async () => {
    //         const response = await customTestAxios.get('posts/1');
    //         return response.data;
    //     },
    // });

    // const { mutate, isLoading, isError, error, isSuccess, data } = useMutation({
    //     mutationFn: async () => {
    //         return await customTestAxios.delete('posts/1');
    //     },
    // });

    const { mutate, isLoading, isError, error, isSuccess, data } = useMutation({
        mutationFn: async () => {
            return await fetch('https://koreanjson.com/posts/1', { method: 'DELETE' });
        },
    });

    return (
        <>
            <div>Main Page</div>
            <button onClick={() => mutate()}>글 삭제</button>
            {isError && <div>삭제 실패 {error.message}</div>}
            {isSuccess && console.log(data.Response)}
        </>
    );
}
