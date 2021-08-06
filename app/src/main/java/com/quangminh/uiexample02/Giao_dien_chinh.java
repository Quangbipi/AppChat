package com.quangminh.uiexample02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.quangminh.uiexample02.Adapter.AccountAdapter;
import com.quangminh.uiexample02.Adapter.ViewPagerAdapter02;

import java.util.ArrayList;
import java.util.List;

public class Giao_dien_chinh extends AppCompatActivity {

    RecyclerView recyclerView;
    AccountAdapter adapter;
    TabLayout tabLayout;
    ViewPager2 viewPager22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_chinh);


        //recyclerView = findViewById(R.id.rcw);
//        friendsList = getFriendsList();
//        adapter= new AccountAdapter(friendsList, this);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(adapter);
//
//        recyclerView.setHasFixedSize(true);

        viewPager22 = findViewById(R.id.viewpagers);
        viewPager22.setAdapter(new ViewPagerAdapter02(this));

        tabLayout = findViewById(R.id.tabs);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager22, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position){
                    case 0:
                        tab.setText("Messenger");
                        tab.setIcon(R.drawable.ic_baseline_message_24);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.ic_baseline_group);
                        tab.setText("Friend");
                        break;
                }

            }
        }
        );
        tabLayoutMediator.attach();


    }
}