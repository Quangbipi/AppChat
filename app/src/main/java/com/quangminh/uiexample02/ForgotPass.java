package com.quangminh.uiexample02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPass extends AppCompatActivity {

    Button request;
    EditText replaceTk;

    ProgressBar progressBar;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        request = findViewById(R.id.request);
        progressBar = findViewById(R.id.progressBar2);
        replaceTk = findViewById(R.id.editTextTextPersonName);

        mAuth = FirebaseAuth.getInstance();

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestPass();
            }
        });
        progressBar.setVisibility(View.GONE);
    }

    private void RequestPass() {
        String email = replaceTk.getText().toString().trim();

        if(email.isEmpty()){
            replaceTk.setError("Không được bỏ trống");
            replaceTk.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            replaceTk.setError("Email không hợp lệ");
            replaceTk.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(ForgotPass.this, "Kiểm tra email của bạn để lấy mật khẩu mới", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ForgotPass.this, Dang_nhap.class));
                }else{
                    Toast.makeText(ForgotPass.this, "Đã xảy ra lỗi vui lòng thử lại", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}