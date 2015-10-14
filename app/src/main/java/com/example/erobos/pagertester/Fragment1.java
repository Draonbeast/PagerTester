package com.example.erobos.pagertester;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Fragment1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedinstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
}
