import React from "react";
import style from "./Header.module.css";
import { Outlet } from "react-router-dom";

export default function Header() {
  return (
    <>
      <div className={style.container}>
        <img src="logo192.png" alt="로고" className={style.logo} />
        <h1>탐탐</h1>
        <div>시간을 어떻게 쓰고 있는지 분석해봐요</div>
      </div>
      <Outlet />
    </>
  );
}
