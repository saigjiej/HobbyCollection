package com.example.hobbycollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;


public class UserMainActivity extends AppCompatActivity {
    private static final String TAG = "UserMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        //Toolbar를 액티비티의 App Bar로 지정
        setSupportActionBar((Toolbar) findViewById(R.id.app_toolbar));

        //툴바 타이틀 지우기
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //뒤로가기 버튼 설정(drawable에 이미지를 등록해서 화살표 대신 사용가능, 사이즈가 충분히 작아야 함)
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);

        //툴바 배경색
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        // 회원 정보 입력
        findViewById(R.id.user).setOnClickListener(onClickListener);

        //요리 수강 리스트 버튼
        findViewById(R.id.cooking).setOnClickListener(onClickListener);

        //미술 수강 리스트 버튼
        findViewById(R.id.art).setOnClickListener(onClickListener);

        //공예 수강 리스트 버튼
        findViewById(R.id.crafts).setOnClickListener(onClickListener);

        //리뷰 버튼
        findViewById(R.id.review).setOnClickListener(onClickListener);





    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.cooking:
                    Log.e("클릭", "요리");
                    // 요리 수강 리스트 Activity으로 이동
                    startCookingActivity();
                    break;
                case R.id.art:
                    Log.e("클릭", "미술");
                    // 미술 수강 리스트 Activity으로 이동
                    startArtActivity();
                    break;
                case R.id.crafts:
                    Log.e("클릭", "공예");
                    // 공예 수강 리스트 Activity으로 이동
                    startCraftsActivity();
                    break;
                case R.id.user:
                    Log.e("클릭", "회원정보");
                    // 회원정보 Activity으로 이동
                    startProfileActivity();
                    break;
                case R.id.review:
                    Log.e("클릭", "리뷰");
                    // 회원정보 Activity으로 이동
                    startReviewActivity();
                    break;

            }
        }
    };



    // 요리 수강 리스트 Activity
    private void startCookingActivity() {
        Intent intent = new Intent(this, CookActivity.class);
        startActivity(intent);
    }

    // 미술 수강 리스트 Activity
    private void startArtActivity() {
        Intent intent = new Intent(this, ArtActivity.class);
        startActivity(intent);
    }

    // 공예 수강 리스트 Activity
    private void startCraftsActivity() {
        Intent intent = new Intent(this, CraftsActivity.class);
        startActivity(intent);
    }

    // 로그인 Activity
    private void startProfileActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    // 리뷰 Activity
    private void startReviewActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }





    // 옵션 메뉴 구현
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:{
                startActivity(new Intent(this, LoginAcitvity.class));
            }
        }
        return super.onOptionsItemSelected(item);
    }

}