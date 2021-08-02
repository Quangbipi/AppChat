package com.quangminh.uiexample02;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class Fragment_1 extends Fragment {

    public static final String TAG = Fragment_1.class.getName();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    String acc ;
    String pass;
    String acc2;
    Dang_ky mMainActivity;
    EditText account, password, replacepass;
    ImageButton imageButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_1, container, false);
        imageButton = view.findViewById(R.id.next11);

        mMainActivity = (Dang_ky) getActivity();
        account = view.findViewById(R.id.accountdk);
        password = view.findViewById(R.id.passdk);
        replacepass = view.findViewById(R.id.replacepass);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acc = account.getText().toString().trim();
                pass = password.getText().toString().trim();
                acc2 = replacepass.getText().toString().trim();
                if(isValitk()&&isValimk()&&isEquals()) {
                    mOnButtonClickListener.onButtonClicked(v);
                    sendData();
                }else {
                    Toast.makeText(getContext(), "Tài khoản hoặc mật khẩu không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }


    private OnButtonClickListener mOnButtonClickListener;

    interface OnButtonClickListener{
        void onButtonClicked(View view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnButtonClickListener = (OnButtonClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(((Activity) context).getLocalClassName()
                    + " must implement OnButtonClickListener");
        }
    }

    public void sendData(){


        mMainActivity.setAcc(acc);
        mMainActivity.setPass(pass);



    }
    public boolean isValitk(){
        return !TextUtils.isEmpty(acc) && Patterns.EMAIL_ADDRESS.matcher(acc).matches();
    }

    public boolean isValimk(){
        return !TextUtils.isEmpty(pass) && pass.length() >=6;
    }

    public  boolean isEquals(){
        return acc2.equals(pass);
    }
}