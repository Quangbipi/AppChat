package com.quangminh.uiexample02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.accounts.Account;
import android.os.Bundle;

import com.quangminh.uiexample02.Adapter.AccountAdapter;
import com.quangminh.uiexample02.model.Friends;

import java.util.ArrayList;
import java.util.List;

public class Giao_dien_chinh extends AppCompatActivity {

    RecyclerView recyclerView;
    AccountAdapter adapter;

    List<Friends> friendsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_chinh);


        recyclerView = findViewById(R.id.rcw);
        friendsList = getFriendsList();
        adapter= new AccountAdapter(friendsList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.setHasFixedSize(true);


    }



    private List<Friends> getFriendsList(){
        friendsList = new ArrayList<>();
        friendsList.add(new Friends("Khá Bảnh", R.drawable.avt1, "abcdxyz", "10/7/2021"));
        friendsList.add(new Friends("Khá Bảnh", R.drawable.avt2, "abcdxyz", "10/7/2021"));
        friendsList.add(new Friends("Khá Bảnh", R.drawable.avt3, "abcdxyz", "10/7/2021"));
        friendsList.add(new Friends("Khá Bảnh", R.drawable.avt2, "abcdxyz", "10/7/2021"));
        friendsList.add(new Friends("Khá Bảnh", R.drawable.avt3, "abcdxyz", "10/7/2021"));
        friendsList.add(new Friends("Khá Bảnh", R.drawable.avt1, "abcdxyz", "10/7/2021"));
        friendsList.add(new Friends("Khá Bảnh", R.drawable.avt1, "abcdxyz", "10/7/2021"));
        friendsList.add(new Friends("Khá Bảnh", R.drawable.avt2, "abcdxyz", "10/7/2021"));
        friendsList.add(new Friends("Khá Bảnh", R.drawable.avt3, "abcdxyz", "10/7/2021"));
        friendsList.add(new Friends("Khá Bảnh", R.drawable.avt2, "abcdxyz", "10/7/2021"));
        friendsList.add(new Friends("Khá Bảnh", R.drawable.avt3, "abcdxyz", "10/7/2021"));
        friendsList.add(new Friends("Khá Bảnh", R.drawable.avt1, "abcdxyz", "10/7/2021"));
        return friendsList;
    }
}