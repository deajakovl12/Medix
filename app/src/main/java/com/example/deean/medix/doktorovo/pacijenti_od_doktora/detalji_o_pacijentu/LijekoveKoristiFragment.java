package com.example.deean.medix.doktorovo.pacijenti_od_doktora.detalji_o_pacijentu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.deean.medix.lijekovi.Lijek;
import com.example.deean.medix.R;
import com.example.deean.medix.lijekovi.RVAdapter;
import com.example.deean.medix.lijekovi.RecycleView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Deean on 4.3.2016..
 */
public class LijekoveKoristiFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private static  final String ARG_EXAMPLE = "this_is_a_constant";
    private String oib;

    public String tekst;
    Button bPretraga;
    EditText etPretraga;

    ImageView ivDodaj;


    private ArrayList<String> spremi;
    private List<Lijek> lijeks;
    private RecyclerView rv;


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

        rv = (RecyclerView) view.findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        etPretraga = (EditText) view.findViewById(R.id.etPretraga);
        bPretraga = (Button) view.findViewById(R.id.bPretraga);
        ivDodaj = (ImageView) view.findViewById(R.id.ivDodaj);

        bPretraga.setOnClickListener(this);
        ivDodaj.setOnClickListener(this);
        Log.e("ID", OsobniPodaciFragment.id_pacijenta);


        return view;
    }

    @Override
    public void onClick(View v) {
            switch(v.getId()) {
                case R.id.bPretraga:
                    initializeData();
                    break;
                case R.id.ivDodaj:
                    //omoguciti dodavanje lijekova!! //TODO

                    break;

            }

    }
    private void initializeData(){
        tekst = String.valueOf(etPretraga.getText());
        spremi = new ArrayList<>();
        lijeks = new ArrayList<>();
        LijekoviPacijentaAPI.Factory.getIstance().response(OsobniPodaciFragment.id_pacijenta).enqueue(new Callback<ArrayList<Lijek>>() {
            @Override
            public void onResponse(Call<ArrayList<Lijek>> call, Response<ArrayList<Lijek>> response) {
                initializeAdapter();
                for (int i = 0; i < response.body().size(); i++) {
                    spremi.add(response.body().get(i).getNaziv());
                }
                for (int i = 0; i < spremi.size(); i++) {
                    if (spremi.get(i).toLowerCase().contains(tekst.toLowerCase())) {
                        if(spremi.get(i).equals("Lupocet 500mg tablete")) {
                            lijeks.add(new Lijek(spremi.get(i), RecycleView.poljeSlika[0]));
                        }
                        if(spremi.get(i).equals("Neofen")) {
                            lijeks.add(new Lijek(spremi.get(i), RecycleView.poljeSlika[1]));
                        }
                        if(spremi.get(i).equals("Naklofen")) {
                            lijeks.add(new Lijek(spremi.get(i), RecycleView.poljeSlika[2]));
                        }
                    }
                }}

            @Override
            public void onFailure(Call<ArrayList<Lijek>> call, Throwable t) {
                Log.e("Failed", t.getMessage());
            }
        });
    }
    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(getActivity(),lijeks);
        rv.setAdapter(adapter);
    }
}
