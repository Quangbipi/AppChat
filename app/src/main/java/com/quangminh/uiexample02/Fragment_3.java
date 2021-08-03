package com.quangminh.uiexample02;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;

import static android.app.Activity.RESULT_OK;


public class Fragment_3 extends Fragment {



    public static  final int GALLER_ACTION_PICK_CODE = 100;
    public Fragment_3() {
        // Required empty public constructor
    }

    Dang_ky mDangky;
    ImageButton imageButton3, male, female;
    String gender="";
    RoundedImageView avatar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        imageButton3 = view.findViewById(R.id.next33);
        male = view.findViewById(R.id.imageButton22);
        female = view.findViewById(R.id.imageButton33);
        mDangky = (Dang_ky) getActivity();
        avatar = view.findViewById(R.id.roundedImageView);

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValigender()){

                    mOnButtonClickListener.onButtonClicked(v);
                }

            }
        });


        //chọn gioi tính
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setBackgroundResource(R.drawable.custom_button2);
                male.setColorFilter(getResources().getColor(R.color.white, null));
                female.setBackgroundResource(R.drawable.button_disable);
                female.setColorFilter(getResources().getColor(R.color.teal_200, null));
                gender = "male";
                mDangky.setGender(gender);
                Toast.makeText(getContext(), "male", Toast.LENGTH_SHORT).show();
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female.setBackgroundResource(R.drawable.custom_button2);
                female.setColorFilter(getResources().getColor(R.color.white, null));
                male.setBackgroundResource(R.drawable.button_disable);
                male.setColorFilter(getResources().getColor(R.color.teal_200, null));
                gender = "female";
                mDangky.setGender(gender);
                Toast.makeText(getContext(), "female", Toast.LENGTH_SHORT).show();
            }
        });

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                oentDialogImg(Gravity.BOTTOM);
            }
        });

        return view;
    }


    public void opentDialog(int gravity){

        final Dialog dialog = new Dialog(getContext());

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
                Intent i = new Intent(getContext(), Dang_nhap.class);
                startActivity(i);
            }
        });

        dialog.show();

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

    public boolean isValigender(){
        return !TextUtils.isEmpty(gender);
    }

    private void oentDialogImg(int gravity) {
        final Dialog dialog = new Dialog(getContext());
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

        TextView memory = dialog.findViewById(R.id.cosan);
        TextView camera = dialog.findViewById(R.id.chuphinh);

        memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                takePhoto();
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dialog.show();
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