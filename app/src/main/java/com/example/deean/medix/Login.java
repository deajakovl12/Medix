package com.example.deean.medix;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.deean.medix.tabs_fragments.OMedixAppFragment;
import com.example.deean.medix.tabs_fragments.PomocFragment;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class Login extends AppCompatActivity  implements View.OnClickListener{
    Button bPrijava;
    EditText etEmail, etLozinka;
    TextView tvRegistracijaLink, tvLozinkaLink;

    DoktorLokalno DoktorLokalno;
    PacijentLokalno PacijentLokalno;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etLozinka = (EditText)findViewById(R.id.etLozinka);
        bPrijava = (Button) findViewById(R.id.bPrijava);
        tvRegistracijaLink = (TextView) findViewById(R.id.tvRegistracijaLink);
        tvLozinkaLink = (TextView) findViewById(R.id.tvLozinkaLink);


        bPrijava.setOnClickListener(this);
        tvRegistracijaLink.setOnClickListener(this);
        tvLozinkaLink.setOnClickListener(this);


        DoktorLokalno = new DoktorLokalno(this);
        PacijentLokalno = new PacijentLokalno(this);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setPadding(0, 0, 0, 0);
        toolbar.setBackgroundColor(Color.rgb(162, 32, 34));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("Početni").withIcon(getResources().getDrawable(R.drawable.home_small_white));
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withName("O Medix app").withIcon(getResources().getDrawable(R.drawable.information_white));
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withName("Pomoć").withIcon(getResources().getDrawable(R.drawable.help_small_white));

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        item3
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        switch (position) {
                            case 0:
                                startActivity(new Intent(getApplicationContext(),Login.class));
                                //ne treba jer kad kliknem pocetni sam se makne
                                break;
                            case 2:
                                OMedixAppFragment f1 = new OMedixAppFragment();
                                fragmentTransaction.add(R.id.fragment_container, f1);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                                break;
                            case 3:
                                PomocFragment f2 = new PomocFragment();
                                fragmentTransaction.add(R.id.fragment_container,f2);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                                break;
                        }
                        Log.e("Pozicija", String.valueOf(position));
                        return false;
                    }
                })
                .build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bPrijava:
                String email = etEmail.getText().toString();
                String lozinka = etLozinka.getText().toString();

                Pacijent pacijent = new Pacijent(email,lozinka);
                Doktor doktor = new Doktor(email,lozinka);

                autentifikacija_doktora(doktor);
                autentifikacija_pacijenta(pacijent);

                //DoktorLokalno.spremiDoktorPodatke(doktor);
                //DoktorLokalno.postaviPrijavljenogDoktora(true);

                break;
            case R.id.tvRegistracijaLink:
                startActivity(new Intent(this,FragmentRegistracija.class));
                break;

            case R.id.tvLozinkaLink:
                startActivity(new Intent(this,ZaboravljenaLozinka.class));
                break;

        }



    }

    private void autentifikacija_doktora(Doktor doktor){
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.dohvatiPodatkeUPozadini(doktor, new GetUserCallback() {
            @Override
            public void done(Doktor returnedDoktor) {
                if(returnedDoktor == null){
                }else{
                    logDoktorIn(returnedDoktor);
                }
            }
        });

    }
    private void autentifikacija_pacijenta(Pacijent pacijent) {
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.dohvatiPodatkePacijentUPozadini(pacijent, new GetUserCallbackPacijent() {
            @Override
            public void done(Pacijent returnedPacijent) {
                if (returnedPacijent == null) {
                    showErrorMessage();
                } else {
                    logPacijentIn(returnedPacijent);
                }
            }
        });
    }
    private void showErrorMessage(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Krivo uneseni podaci!");
        dialogBuilder.setPositiveButton("Ok",null);
        dialogBuilder.show();
    }

    private void logDoktorIn(Doktor returnedDoktor){
        DoktorLokalno.spremiDoktorPodatke(returnedDoktor);
        DoktorLokalno.postaviPrijavljenogDoktora(true);

        startActivity(new Intent(this,Prijava.class));
    }
    private void logPacijentIn(Pacijent returnedPacijent){
        PacijentLokalno.spremiPacijentPodatke(returnedPacijent);
        PacijentLokalno.postaviPrijavljenogPacijenta(true);

        startActivity(new Intent(this,Prijava_Pacijent.class));
    }
}
