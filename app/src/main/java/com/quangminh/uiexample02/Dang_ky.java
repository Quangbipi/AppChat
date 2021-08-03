package com.quangminh.uiexample02;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
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

import static com.quangminh.uiexample02.Fragment_3.GALLER_ACTION_PICK_CODE;

public class Dang_ky extends AppCompatActivity implements Fragment_1.OnButtonClickListener, Fragment_2.OnButtonClickListener, Fragment_3.OnButtonClickListener {

    ViewPager2 viewPager2;

    private static final int REQUEST_PERMISSION_CODE = 0;
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
        runTimePermission();




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
                            opentDialog(Gravity.CENTER);
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
                            Log.d("ddd", task.getException()+"");

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

    public void opentDialog(int gravity){

        final Dialog dialog = new Dialog(this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_1);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttribuites = window.getAttributes();
        windowAttribuites.gravity = gravity;
        window.setAttributes(windowAttribuites);

        if(Gravity.BOTTOM==gravity){

            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }

        TextView textView = dialog.findViewById(R.id.tvdialog);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dang_ky.this, Dang_nhap.class);
                startActivity(i);
            }
        });

        dialog.show();

    }

    private void runTimePermission(){
        if(Build.VERSION.SDK_INT>=23 && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
            }, REQUEST_PERMISSION_CODE);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==REQUEST_PERMISSION_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            }else{
                runTimePermission();
            }
        }
    }
    private void takePhoto() {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i, GALLER_ACTION_PICK_CODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            if(requestCode == GALLER_ACTION_PICK_CODE){

            }
        }
    }


}