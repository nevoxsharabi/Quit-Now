package dev.NevoSharabi.quitnow.progress;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import dev.NevoSharabi.quitnow.ActivitySplash;
import dev.NevoSharabi.quitnow.MainActivity;
import dev.NevoSharabi.quitnow.R;
import dev.NevoSharabi.quitnow.TipsAndSymptoms;
import dev.NevoSharabi.quitnow.dateBase.DBreader;
import dev.NevoSharabi.quitnow.dateBase.DBupdater;
import dev.NevoSharabi.quitnow.profile.User;
import dev.NevoSharabi.quitnow.tools.App;
import dev.NevoSharabi.quitnow.tools.OnFragmentTransaction;
import dev.NevoSharabi.quitnow.tools.Utils;
import com.google.android.material.button.MaterialButton;

import java.util.Calendar;
import java.util.List;

import java.util.concurrent.TimeUnit;

public class ProgressFragment extends Fragment {
    private Utils utils;

    private View view;
    private TextView time_lbl_passed;
    private TextView progress_money;
    private MaterialButton reset_progress;
    private MaterialButton quit_overview;
    private MaterialButton tips_and_symptoms;

//    private SmokerDataFragment pastData;
//    private SmokerDataFragment futureData;
    private OnFragmentTransaction onFragmentTransaction;

    private DBreader dbReader;
    private List tips;
    private User user;


    //====================================================

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onFragmentTransaction = (OnFragmentTransaction) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_progress, container, false);

//        pastData = SmokerDataFragment.newInstance(Section.Before);
//        futureData = SmokerDataFragment.newInstance(Section.After);
        utils = Utils.get();
        dbReader = DBreader.get();
//        tips = dbReader.getTips();
        user = dbReader.getUser();

        findViews();
        setListeners();
        internetChecker.postDelayed(runnable, 200);

        App.isNetworkAvailable();


        return view;
    }

    //====================================================

    private void findViews() {
        time_lbl_passed = view.findViewById(R.id.time_passed);
        reset_progress = view.findViewById(R.id.reset_progress);
        progress_money = view.findViewById(R.id.progress_money);
        quit_overview = view.findViewById(R.id.quit_overview);
        tips_and_symptoms = view.findViewById(R.id.tips_and_symptoms);


    }

    private void setListeners() {
        reset_progress.setOnClickListener(v -> {
            user.setDateStoppedSmoking(Calendar.getInstance().getTimeInMillis());
            DBupdater.get().updateUser(user);

        });

        quit_overview.setOnClickListener(v -> {
            Intent intent   = new Intent(getActivity(), TipsAndSymptoms.class);
            getActivity().startActivity(intent);
                });
                

        //tips_and_symptoms.setOnClickListener(v ->
      //          Dialogs.get().goalDialog(user_main_goal).show()
     //   );
        
        
    }

    private void loadRandomTip() {

    }


    private void updateProgressClock() {
        time_lbl_passed.setText(formatDuration());
    }

    private String formatDuration() {
        long duration = user.getRehabDuration();
        long years = TimeUnit.MILLISECONDS.toDays(duration) / 365;
        long months = (TimeUnit.MILLISECONDS.toDays(duration) % 365) / 30;
        long days = TimeUnit.MILLISECONDS.toDays(duration) % 30;
        long hours = TimeUnit.MILLISECONDS.toHours(duration) % 24;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) % 60;

        return String.format("%2d-y %2d-m %2d-d %02d:%02d:%02d", years, months, days, hours, minutes, seconds);
    }

    //====================================================

    private final Handler internetChecker = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            internetChecker.postDelayed(runnable, 1000);
            user = DBreader.get().getUser();
            if (user == null) return;
           progress_money.setText("Money saved: " + utils.formatNumber(user.moneySaved(), "##.#") + " "+ "$");
           updateProgressClock();
        }
    };
}