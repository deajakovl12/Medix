package com.example.deean.medix;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.deean.medix.tabs_fragments.MjereOprezaFragment;
import com.example.deean.medix.tabs_fragments.OsnovneInformacijeFragment;

public class FragmentLijekDetalji extends ToolbarActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private String ime;
    private int[] tabIcons = {R.drawable.caution_white,R.drawable.information_white};

    private Doktor doktor;
    DoktorLokalno DoktorLokalno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_lijek_detalji);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(tabIcons[1]);
        tabLayout.getTabAt(1).setIcon(tabIcons[0]);

        DoktorLokalno = new DoktorLokalno(this);

        doktor = DoktorLokalno.getPrijavljenogDoktora();
        postaviDrawer(postaviToolbar("Lijek detalji"), doktor.ime.toUpperCase(), doktor.prezime.toUpperCase(), doktor.email).build();
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Intent intent = getIntent();
        ime = intent.getStringExtra("ime1");
        Log.e("TAG", ime);
        adapter.addFragment(OsnovneInformacijeFragment.newIstance(ime),"Osnovne informacije");
        adapter.addFragment(MjereOprezaFragment.newIstance(ime),"Mjere opreza");
        viewPager.setAdapter(adapter);
    }
}
