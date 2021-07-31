package com.quangminh.uiexample02.Adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.quangminh.uiexample02.Fragment_1;
import com.quangminh.uiexample02.Fragment_2;
import com.quangminh.uiexample02.Fragment_3;
import com.quangminh.uiexample02.R;

import java.util.zip.Inflater;

public class ViewpagerAdapter extends FragmentStateAdapter {


    public ViewpagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Fragment_1();
            case 1:
                return new Fragment_2();
            case 2:
                return new Fragment_3();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public long getItemId(int position) {

        return super.getItemId(position);
    }


}
