// 시간표 하루 컬럼

import { React, useState, useRef, useEffect } from "react";
import style from "./Day.module.css";
// import { useRecoilState } from "recoil";
// import {
//   focusedDayAtom,
//   clickedIndexStartAtom,
//   clickedIndexEndAtom,
//   focusedIndexAtom,
// } from "util/store";

export default function Day({ date, day, isFocused }) {
  /** 데이터 */
  // 기록한 내용 (서버에 보낼 내용만 담는다.)
  const [acts, setActs] = useState(
    Array.from({ length: 144 }, () => {
      return {
        data: null,
        mergeEnd: -1, // mergeEnd가 -1이 아닌 값이라면, 해당 칸은 merge가 시작되는 칸이다.
      };
    })
  );

  // 각 칸의 높이
  const [height, setHeight] = useState(
    Array.from({ length: 144 }, () => "20px")
  );

  // 각 칸을 보이게 할 것인지 여부
  const [display, setDisplay] = useState(
    Array.from({ length: 144 }, () => "block")
  );

  const [clickedIndexStart, setClickedIndexStart] = useState(-1); // 클릭된 인덱스 시작 번호 (className 변경하기 위함)
  const [clickedIndexEnd, setClickedIndexEnd] = useState(-1); // 클릭된 인덱스 끝 번호
  const [focusedIndex, setFocusedIndex] = useState(); // 포커싱된 인덱스

  const inputRefs1 = useRef([]); // '클릭' 인풋 태그 참조 변수
  const inputRefs2 = useRef([]); // '포커싱' 인풋 태그 참조 변수

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
    // if (clickedIndexStart > clickedIndexEnd) {
    //   let tmp = clickedIndexStart;
    //   setClickedIndexStart(clickedIndexEnd);
    //   setClickedIndexEnd(tmp);
    // }
    console.log("병합");
    merge();
  };

  // 병합
  const merge = () => {
    let start = Math.min(clickedIndexEnd, clickedIndexStart);
    let end = Math.max(clickedIndexEnd, clickedIndexStart);

    // 첫 번째 칸의 높이 늘리기
    console.log(start);
    let newHeight = [...height];
    newHeight[start] = `${(end - start + 1) * 20}px`;
    setHeight(newHeight);

    // 첫 번째 칸의 merge 정보 추가하기
    let newActs = [...acts];
    newActs[start].mergeEnd = end;
    setActs(newActs);

    // 나머지 칸은 안 보이게 하기
    let newDisplay = [...display];
    for (let i = start + 1; i <= end; i++) {
      newDisplay[i] = "none";
    }
    setDisplay(newDisplay);

    setClickedIndexEnd(start);
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

  useEffect(() => {
    console.log(day, "는 ", isFocused);
  }, [isFocused]);

  return (
    <>
      <div className={style.dates}>
        <div className={style.date}>{date}일</div>
        <div className={style.day}>{day}</div>
        <div className={style.minutes}>
          {acts.map((act, idx) => (
            <div
              key={idx}
              className={`${
                // 선택 영역에 회색 background color 주기
                isFocused &&
                Math.min(clickedIndexStart, clickedIndexEnd) <= idx &&
                idx <= Math.max(clickedIndexStart, clickedIndexEnd)
                  ? style.clicked
                  : ""
              } ${
                // 선택 영역에 초록색 테두리 표시하기
                isFocused &&
                (Math.min(clickedIndexStart, clickedIndexEnd) === idx &&
                Math.max(clickedIndexStart, clickedIndexEnd) === idx
                  ? style.clickedAlone
                  : Math.min(clickedIndexStart, clickedIndexEnd) === idx &&
                    Math.max(clickedIndexStart, clickedIndexEnd) !== idx
                  ? style.clickedStart
                  : Math.min(clickedIndexStart, clickedIndexEnd) < idx &&
                    idx < Math.max(clickedIndexStart, clickedIndexEnd)
                  ? style.clickedBetween
                  : Math.min(clickedIndexStart, clickedIndexEnd) !== idx &&
                    Math.max(clickedIndexStart, clickedIndexEnd) === idx
                  ? style.clickedEnd
                  : style.notClicked)
              }`}
              style={{
                // 병합 시 높이 변경
                height: height[idx],
                display: display[idx],
              }}
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
                // 클릭 시 보이는 인풋 태그 (글자 입력 불가)
                <input
                  className={style.notFocused}
                  ref={(element) => {
                    inputRefs1.current[idx] = element;
                  }}
                  // style={{ color: "red" }}
                  value={acts[idx].data}
                  onKeyPress={() => {
                    setFocusedIndex(idx);
                  }}
                  readOnly
                />
              ) : (
                // 포커싱될 시 보이는 인풋 태그 (글자 입력 가능)
                <input
                  ref={(element) => {
                    inputRefs2.current[idx] = element;
                  }}
                  onChange={(e) => {
                    let newActs = [...acts];
                    newActs[idx] = e.target.value;
                    setActs(newActs);
                  }}
                  onKeyDown={(e) => {
                    // TODO : 화살표 키보드 안먹힘 (스크롤만 내려감)
                    if (e.keyCode === 13 || e.keyCode === 34) {
                      setClickedIndexStart(idx + 1);
                      setClickedIndexEnd(idx + 1);
                      setFocusedIndex(idx + 1);
                    }
                  }}
                  value={acts[idx].data}
                />
              )}
            </div>
          ))}
        </div>
      </div>
    </>
  );
}
