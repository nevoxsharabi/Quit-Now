package dev.NevoSharabi.quitnow.profile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.HashMap;

import dev.NevoSharabi.quitnow.ActivitySplash;
import dev.NevoSharabi.quitnow.MainActivity;
import dev.NevoSharabi.quitnow.R;
import dev.NevoSharabi.quitnow.dateBase.DBreader;
import dev.NevoSharabi.quitnow.dateBase.DBupdater;
import dev.NevoSharabi.quitnow.login.SharedPrefs;
import dev.NevoSharabi.quitnow.progress.ProgressFragment;
import dev.NevoSharabi.quitnow.tools.App;
import dev.NevoSharabi.quitnow.tools.KEYS;
import dev.NevoSharabi.quitnow.tools.Utils;

public class CreateProfileActivity extends AppCompatActivity {
    private String          currencySymbol;

    private Uri             filePathUri;
    private ImageView       user_profile_pic;

    private TextInputLayout user_name;
    private TextInputLayout years_smoked;
    private TextInputLayout cigs_per_day;
    private TextInputLayout price_per_pack;
    private TextInputLayout cigs_per_pack;

    private MaterialButton  continue_btn;
    private MaterialButton  currency_btn;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);
        findViews();
        setListeners();
    }


    private void setListeners() {
        //user_profile_pic.setOnClickListener(v -> Utils.get().getImage(this));

        continue_btn.setOnClickListener(v -> {
            User user = createUserData();
            DBupdater.get()     .updateUser(user);
            DBreader.get()      .readUserData();
            SharedPrefs.get().saveFirstLogin();
            Utils.get()         .myStartActivity(CreateProfileActivity.this, ProgressFragment.class);

        });


    }

    private void findViews() {
        user_profile_pic =  findViewById(R.id.user_profile_pic);
        user_name =         findViewById(R.id.user_name);
        years_smoked =      findViewById(R.id.years_smoked);
        cigs_per_day =      findViewById(R.id.cigs_per_day);
        price_per_pack =    findViewById(R.id.price_per_pack);
        cigs_per_pack =     findViewById(R.id.cigs_per_pack);
        continue_btn =      findViewById(R.id.first_time_continue);

    }

    private User createUserData() {
        String  name                = user_name.getEditText().getText().toString();
        long    dateStoppedSmoking  = Calendar.getInstance().getTimeInMillis();
        double  yearsSmoked         = Double.parseDouble(0 + years_smoked.getEditText().getText().toString());
        double  pricePerPack        = Double.parseDouble(0 + price_per_pack.getEditText().getText().toString());
        int     cigsPerDay          = Integer.parseInt(0 + cigs_per_day.getEditText().getText().toString());

        return new User().setUid(App.getLoggedUser().getUid())
                .setName(name)
                .setYearsSmoked(yearsSmoked)
                .setCigsPerWeek(cigsPerDay)
                .setPricePerPack(pricePerPack)
                .setCoins(2000)
                .setLoggedToday(dateStoppedSmoking)
                .setDateStoppedSmoking(dateStoppedSmoking)
                .setBoughtItems(new HashMap<>());
    }
}
