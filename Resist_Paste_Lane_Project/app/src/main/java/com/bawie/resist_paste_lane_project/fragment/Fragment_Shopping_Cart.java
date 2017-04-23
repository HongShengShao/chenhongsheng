package com.bawie.resist_paste_lane_project.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawie.resist_paste_lane_project.R;

/**
 * @author 陈宏升
 * @类的作用：
 * @data 2017/4/11 20:09
 */
public class Fragment_Shopping_Cart extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_shopping_cart,null);

        return view;
    }
}
