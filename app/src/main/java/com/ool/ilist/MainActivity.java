package com.ool.ilist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private long backBtnTime = 0;

    private TextView tv_nickname;
    private ImageView iv_profile;

    SingerAdapter adapter;
    EditText editTextDate;
    EditText editTextToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 로그인 결과
        Intent intent = getIntent();

        tv_nickname = findViewById(R.id.tv_nickname);
        tv_nickname.setText(intent.getStringExtra("nickName"));

        iv_profile = findViewById(R.id.iv_profile);
        Glide.with(this).load(intent.getStringExtra("photoUrl")).into(iv_profile); // 프로필 url을 이미지에 셋팅


        // 2. 스케쥴 관리
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        editTextToDo = (EditText) findViewById(R.id.editTextToDo);

        ListView listView = (ListView) findViewById(R.id.listView);

        // 어댑터 안에 데이터 담기
        adapter = new SingerAdapter();
        adapter.addItem(new SingerItem("12월 10일(금)", "2차평가(정보보호개론, 인터넷프로토콜)"));
        adapter.addItem(new SingerItem("01월 07일(금)", "3차평가(소프트웨어공학)"));
        adapter.addItem(new SingerItem("01월 14일(금)", "4차평가(멀티미디어개론)"));
        adapter.addItem(new SingerItem("02월 11일(금)", "5차평가(데이터베이스)"));
        adapter.addItem(new SingerItem("02월 18일(금)", "6차평가(정보처리)"));

        // 리스트 뷰에 어댑터 설정
        listView.setAdapter(adapter);

        // 이벤트 처리 리스너 설정
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "선택 :"+item.getDate(), Toast.LENGTH_LONG).show();
            }
        });

        // 버튼 눌렀을 때 리스트뷰에 포함되도록 처리
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = editTextDate.getText().toString();
                String toDo = editTextToDo.getText().toString();

                adapter.addItem(new SingerItem(date, toDo));
                adapter.notifyDataSetChanged();
            }
        });

        // 이미 들어가 있는 데이터들에 한해서 정렬해 줌(새로 추가되는 데이터에 대한 정렬 기능 필요!!)
        Comparator<SingerItem> dataAsc = new Comparator<SingerItem>() {
            @Override
            public int compare(SingerItem o1, SingerItem o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        };
        Collections.sort(adapter.items, dataAsc);
        adapter.notifyDataSetChanged();
    }

    class SingerAdapter extends BaseAdapter {
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();

        // Generate > implement methods
        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 뷰 객체 재사용
            SingerItemView view = null;
            if (convertView == null) {
                view = new SingerItemView(getApplicationContext());
            } else {
                view = (SingerItemView) convertView;
            }

            SingerItem item = items.get(position);

            view.setDate(item.getDate());
            view.setToDo(item.getToDo());


            return view;
        }
    }

    // 3. 뒤로 가기 버튼 2번 눌러 종료하기
    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0 <= gapTime && 2000 >= gapTime) {
            super.onBackPressed();
        }
        else {
            backBtnTime = curTime;
            Toast.makeText(this, "한 번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
        }
    }
}