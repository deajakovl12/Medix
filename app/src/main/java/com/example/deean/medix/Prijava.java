package com.example.deean.medix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Prijava extends AppCompatActivity implements View.OnClickListener {
    Button bOdjava;
    EditText etEmail, etOIB, etTelefon, etAdresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prijava);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etOIB = (EditText) findViewById(R.id.etOIB);
        etTelefon = (EditText) findViewById(R.id.etTelefon);
        etAdresa = (EditText) findViewById(R.id.etAdresa);
        bOdjava = (Button) findViewById(R.id.bOdjava);

        bOdjava.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bOdjava:
                startActivity(new Intent(this,Login.class));

                break;

        }
    }
}
