package com.example.deean.medix.pacijentovo;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.deean.medix.pacijentovo.konstruktor_i_baza.Pacijent;
import com.example.deean.medix.pacijentovo.konstruktor_i_baza.PacijentLokalno;
import com.example.deean.medix.R;
import com.example.deean.medix.pocetni_zaslon.Login;

public class Prijava_Pacijent extends ToolbarActivityPacijent {
    TextView  etSpol, etTelefon, etAdresa,etPrezime,etIme,etMobitel,etOIB;
    com.example.deean.medix.pacijentovo.konstruktor_i_baza.PacijentLokalno PacijentLokalno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prijava__pacijent);

        etSpol = (TextView) findViewById(R.id.etSpol);
        etTelefon = (TextView) findViewById(R.id.etTelefon);
        etAdresa = (TextView) findViewById(R.id.etAdresa);
        etIme = (TextView) findViewById(R.id.etIme);
        etPrezime = (TextView) findViewById(R.id.etPrezime);
        etMobitel = (TextView) findViewById(R.id.etMobitel);
        etOIB = (TextView) findViewById(R.id.etOIB);

        PacijentLokalno = new PacijentLokalno(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(provjera()==true){
            prikaziPacijentovePodatke();
        }
        else{
            startActivity(new Intent(Prijava_Pacijent.this,Login.class));
        }
    }

    private boolean provjera(){
        return PacijentLokalno.provjeriPrijavljenogPacijenta();
    }
    private void prikaziPacijentovePodatke(){
        Pacijent pacijent = PacijentLokalno.getPrijavljenogPacijenta();
        etSpol.setText(pacijent.getSpol());
        etIme.setText(pacijent.getIme().toUpperCase());
        etPrezime.setText(pacijent.getPrezime().toUpperCase());
        etAdresa.setText(pacijent.getAdresa());
        etTelefon.setText(pacijent.getTelefon());
        etMobitel.setText(pacijent.getMobitel());
        etOIB.setText(pacijent.getOib());

        postaviDrawer(postaviToolbar("Pacijent"),etIme.getText().toString(),etPrezime.getText().toString(),pacijent.getEmail()).build();

    }
}
