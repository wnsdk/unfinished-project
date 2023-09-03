import { React, useState } from "react";
import style from "./Main.module.css";
import TableHeader from "components/TableHeader";
import Day from "components/Day";

// const days = ["일", "월", "화", "수", "목", "금", "토"];
const days = [
  "일요일",
  "월요일",
  "화요일",
  "수요일",
  "목요일",
  "금요일",
  "토요일",
];

export default function Main() {
  let today = new Date();
  // let year = today.getFullYear; // 년
  let month = today.getMonth() + 1; // 월
  let date = today.getDate(); // 일
  let day = today.getDay(); // 요일
  let startDate = date - day; // 시작 기준일

  const [focusedDay, setFocusedDay] = useState(0); // 포커싱된 요일

  /** 메서드 */
  // 저장 (TODO : 버튼을 직접 누르지 않고 몇 초마다 자동 저장하기)
  const store = () => {
    alert("아직 서버가 준비 안됐습니당^^");
  };

  // 병합
  const merge = () => {};

  return (
    <>
      <div className={style.menu}>
        <button onClick={() => merge()}>병합</button>
        <button onClick={() => store()}>저장</button>
      </div>
      <div className={style.table}>
        <TableHeader month={month} />
        {Array.from({ length: 7 }).map((item, idx) => (
          <div
            key={idx}
            onMouseDown={() => {
              setFocusedDay(idx);
            }}
          >
            <Day
              date={startDate + idx}
              day={days[idx]}
              isFocused={focusedDay === idx}
            />
          </div>
        ))}
      </div>
    </>
  );
}
