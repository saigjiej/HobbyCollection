package com.example.hobbycollection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ArtActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;  // 통신 매개체
    private ArrayList<Art> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art);


        //Toolbar를 액티비티의 App Bar로 지정
        setSupportActionBar((Toolbar) findViewById(R.id.app_toolbar));
        //Toolbar td = (Toolbar) findViewById(R.id.app_toolbar));
        //setSupportActionBar(tb);

        //툴바 타이틀 지우기
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //뒤로가기 버튼 설정(drawable에 이미지를 등록해서 화살표 대신 사용가능, 사이즈가 충분히 작아야 함)
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);

        //툴바 배경색
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));





        recyclerView = findViewById(R.id.recycierView); // 아이디 연결
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();  // User 객체를 담을 ArrayList (어댑터 쪽으로)

        database = FirebaseDatabase.getInstance();  // 파이어베이스 데이터베이스 연동

        databaseReference = database.getReference("Art");  // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); // 기존 배열리스트가 존재하지 않게 초기화
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {  // 반복문으로 데이터 List를 추출해냄
                    Art art = snapshot.getValue(Art.class);  // 만들어뒀던 User 객체에 데이터를 담는다.
                    arrayList.add(art); // 담은 데이터를 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged();  // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 디비를 가져오던 중 에러 발생 시
                Log.e("ArtActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });

        adapter= new ArtAdapter(arrayList, this);
        recyclerView.setAdapter(adapter); // 리사이클러뷰에 어뎁터 연결

    }



    // 옵션 메뉴 구현
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:{
                startActivity(new Intent(this, UserMainActivity.class));
            }
        }
        return super.onOptionsItemSelected(item);
    }
}