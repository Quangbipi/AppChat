package com.quangminh.uiexample02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.quangminh.uiexample02.Adapter.ViewpagerAdapter;
import com.quangminh.uiexample02.model.AccountUser;

import java.util.Random;

public class Dang_ky extends AppCompatActivity implements Fragment_1.OnButtonClickListener, Fragment_2.OnButtonClickListener, Fragment_3.OnButtonClickListener {

    ViewPager2 viewPager2;

    DatabaseReference mData;
    FirebaseAuth mAuth;
    String pass="";
    String acc="";
    String name = "";
    String date = "";
    String phone = "";
    String gender = "";
    Fragment_3 fragment_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        viewPager2 = findViewById(R.id.viewpager);

        viewPager2.setAdapter(new ViewpagerAdapter(this));

        viewPager2.setUserInputEnabled(false);
        fragment_3 = new Fragment_3();



    }


    @Override
    public void onButtonClicked(View view) {
        mAuth = FirebaseAuth.getInstance();
        int curr = viewPager2.getCurrentItem();
        switch (view.getId()){
            case R.id.next11:
                viewPager2.setCurrentItem(curr+1);

                break;
            case R.id.next22:
                viewPager2.setCurrentItem(curr+2);
                break;
            case R.id.next33:

                mAuth = FirebaseAuth.getInstance();
                //object đẩy lên
                AccountUser accountUser = new AccountUser(acc, pass, name, date, phone, gender);

                mAuth.createUserWithEmailAndPassword(acc, pass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //nếu đăng ký thành công
                        if(task.isSuccessful()){
                            //mở dialog thông báo
                            fragment_3.opentDialog(Gravity.CENTER);
                            // realtime db lên
                            FirebaseDatabase.getInstance("https://boxchat-2b7f0-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Account")
                                    .child(FirebaseAuth.getInstance().getUid()).setValue(accountUser)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Dang_ky.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                                    }else {
                                        Toast.makeText(Dang_ky.this, "Đăng ký thất bại ...." , Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }else {
                            Toast.makeText(Dang_ky.this, "Đăng ký thất bại 333" + task, Toast.LENGTH_SHORT).show();
                            Log.d("log", task +"");
                        }

//
                    }
                });


        }
    }


    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
//    mData = FirebaseDatabase.getInstance("https://boxchat-2b7f0-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
//                mData.child("Account").push().setValue(accountUser);
}