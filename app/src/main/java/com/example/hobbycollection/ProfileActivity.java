package com.example.hobbycollection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private Button back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textViewUserEmail = (TextView) findViewById(R.id.textviewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        back = (Button) findViewById(R.id.back);


        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginAcitvity.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        textViewUserEmail.setText(user.getEmail()+ "으로 로그인하였습니다.");
        buttonLogout.setOnClickListener(onClickListener);
        back.setOnClickListener(onClickListener);


        FirebaseUser users = FirebaseAuth.getInstance().getCurrentUser();

        if (users == null) {
            myStartActivity(LoginAcitvity.class);
        } else {
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            // 로그인 됐을 때 recyclerView 초기화
            final ArrayList<AddInfo> postList = new ArrayList<>();
            db.collection("posts")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    postList.add(new AddInfo(
                                            document.getData().get("order").toString(),
                                            document.getData().get("name").toString(),
                                            document.getData().get("phoneNumber").toString(),
                                            document.getData().get("number").toString(),
                                            document.getData().get("publisher").toString()));
                                }
                                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));

                                RecyclerView.Adapter mAdapter = new PayAdapter(ProfileActivity.this, postList);
                                recyclerView.setAdapter(mAdapter);
                            } else {
                                Log.d(TAG, "Error getting documents : ", task.getException());
                            }
                        }
                    });


        }


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == buttonLogout) {
                firebaseAuth.getInstance().signOut();
                myStartActivity(LoginAcitvity.class);
                finish();
            }
            if(view == back) {
                startUserMainActivity();
            }
        }
    };

    private void myStartActivity(Class c){
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 앱이 꺼짐
        startActivity(intent);
    }

    private void startUserMainActivity() {
        Intent intent = new Intent(this, UserMainActivity.class);
        startActivity(intent);
    }

}