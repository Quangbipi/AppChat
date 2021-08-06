package com.quangminh.uiexample02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class Login extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewPager;
    Button dangnhap, dangky;

    TextView textView, textView1;
    PhotoAdapter adapter;
    CircleIndicator circleIndicator;
    private List<Photo> mLists;
    private Timer timer;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d("log", "onCreate");
        viewPager = findViewById(R.id.view_pager);
        circleIndicator = findViewById(R.id.circle);
        textView = findViewById(R.id.title1);
        textView1 = findViewById(R.id.tvnd);
        dangnhap = findViewById(R.id.btdn);
        dangky = findViewById(R.id.btdk);

        mLists=getListPhoto();
        adapter = new PhotoAdapter(this, mLists);


        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);
        adapter.registerDataSetObserver(circleIndicator.getDataSetObserver());


        autoSlideImage();
        Log.d("log", curent() +"");

        dangnhap.setOnClickListener(this);
        dangky.setOnClickListener(this);

    }

    //auto login
    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser!=null){
            Intent intent = new Intent(Login.this, Setting.class);
            startActivity(intent);
            finish();
        }
    }

    private void autoSlideImage() {
        if(mLists==null|| mLists.isEmpty()|| viewPager==null){
            return;
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {

                        int currentItem= viewPager.getCurrentItem();
                        int totalItem = mLists.size()-1;
                        if(currentItem<totalItem){
                            currentItem++;
                            if(currentItem==1){
                                textView.setText("Cảm Xúc");
                                textView1.setText("Gửi trọn cảm xúc của bạn thông qua nhứng tin nhắn");
                            }else if(currentItem==2){
                                textView.setText("Kết Nối");
                                textView1.setText("Giúp bạn kết nối với những người bạn yêu thương nhất");
                            }
                            viewPager.setCurrentItem(currentItem);

                        }else {
                            viewPager.setCurrentItem(0);
                            textView.setText("Chào Mừng");
                            textView1.setText("Chào mừng bạn đến với Mini Messenger");
                        }
                    }
                });

            }
        }, 500, 3000);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
    }

    private List<Photo> getListPhoto(){
        List<Photo> photoList = new ArrayList<>();
        photoList.add(new Photo(R.drawable.login_1));
        photoList.add(new Photo(R.drawable.login2));
        photoList.add(new Photo(R.drawable.login_3));
        return photoList;
    }

    private int curent(){
        int currentItem = viewPager.getCurrentItem();
        return currentItem;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btdn:
                Intent intent = new Intent(Login.this, Dang_nhap.class);
                startActivity(intent);
                break;
            case R.id.btdk:
                Intent i = new Intent(Login.this, Dang_ky.class);
                startActivity(i);
                break;
        }
    }
}