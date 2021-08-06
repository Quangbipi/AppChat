package com.quangminh.uiexample02;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.quangminh.uiexample02.Adapter.AccountAdapter;
import com.quangminh.uiexample02.model.AccountUser;

import java.util.ArrayList;
import java.util.List;


public class UserFragment extends Fragment {



    View view;
    RecyclerView UserList;
    AccountAdapter adapter;
    List<AccountUser> accountUsers;

    DatabaseReference databaseReference;
    FirebaseUser user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);
        UserList = view.findViewById(R.id.rcw);


        accountUsers = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        UserList.setLayoutManager(linearLayoutManager);
        UserList.setHasFixedSize(true);

        readUser();
        return view;
    }

    private  void readUser(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Account");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                accountUsers.clear();

                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    AccountUser accountUser = snapshot1.getValue(AccountUser.class);

                    assert accountUser != null;
                    assert  user != null;
                    if(!accountUser.getUserId().equals(user.getUid())){
                        accountUsers.add(accountUser);
                    }
                }
                adapter= new AccountAdapter(accountUsers, getContext());
                UserList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}