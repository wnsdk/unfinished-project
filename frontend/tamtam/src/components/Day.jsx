// 시간표 하루 컬럼

import { React, useState, useRef } from "react";
import style from "./Day.module.css";

export default function Day() {
  /** 데이터 */
  const [act, setAct] = useState(Array.from({ length: 144 }, () => ""));
  const [ClickedIndex, setClickedIndex] = useState(); // 클릭된 인덱스 번호 (className 변경하기 위함)

  const dragItemIndex = useRef(null);
  const inputRefs = useRef([]);

  /** 함수 */
  const onDoubleClick = (idx) => {
    inputRefs.current[idx]?.focus();
  };
  const onMouseDown = (e, idx) => {
    // 마우스 1번 클릭 시 focus 비활성화
    e.preventDefault();
    document.activeElement.blur();
    setClickedIndex(idx);
  };

  const onDragStart = () => {
    console.log("드래그 시작");
  };
  const onDragEnd = (e) => {
    // e.target.classList.remove("grabbing");
    console.log("드래그 끝");
  };

  return (
    <>
      <div className={style.days}>
        <div className={style.day}>3일</div>
        <div className={style.week}>일요일</div>
        <div className={style.minutes}>
          {act.map((item, idx) => (
            <div
              key={idx}
              className={ClickedIndex === idx ? style.clicked : ""}
              // onDragStart={() => onDragStart}
              // onDragEnd={() => onDragEnd}
              // onClick={() => onDragStart}
            >
              <input
                ref={(element) => {
                  inputRefs.current[idx] = element;
                }}
                onDoubleClick={() => onDoubleClick(idx)}
                onMouseDown={(e) => {
                  onMouseDown(e, idx);
                }}
                // value={}
              />
            </div>
          ))}
        </div>
      </div>
    </>
  );
}
