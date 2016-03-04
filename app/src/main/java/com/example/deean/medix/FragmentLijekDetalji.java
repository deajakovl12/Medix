package com.example.deean.medix;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.deean.medix.tabs_fragments.MjereOprezaFragment;
import com.example.deean.medix.tabs_fragments.OsnovneInformacijeFragment;

public class FragmentLijekDetalji extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private int[] tabIcons = {R.drawable.caution_white,R.drawable.information_white};

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


        Intent intent = getIntent();
        String ime = intent.getStringExtra("ime1");
        Log.e("TAG", ime);


    }

    private void setupViewPager(ViewPager viewPager) {
        //todo adapter za ovo
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(OsnovneInformacijeFragment.newIstance("this data is for fragment 1"),"Osnovne informacije");
        adapter.addFragment(MjereOprezaFragment.newIstance("this data is for fragment 2"),"Mjere opreza");
        viewPager.setAdapter(adapter);
    }
}
