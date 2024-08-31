import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist();

// export const focusedDayAtom = atom({
//   key: "focusedDayAtom",
//   default: -1,
//   effects_UNSTABLE: [persistAtom],
// });

// export const clickedIndexStartAtom = atom({
//   key: "clickedIndexStartAtom",
//   default: -1,
//   effects_UNSTABLE: [persistAtom],
// });

// export const clickedIndexEndAtom = atom({
//   key: "clickedIndexEndAtom",
//   default: -1,
//   effects_UNSTABLE: [persistAtom],
// });

// export const focusedIndexAtom = atom({
//   key: "focusedIndexAtom",
//   default: -1,
//   effects_UNSTABLE: [persistAtom],
// });
