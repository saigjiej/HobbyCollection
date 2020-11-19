package com.example.hobbycollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

// 연락처 Activity에서 입력받은 연락처 전달하기
public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // "Save" 버튼을 눌렀을 경우에만 가능
        Button buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Intent 객체 생성.
                Intent intent = new Intent();


                // No 입력 값을 int 값으로 변환하여 전달.
                EditText editTextNo = (EditText) findViewById(R.id.editTextNo);
                String strNo = editTextNo.getText().toString();
                if (!strNo.isEmpty() && strNo.matches("^[0-9]*$")) {   // RegEX로 번호 확인
                    intent.putExtra("contact_no", Integer.parseInt(strNo));
                } else {
                    intent.putExtra("contact_no", 0);
                }

                // Name 입력 값을 String 값으로 그대로 전달
                EditText editTextName = (EditText) findViewById(R.id.editTextName);
                intent.putExtra("contact_name", editTextName.getText().toString());

                // Phone 입력 값을 String 값으로 그대로 전달
                EditText editTextPhone = (EditText) findViewById(R.id.editTextPhone);
                intent.putExtra("contact_phone", editTextPhone.getText().toString());

                CheckBox checkBoxOver20 = (CheckBox) findViewById(R.id.checkBoxOver20);
                intent.putExtra("contact_over20", checkBoxOver20.isChecked());

                setResult(RESULT_OK, intent);
                finish();

            }
        });


    }
}