package com.quangminh.uiexample02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Dang_nhap extends AppCompatActivity {

    TextView dangKy;
    Button dangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_nhap_01);

        dangKy = findViewById(R.id.textView12);
        dangNhap = findViewById(R.id.dnbt);

        dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dang_nhap.this, Setting.class);
                startActivity(i);
            }
        });

        dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dang_nhap.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}