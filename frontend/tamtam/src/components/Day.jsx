// 시간표 하루 컬럼

import { React, useState, useRef, useEffect } from "react";
import style from "./Day.module.css";
import { useRecoilState } from "recoil";
// import {
//   focusedDayAtom,
//   clickedIndexStartAtom,
//   clickedIndexEndAtom,
//   focusedIndexAtom,
// } from "util/store";

export default function Day({ date, day, isFocused, ref }) {
  /** 데이터 */
  const [act, setAct] = useState(Array.from({ length: 144 }, () => ""));
  const [clickedIndexStart, setClickedIndexStart] = useState(-1); // 클릭된 인덱스 시작 번호 (className 변경하기 위함)
  const [clickedIndexEnd, setClickedIndexEnd] = useState(-1); // 클릭된 인덱스 끝 번호
  const [focusedIndex, setFocusedIndex] = useState(); // 포커싱된 인덱스
  // const [focusedDay, setFocusedDay] = useRecoilState(focusedDayAtom);
  // const [clickedIndexStart, setClickedIndexStart] = useRecoilState(
  //   clickedIndexStartAtom
  // ); // 클릭된 인덱스 시작 번호
  // const [clickedIndexEnd, setClickedIndexEnd] =
  //   useRecoilState(clickedIndexEndAtom); // 클릭된 인덱스 끝 번호
  // const [focusedIndex, setFocusedIndex] = useRecoilState(focusedIndexAtom); // 포커싱된 인덱스

  const inputRefs1 = useRef([]);
  const inputRefs2 = useRef([]);

  /** 함수 */
  // 더블 클릭 시
  const onDoubleClick = (idx) => {
    setFocusedIndex(idx);
  };

  // 마우스 1번 클릭 시
  const onMouseDown = (e, idx) => {
    // focus 비활성화
    // if (clickedIndexStart !== idx) {
    //   e.preventDefault();
    // }
    // 기존에 focus 된 칸을 클릭한 게 아니라면
    if (focusedIndex !== idx) {
      setFocusedIndex(-1);
    }

    document.activeElement.blur();
    setClickedIndexStart(idx);
    setClickedIndexEnd(idx);
  };

  // 드래그 시작
  const onDragStart = (idx) => {};

  // 드래그 진입
  const onDragEnter = (idx) => {
    if (!isFocused) return;
    setClickedIndexEnd(idx);
  };

  // 드래그 끝
  const onDrop = (e, idx) => {
    e.preventDefault();
    if (clickedIndexStart > clickedIndexEnd) {
      let tmp = clickedIndexStart;
      setClickedIndexStart(clickedIndexEnd);
      setClickedIndexEnd(tmp);
    }
    merge();
  };

  // 병합
  const merge = () => {
    console.log("병합합니당");
    // clickedIndexStart
  };

  /** useEffect */
  useEffect(() => {
    // 클릭된 인풋 태그의 인덱스가 바뀌면, 해당 인풋 태그를 포커싱하기
    inputRefs1.current[clickedIndexStart]?.focus();
  }, [clickedIndexStart]);

  useEffect(() => {
    // 포커싱된 인풋 태그의 인덱스가 바뀌면, 해당 인풋 태그를 포커싱하기
    inputRefs2.current[focusedIndex]?.focus();
  }, [focusedIndex]);

  return (
    <>
      <div className={style.dates}>
        <div className={style.date}>{date}일</div>
        <div className={style.day}>{day}</div>
        <div className={style.minutes}>
          {act.map((item, idx) => (
            <div
              key={idx}
              className={
                isFocused &&
                Math.min(clickedIndexStart, clickedIndexEnd) <= idx &&
                idx <= Math.max(clickedIndexStart, clickedIndexEnd)
                  ? style.clicked
                  : ""
              }
              onDragStart={() => onDragStart(idx)}
              onDragEnter={() => {
                onDragEnter(idx);
              }}
              onDragOver={(e) => {
                e.preventDefault();
              }}
              onDrop={(e) => {
                onDrop(e, idx);
              }}
              onMouseDown={(e) => {
                onMouseDown(e, idx);
              }}
              onDoubleClick={() => onDoubleClick(idx)}
              draggable={focusedIndex !== idx}
            >
              {!isFocused || focusedIndex !== idx ? (
                <input
                  className={style.notFocused}
                  ref={(element) => {
                    inputRefs1.current[idx] = element;
                  }}
                  // style={{ color: "red" }}
                  value={act[idx]}
                  onKeyPress={() => {
                    setFocusedIndex(idx);
                  }}
                  readOnly
                />
              ) : (
                <input
                  ref={(element) => {
                    inputRefs2.current[idx] = element;
                  }}
                  onChange={(e) => {
                    let newAct = [...act];
                    newAct[idx] = e.target.value;
                    setAct(newAct);
                  }}
                  onKeyDown={(e) => {
                    // TODO : 화살표 키보드 안먹힘 (스크롤만 내려감)
                    if (e.keyCode === 13 || e.keyCode === 34) {
                      setClickedIndexStart(idx + 1);
                      setClickedIndexEnd(idx + 1);
                      setFocusedIndex(idx + 1);
                    }
                  }}
                  value={act[idx]}
                />
              )}
            </div>
          ))}
        </div>
      </div>
    </>
  );
}
