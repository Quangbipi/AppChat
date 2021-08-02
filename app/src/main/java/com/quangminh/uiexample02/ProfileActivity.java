package com.quangminh.uiexample02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    FirebaseUser user;
    String userId;

    TextView name, mail, phoneNumber, dateofBirth, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Anhxa();

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Account");
        userId = user.getUid();

        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AccountUser accountUser = snapshot.getValue(AccountUser.class);

                if(accountUser!=null){
//                    String hoten = accountUser.name;
//                    String email = accountUser.account;
//                    String soDt = accountUser.phoneNumber;
//                    String ngaySinh = accountUser.dateOfBirth;
//                    String
                    name.setText(accountUser.name);
                    mail.setText(accountUser.account);
                    phoneNumber.setText(accountUser.phoneNumber);
                    dateofBirth.setText(accountUser.dateOfBirth);
                    gender.setText(accountUser.gender);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ProfileActivity.this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void Anhxa(){
        name = findViewById(R.id.textView14);
        mail = findViewById(R.id.hienmail);
        phoneNumber = findViewById(R.id.sdt);
        dateofBirth = findViewById(R.id.hiendate);
        gender = findViewById(R.id.gt);

    }
}