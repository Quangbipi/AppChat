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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class Dang_nhap extends AppCompatActivity {

    TextView dangKy, forgotMk;
    Button dangNhap;
    EditText taiKhoan, matKhau;

    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_nhap_01);

        forgotMk = findViewById(R.id.fogotmk);
        dangKy = findViewById(R.id.textView12);
        dangNhap = findViewById(R.id.dnbt);
        taiKhoan = findViewById(R.id.edtsignin);
        matKhau = findViewById(R.id.passsignin);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               signInUser();
            }
        });

        dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dang_nhap.this, Dang_ky.class);
                startActivity(intent);
            }
        });

        forgotMk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dang_nhap.this, ForgotPass.class));
            }
        });
        progressBar.setVisibility(View.GONE);
    }

    private void signInUser() {
        String email = taiKhoan.getText().toString().trim();
        String pass = matKhau.getText().toString().trim();

        if(email.isEmpty()){
            taiKhoan.setError("Không được bỏ trống");
            taiKhoan.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            taiKhoan.setError("Email không hợp lệ");
            taiKhoan.requestFocus();
            return;
        }

        if (pass.isEmpty()){
            matKhau.setError("Hãy nhập mật khẩu");
            matKhau.requestFocus();
            return;
        }

        if (pass.length()<6){
            matKhau.setError("Mật khẩu phải lớn hơn 6");
            matKhau.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Intent i = new Intent(Dang_nhap.this, Setting.class);
                    startActivity(i);
                    progressBar.setVisibility(View.GONE);

                }else{
                    Toast.makeText(Dang_nhap.this, "Tài khoản hoặc mật khẩu không chihs xác", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}