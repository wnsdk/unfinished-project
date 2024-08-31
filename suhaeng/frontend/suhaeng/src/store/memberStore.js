import { create } from "zustand";

const useLoginStore = create((set) => ({
  isLogin: false,
  profileUrl: null,
  memberName: null,
  email: null,
  // plusCounter: () => set((state) => ({ counter: state.counter + 1 })),
  // resetCounter: () => set({ counter: 0 }),
}));

export { useLoginStore };
