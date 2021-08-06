package com.quangminh.uiexample02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.quangminh.uiexample02.model.AccountUser;

public class Setting extends AppCompatActivity {

    TextView UserName, logOut, thongTin;

    String userId;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    FirebaseUser user;

    ImageButton imageMess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        UserName = findViewById(R.id.username);
        logOut = findViewById(R.id.logouttv);
        thongTin = findViewById(R.id.thongtin);
        imageMess = findViewById(R.id.mess);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Account");
        userId = user.getUid();

        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AccountUser accountUser = snapshot.getValue(AccountUser.class);

                if(accountUser!=null){

                    String userName = accountUser.getName();
                    UserName.setText(userName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Setting.this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Setting.this, Dang_nhap.class));
            }
        });

        thongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setting.this, ProfileActivity.class));
            }
        });
        imageMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setting.this, Giao_dien_chinh.class));
            }
        });


    }
}