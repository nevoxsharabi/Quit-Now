package dev.NevoSharabi.quitnow.progress;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dev.NevoSharabi.quitnow.*;
import dev.NevoSharabi.quitnow.myDateBase.DataBaseReader;
import dev.NevoSharabi.quitnow.myDateBase.DataBaseUpDate;
import dev.NevoSharabi.quitnow.profile.User;
import dev.NevoSharabi.quitnow.tools.App;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiresApi(api = Build.VERSION_CODES.O)
public class RewardsFragment extends Fragment {
    private View view;
    private RecyclerView rewards_list;
    private List<Reward> rewards;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rewards, container, false);
        init_views();
        RewardsAdapter rewardsAdapter = new RewardsAdapter(rewards);
        rewards_list    .setLayoutManager(new GridLayoutManager(getContext(),2 ));
        rewards_list    .setAdapter(rewardsAdapter);
        if(App.isNetworkAvailable()) createRewards(rewards);

        return view;
    }


    void init_views(){
        rewards_list = view.findViewById(R.id.rewards_list);
        rewards = new ArrayList<>();
    }


    void createRewards(List<Reward> rewards){
        User user = DataBaseReader.get().getUser();
        if(user == null) return;
        int time = (int) TimeUnit.MILLISECONDS.toDays(user.getRehabDuration());
        for (int i = 0; i < 19; i++) {
            int max = (int)(Math.pow(i,2)+1);
            LocalDate newDate = LocalDate.now().plusDays(max);
            Reward reward = new Reward()
                    .setRewardName(max + " Days Clean!")
                    .setMax(max)
                    .setUnlockDate(newDate);
            if(time > reward.getMax()) {
                    user.incrementCoins(1000 * i);
                    DataBaseUpDate.get().updateUser(user);
                }
            rewards.add(reward);
        }
    }
}