package com.example.hobbycollection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class PayActivity extends AppCompatActivity  {
    // 입력
    private static final String TAG = "PayActivity";
    private FirebaseUser user;
    private Spinner spinner;
    private TextView tv_result;
    private Spinner spinner2;
    private TextView tv_result2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        //Toolbar를 액티비티의 App Bar로 지정
        setSupportActionBar((Toolbar) findViewById(R.id.app_toolbar));
        //Toolbar td = (Toolbar) findViewById(R.id.app_toolbar));
        //setSupportActionBar(tb);

        //툴바 타이틀 지우기
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //툴바 배경색
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        // 결재하기 버튼
        findViewById(R.id.pay).setOnClickListener(onClickListener);

        spinner = (Spinner)findViewById(R.id.spinner);
        tv_result = (TextView)findViewById(R.id.tv_result);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tv_result.setText(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2 = (Spinner)findViewById(R.id.spinner2);
        tv_result2 = (TextView)findViewById(R.id.tv_result2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tv_result2.setText(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.pay:
                    payUpdate();
                    break;
            }
        }
    };

    private void payUpdate() {
        final String order = ((TextView) findViewById(R.id.tv_result)).getText().toString();
        final String name = ((EditText) findViewById(R.id.et_name)).getText().toString();
        final String phoneNumber = ((EditText) findViewById(R.id.et_phoneNumber)).getText().toString();
        final String number = ((TextView) findViewById(R.id.tv_result2)).getText().toString();

        if(order.length() > 0) {
            user = FirebaseAuth.getInstance().getCurrentUser();
            AddInfo addInfo = new AddInfo(order, name, phoneNumber, number, user.getUid());
            uploader(addInfo);
            startToast("정보가 입력되었습니다.");

        } else {
            startToast("회원정보를 입력해주세요.");
        }

    }

    private void uploader(AddInfo addInfo) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("posts").add(addInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Error adding document", e);
                    }
                });
    }


    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}