package com.quangminh.uiexample02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.RotateDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.makeramen.roundedimageview.RoundedImageView;
import com.quangminh.uiexample02.Adapter.AccountAdapter;
import com.quangminh.uiexample02.model.AccountUser;

public class MainActivity3 extends AppCompatActivity {

    ImageButton next, male, female;

    RoundedImageView avatar;
    String gender;

    DatabaseReference mData;
    AccountUser user;
    String account;
    String password;
    String hoTen;
    String dateOfbirth;
    String phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_ky_3);
        next = findViewById(R.id.next3);
        male = findViewById(R.id.imageButton2);
        female = findViewById(R.id.imageButton3);
        avatar=findViewById(R.id.roundedImageView);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
             account = bundle.getString("acc");
             password = bundle.getString("pass");
             hoTen = bundle.getString("hoten");
             dateOfbirth = bundle.getString("date");
             phoneNumber = bundle.getString("phone1");
        }




        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender="male";
                Toast.makeText(MainActivity3.this, gender, Toast.LENGTH_SHORT).show();
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender="female";
                Toast.makeText(MainActivity3.this, gender, Toast.LENGTH_SHORT).show();
            }
        });




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //user = new AccountUser(account,password, hoTen, dateOfbirth, phoneNumber, gender);

                mData = FirebaseDatabase.getInstance("https://boxchat-2b7f0-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
                mData.child(phoneNumber).setValue(user);
                opentDialog(Gravity.CENTER);
                //showDialog();

            }

        });

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity3.this, "select", Toast.LENGTH_SHORT).show();
                oentDialogImg(Gravity.BOTTOM);
            }
        });
    }

    private void oentDialogImg(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_picker_image);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttribuites = window.getAttributes();
        windowAttribuites.gravity = gravity;
        window.setAttributes(windowAttribuites);

        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }
        dialog.show();
    }

    private void opentDialog(int gravity){

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
                Intent i = new Intent(MainActivity3.this, Dang_nhap.class);
                startActivity(i);
            }
        });

        dialog.show();

    }
    private void showDialog(){

//        ViewGroup viewGroup = findViewById(android.R.id.content);

//        View view = LayoutInflater.from(this).inflate(R.layout.layout_dialog_1,getApplicationContext(),false);

        AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setView(R.layout.layout_dialog_1);

//        AlertDialog alertDialog = builder.create();
        builder.show();
    }
}