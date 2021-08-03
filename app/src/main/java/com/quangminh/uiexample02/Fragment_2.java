package com.quangminh.uiexample02;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Calendar;
import java.util.TimeZone;


public class Fragment_2 extends Fragment {


    public Fragment_2() {
        // Required empty public constructor
    }


    String nam ;
    String date ;
    String phone;
    Dang_ky mDangky;
    EditText name, datofBirth, phoneNumber;
    ImageButton imageButton2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        mDangky = (Dang_ky) getActivity();
        imageButton2 = view.findViewById(R.id.next22);
        name = view.findViewById(R.id.hotendk1);
        datofBirth = view.findViewById(R.id.edsn1);
        phoneNumber = view.findViewById(R.id.phonedk1);

        datofBirth = view.findViewById(R.id.edsn1);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        final long today = MaterialDatePicker.todayInUtcMilliseconds();

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select a Date");
        builder.setSelection(today);


        final MaterialDatePicker materialDatePicker = builder.build();
        datofBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getParentFragmentManager(), "DATE_PICKER");


            }
        });
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                datofBirth.setText(materialDatePicker.getHeaderText());
            }
        });





        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nam = name.getText().toString().trim();
                date = datofBirth.getText().toString().trim();
                phone= phoneNumber.getText().toString().trim();
//                if(isValiname()&&isValidate()&&isValiphone()){
//                    mOnButtonClickListener.onButtonClicked(v);
//                    senData();
//                }else{
//                    Toast.makeText(getContext(), "Bạn không được bỏ trống mục nào", Toast.LENGTH_SHORT).show();
//                }

                if(Validate()){
                    mOnButtonClickListener.onButtonClicked(v);
                    senData();
                }

            }
        });
        return view;
    }

    private OnButtonClickListener mOnButtonClickListener;

    interface OnButtonClickListener {
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

    public void senData(){

        mDangky.setName(nam);
        mDangky.setDate(date);
        mDangky.setPhone(phone);

    }
    public boolean isValiname(){
        return !TextUtils.isEmpty(nam);
    }
    public boolean isValidate(){
        return !TextUtils.isEmpty(date);
    }
    public boolean isValiphone(){
        return !TextUtils.isEmpty(phone);
    }

    private boolean Validate(){
        if(nam.isEmpty()){
            name.setError("Không được bỏ trống");
            name.requestFocus();
            return false;
        }
        if(date.isEmpty()){
            datofBirth.setError("Không được bỏ trống");
            datofBirth.requestFocus();
            return false;
        }
        if(phone.isEmpty()){
            phoneNumber.setError("Không được bỏ trống");
            phoneNumber.requestFocus();
            return false;
        }
        return  true;
    }
}