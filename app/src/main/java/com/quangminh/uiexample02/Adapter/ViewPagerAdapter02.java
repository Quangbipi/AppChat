package com.quangminh.uiexample02.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.quangminh.uiexample02.ChatFragment;
import com.quangminh.uiexample02.UserFragment;

public class ViewPagerAdapter02 extends FragmentStateAdapter {


    public ViewPagerAdapter02(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ChatFragment();
            case 1:
                return new UserFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
