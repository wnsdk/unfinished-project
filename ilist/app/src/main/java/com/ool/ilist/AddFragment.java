package com.ool.ilist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddFragment extends Fragment {

    EditText editTextDate;
    EditText editTextToDo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_add, container, false);

        editTextDate = rootView.findViewById(R.id.editTextDate);
        editTextToDo = rootView.findViewById(R.id.editTextToDo);

        // 버튼 눌렀을 때 리스트뷰에 포함되도록 처리
        Button button = rootView.findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = editTextDate.getText().toString();
                String toDo = editTextToDo.getText().toString();

                MainActivity activity = (MainActivity) getActivity();
                activity.adapter.addItem(new SingerItem(date, toDo));
                activity.adapter.notifyDataSetChanged();
                activity.onFragmentChangedIntoMenu(0);
            }
        });

        return rootView;
    }


}