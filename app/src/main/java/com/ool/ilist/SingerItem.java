package com.ool.ilist;

// To Do 리스트 데이터 담기
public class SingerItem {
    String date;
    String toDo;

    // Generate > Constructor
    public SingerItem(String date, String toDo) {
        this.date = date;
        this.toDo = toDo;
    }

    // Generate > Getter and Setter
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }


    // Generate > toString() : 아이템을 문자열로 출력
    @Override
    public String toString() {
        return "SingerItem{" +
                "date='" + date + '\'' +
                ", toDo='" + toDo + '\'' +
                '}';
    }
}