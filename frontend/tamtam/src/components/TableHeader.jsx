// 시간표 좌측 탭

import React from "react";
import style from "./TableHeader.module.css";

export default function TableHeader({ month }) {
  return (
    <>
      <div className={style.days}>
        <div>{month}월 September</div>
        {Array.from({ length: 24 }).map((item, idx) => (
          <div className={style.hours} key={idx}>
            <div>{idx}시</div>
            <div className={style.minutes}>
              <div>00</div>
              <div>10</div>
              <div>20</div>
              <div>30</div>
              <div>40</div>
              <div>50</div>
            </div>
          </div>
        ))}
      </div>
    </>
  );
}
