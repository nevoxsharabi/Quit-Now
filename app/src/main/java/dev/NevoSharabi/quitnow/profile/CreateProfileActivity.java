package dev.NevoSharabi.quitnow.profile;


import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.HashMap;

import dev.NevoSharabi.quitnow.MainActivity;
import dev.NevoSharabi.quitnow.R;
import dev.NevoSharabi.quitnow.myDateBase.DataBaseReader;
import dev.NevoSharabi.quitnow.myDateBase.DataBaseUpDate;
import dev.NevoSharabi.quitnow.tools.App;
import dev.NevoSharabi.quitnow.tools.Utils;

public class CreateProfileActivity extends AppCompatActivity {

    private ImageView       user_profile_pic;

    private TextInputLayout user_name;
    private TextInputLayout years_smoked;
    private TextInputLayout cigsPerWeek;
    private TextInputLayout price_per_pack;
    private TextInputLayout cigs_per_pack;

    private MaterialButton  continue_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);
        findViews();
        setListeners();
    }


    private void setListeners() {
        continue_btn.setOnClickListener(v -> {
            User user = createUserData();
            DataBaseUpDate.get()     .updateUser(user);
            DataBaseReader.get()      .readUserData();
            Utils.get()         .myStartActivity(CreateProfileActivity.this, MainActivity.class);

        });


    }

    private void findViews() {
        user_profile_pic =  findViewById(R.id.user_profile_pic);
        user_name =         findViewById(R.id.user_name);
        years_smoked =      findViewById(R.id.years_smoked);
        cigsPerWeek =      findViewById(R.id.cigs_per_day);
        price_per_pack =    findViewById(R.id.price_per_pack);
        cigs_per_pack =     findViewById(R.id.cigs_per_pack);
        continue_btn =      findViewById(R.id.first_time_continue);

    }

    private User createUserData() {
        String  name                = user_name.getEditText().getText().toString();
        long    dateStoppedSmoking  = Calendar.getInstance().getTimeInMillis();
        double  yearsSmoked         = Double.parseDouble(0 + years_smoked.getEditText().getText().toString());
        double  pricePerPack        = Double.parseDouble(0 + price_per_pack.getEditText().getText().toString());
        int     CigsPerWeek          = Integer.parseInt(0 + cigsPerWeek.getEditText().getText().toString());
        int cigsPerPack         = Integer.parseInt(0 + cigs_per_pack.getEditText().getText().toString());
        return new User().setUid(App.getLoggedUser().getUid())
                .setName(name)
                .setYearsSmoked(yearsSmoked)
                .setCigsPerWeek(CigsPerWeek)
                .setPricePerPack(pricePerPack)
                .setCigsPerPack(cigsPerPack)
                .setCoins(2000)
                .setDateStoppedSmoking(dateStoppedSmoking)
                .setBoughtItems(new HashMap<>());
    }
}
