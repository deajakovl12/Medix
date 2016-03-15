package com.example.deean.medix.tabs_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deean.medix.Lijek;
import com.example.deean.medix.LijekDetaljiAPI;
import com.example.deean.medix.R;
import com.example.deean.medix.RecycleView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Deean on 4.3.2016..
 */
public class LijekoveKoristiFragment extends android.support.v4.app.Fragment{

    private static  final String ARG_EXAMPLE = "this_is_a_constant";
    private String oib;
    public LijekoveKoristiFragment(){
    }
    public static LijekoveKoristiFragment newIstance (String example_argument){
        LijekoveKoristiFragment lijekoveKoristiFragment = new LijekoveKoristiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_EXAMPLE, example_argument);
        lijekoveKoristiFragment.setArguments(args);
        return lijekoveKoristiFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        oib = getArguments().getString(ARG_EXAMPLE);
        Log.i("Fragment created with", oib);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.activity_lijekove_koristi_fragment, container, false);
        return view;
    }
}