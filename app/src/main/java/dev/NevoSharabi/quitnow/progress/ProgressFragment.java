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

import dev.NevoSharabi.quitnow.R;
import dev.NevoSharabi.quitnow.tips.TipsAndSymptoms;
import dev.NevoSharabi.quitnow.myDateBase.DataBaseReader;
import dev.NevoSharabi.quitnow.myDateBase.DataBaseUpDate;
import dev.NevoSharabi.quitnow.profile.User;
import dev.NevoSharabi.quitnow.tools.App;
import dev.NevoSharabi.quitnow.tools.Utils;
import com.google.android.material.button.MaterialButton;

import java.util.Calendar;

import java.util.concurrent.TimeUnit;

public class ProgressFragment extends Fragment {
    private Utils utils;

    private View view;
    private TextView time_lbl_passed;
    private TextView progress_money;
    private MaterialButton reset_progress;
    private MaterialButton quit_overview;
    private DataBaseReader dbReader;
    private User user;


    //====================================================

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_progress, container, false);
        utils = Utils.get();
        dbReader = DataBaseReader.get();
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


    }

    private void setListeners() {
        reset_progress.setOnClickListener(v -> {
            user.setDateStoppedSmoking(Calendar.getInstance().getTimeInMillis());
            DataBaseUpDate.get().updateUser(user);

        });

        quit_overview.setOnClickListener(v -> {
            Intent intent   = new Intent(getActivity(), TipsAndSymptoms.class);
            getActivity().startActivity(intent);
                });

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
            user = DataBaseReader.get().getUser();
            if (user == null) return;
           progress_money.setText("so far you saved: " + utils.formatNumber(user.moneySaved(), "##.#") + " "+ "$");
           updateProgressClock();
        }
    };
}