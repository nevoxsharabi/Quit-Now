package dev.NevoSharabi.quitnow.tips;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import dev.NevoSharabi.quitnow.ui.main.SectionsPagerAdapter;
import dev.NevoSharabi.quitnow.databinding.ActivityTipsAndSymptomsBinding;

public class TipsAndSymptoms extends AppCompatActivity {

private ActivityTipsAndSymptomsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityTipsAndSymptomsBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);



    }
}