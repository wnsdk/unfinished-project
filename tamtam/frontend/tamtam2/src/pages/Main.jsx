import { React, useState } from "react";
import style from "./Main.module.css";

export default function Main() {
  return (
    <>
      <div className={style.container}>
        {/* 날짜 로우 */}
        <div className={style.container_days}>
          {/* 투명 박스 */}
          <div
            style={{
              width: "76px",
              height: "55px",
              backgroundColor: "#ffffff",
              opacity: "0",
            }}
          ></div>
          {/* 날짜 */}
          <div className={style.days}>
            {Array.from({ length: 7 }).map((hour, idx) => (
              <div key={idx} className={style.day}>
                {idx < 2 ? (
                  <>
                    <div className={style.day_past}>25</div>
                    <div className={style.week_past}>월</div>
                  </>
                ) : idx === 2 ? (
                  <>
                    <div className={style.circle_now}></div>
                    <div className={style.day_now}>25</div>
                    <div className={style.week_now}>월</div>
                  </>
                ) : (
                  <>
                    <div className={style.day_future}>25</div>
                    <div className={style.week_future}>월</div>
                  </>
                )}
              </div>
            ))}
          </div>
        </div>
        {/* 달력 로우 */}
        <div className={style.container_hours}>
          {/* 좌측 시각 컬럼 */}
          <div className={style.hours}>
            {Array.from({ length: 26 }).map((hour, idx) => (
              <div key={idx} className={style.hour}>
                {0 < idx && idx < 12 && <div>오전 {idx}시</div>}
                {idx === 12 && <div>오후 {idx}시</div>}
                {12 < idx && idx < 25 && <div>오후 {idx - 12}시</div>}
              </div>
            ))}
          </div>
          <div className={style.container_events}>
            {Array.from({ length: 7 }).map((hour, idx) => (
              <div key={idx} className={style.events}>
                <div className={style.event}>
                  <div className={style.event_box}></div>
                  <div className={style.event_text}>식사</div>
                  <div className={style.event_text}>오전 2시~ 8시</div>
                </div>
              </div>
            ))}
          </div>
          {/* <div style="width: 15px; height: 805px; position: relative"> */}
          {/* <div style="width: 15px; height: 805px; left: 0px; top: 0px; position: absolute"></div> */}
          {/* <div style="width: 10px; height: 432px; left: 3px; top: 108px; position: absolute; background: #D9D9D9; border-radius: 10px"></div> */}
          {/* </div> */}
        </div>
      </div>
    </>
  );
}
