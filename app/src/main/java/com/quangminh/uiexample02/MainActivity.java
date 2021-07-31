package com.quangminh.uiexample02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageButton button;
    EditText account1, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_ky_1);

        button = findViewById(R.id.next1);
        account1 = findViewById(R.id.accountdk);
        password = findViewById(R.id.passdk);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putString("account", account());
                bundle.putString("pass", password());
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }

    private String account(){
        String account = account1.getText().toString().trim();
        return account;
    }
    private String password(){
        String pass = password.getText().toString().trim();
        return pass;
    }
}