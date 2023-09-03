import React from "react";
import style from "./Main.module.css";
import TableHeader from "components/TableHeader";
import Day from "components/Day";

export default function Main() {
  return (
    <>
      <div className={style.table}>
        <TableHeader />
        <Day />
        <Day />
        <Day />
        <Day />
        <Day />
        <Day />
      </div>
    </>
  );
}
