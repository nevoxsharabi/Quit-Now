package dev.NevoSharabi.quitnow.progress;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import dev.NevoSharabi.quitnow.R;
import dev.NevoSharabi.quitnow.myDateBase.DataBaseReader;
import com.wajahatkarim3.easyflipview.EasyFlipView;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiresApi(api = Build.VERSION_CODES.O)
public class RewardsAdapter extends RecyclerView.Adapter<RewardsAdapter.RewardsViewHolder> {

    private View                    view;
    private List<Reward>            rewards;

    public RewardsAdapter(List<Reward> rewards){ this.rewards = rewards; }

    //====================================================

    @NonNull
    @Override
    public RewardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reward,parent,false);
        return new RewardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RewardsViewHolder holder, int position) {
        Reward reward = rewards.get(position);
        setHolderData(holder,position, reward);
    }


    private void setHolderData(RewardsViewHolder holder, int position, Reward reward){
        int time = (int)TimeUnit.MILLISECONDS.toDays(DataBaseReader.get().getUser().getRehabDuration());
        String reward_info = (String) DataBaseReader.get().getRewardsInfo().get(position);
        holder.progressBar  .setMax(reward.getMax());
        holder.progressBar  .setProgress(time);
        holder.rewardText   .setText(reward.getRewardName());
        holder.reward_info  .setText(reward_info != null ? reward_info : "");

    }

    //====================================================

    @Override
    public int getItemCount() { return rewards.size(); }

    //====================================================

    public class RewardsViewHolder extends RecyclerView.ViewHolder {

        ProgressBar     progressBar;
        TextView        unlock_date;
        TextView        reward_info;
        TextView        rewardText;
        EasyFlipView    flipView;

        RewardsViewHolder(View itemView) {
            super(itemView);
            findViews();
            setViewHolderData();
            flipView.flipTheView();
        }

        //====================================================

        void findViews(){
            progressBar = itemView.findViewById(R.id.reward_progressBar);
            unlock_date = itemView.findViewById(R.id.unlock_date);
            reward_info = itemView.findViewById(R.id.reward_info);
            rewardText  = itemView.findViewById(R.id.reward_text);
            flipView    = itemView.findViewById(R.id.flip_view);
        }

        private void setViewHolderData(){
            itemView.setOnClickListener(v -> flipView.flipTheView());
        }

    }
}
