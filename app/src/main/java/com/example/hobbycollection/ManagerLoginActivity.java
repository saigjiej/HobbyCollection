package com.example.hobbycollection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ManagerLoginActivity extends AppCompatActivity {
    private static final String TAG = "ManagerLoginActivity";
    // 파이어베이스 등록
    // FirebaseAuth의 인스턴스를 선언
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_login);

        // 하나의 인스턴스만 가지고 사용
        // onCreate() 메서드에서 FirebaseAuth 인스턴스를 초기화
        mAuth = FirebaseAuth.getInstance();

        // 로그인 버튼
        findViewById(R.id.login_btn).setOnClickListener(onClickListener);

    }

    // 활동을 초기화할 때 사용자가 현재 로그인되어 있는지 확인합니다.
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    // View에 있는 버튼 클릭 시
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                //로그인 버튼 클릭 시
                case R.id.login_btn:
                    Log.e("클릭", "로그인");
                    // 로그인 로직
                    signIn();
                    break;

            }
        }
    };

    // 로그인 로직
    private void signIn() {
        // 이메일 , 비밀번호 받기
        String email = ((EditText) findViewById(R.id.email_input)).getText().toString();
        String password = ((EditText) findViewById(R.id.password_input)).getText().toString();

        // 이메일과 패스워드가 적혀있을 경우
        if (email.length() > 0 && password.length() > 0) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(ManagerLoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // 성공 시
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(ManagerLoginActivity.this, "Salt Shaker에 오신 것을 환영합니다!", Toast.LENGTH_LONG).show();
                                // 회원 activity로 이동
                                startManagerSelectActivity();

                            } else {   // 실패 시
                                if (task.getException() != null) {
                                    Toast.makeText(ManagerLoginActivity.this, "로그인을 다시 시도해주세요", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });

        } else {    // 이메일과 비밀번호를 입력하지 않았을 경우
            Toast.makeText(ManagerLoginActivity.this, "이메일 또는 비밀번호를 입력해주세요", Toast.LENGTH_LONG).show();
        }
    }

    // 메인 엑티비티로 이동
    private void startManagerSelectActivity(){
        Intent intent = new Intent(this,ManagerSelectActivity.class);
        // 앱이 꺼짐
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}