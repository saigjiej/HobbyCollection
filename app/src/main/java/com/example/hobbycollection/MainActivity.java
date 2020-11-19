package com.example.hobbycollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// 연락처 Activity로부터 가져온 데이터 표시
public class MainActivity extends AppCompatActivity {
    // 연락처 Activity(ContactActivity)를 실행하기에 앞서,
    // Activity 실행 요청을 식별하기 위한 요청 코드(requestCode)를 정의합니다.
    static final int REQ_ADD_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MainActivity에서 "Add Contact" 버튼 클릭 시, 연락처 Activity(ContactActivity)를 실행
        Button buttonAddContact = (Button) findViewById(R.id.buttonAddContact);
        buttonAddContact.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivityForResult(intent, REQ_ADD_CONTACT);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQ_ADD_CONTACT) {
            if (resultCode == RESULT_OK) {

                // No 값을 int 타입에서 String 타입으로 변환하여 표시.
                TextView textViewNo = (TextView) findViewById(R.id.textViewNo);
                int no = intent.getIntExtra("contact_no", 0);
                textViewNo.setText(Integer.toString(no));

                // Name 값을 String 타입 그대로 표시.
                TextView textViewName = (TextView) findViewById(R.id.textViewName);
                String name = intent.getStringExtra("contact_name");
                if (name != null)
                    textViewName.setText(name);


                // Phone 값을 String 타입 그대로 표시.
                TextView textViewPhone = (TextView) findViewById(R.id.textViewPhone);
                String phone = intent.getStringExtra("contact_phone");
                if (phone != null)
                    textViewName.setText(phone);

                TextView textViewOver20 = (TextView) findViewById(R.id.textViewOver20);
                boolean over20 = intent.getBooleanExtra("contact_over20", false);
                if (over20)
                    textViewOver20.setText("Over 20");
                else
                    textViewOver20.setText("Not over 20");


            }
        }
    }

}