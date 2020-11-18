package com.example.hobbycollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        // 사용자 버튼을 클릭할 시
        Button userButton = (Button) findViewById(R.id.userbtn);
        userButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 로그인 액티비티로 이동
                startLoginActivity();
            }
        }) ;

        // 관리자 버튼을 클릭할 시
        Button mamagerButton = (Button) findViewById(R.id.mamagerbtn);
        mamagerButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 관리자 로그인 액티비티로 이동
                startMamagerLoginActivity();
            }
        }) ;


    }

    // 사용자 로그인 액티비티
    private void startLoginActivity() {
        Intent intent = new Intent(this, UserLoginAcitvity.class);
        startActivity(intent);
    }

    // 관리자 로그인 액티비티
   private void startMamagerLoginActivity() {
        Intent intent = new Intent(this, ManagerLoginActivity.class);
        startActivity(intent);
    }
}
