package com.example.asus.todayhead.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.asus.todayhead.R;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by asus on 2017/3/24.
 */
public class Fragment_Browse extends Fragment {

    private PhotoView photoView;
    private int count;
    private String imageUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        count=getArguments().getInt("bun");
        imageUrl=getArguments().getString("imageUrl");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_browse,null);
        initView(view);
        return view;
    }



    private void initView(View view) {
        photoView= (PhotoView) view.findViewById(R.id.photoView);
        Glide.with(getActivity()).load(imageUrl).into(photoView);
    }
}
