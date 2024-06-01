import { create } from 'zustand';

const useLoginStore = create((set) => ({
    profileUrl: 'https://bit.ly/3V2xkYB',
    memberName: '김실험',
    email: 'zzz@gmail.com',
    // plusCounter: () => set((state) => ({ counter: state.counter + 1 })),
    // resetCounter: () => set({ counter: 0 }),
}));

export { useLoginStore };
