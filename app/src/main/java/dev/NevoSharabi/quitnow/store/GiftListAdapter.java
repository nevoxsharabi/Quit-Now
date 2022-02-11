package dev.NevoSharabi.quitnow.store;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import dev.NevoSharabi.quitnow.R;
import dev.NevoSharabi.quitnow.myDateBase.DBreader;
import dev.NevoSharabi.quitnow.profile.User;
import dev.NevoSharabi.quitnow.tools.KEYS;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class GiftListAdapter extends RecyclerView.Adapter<GiftListAdapter.GiftListViewHolder> {

    private View                        view;
    private HashMap<String, StoreItem>  items;
    private ArrayList<String>           keys;
    private User userToGift;

    //=============================

    public GiftListAdapter(HashMap<String, StoreItem> giftsList, User user){
        this.items      = giftsList;
        this.keys       = new ArrayList<>(giftsList.keySet());
        this.userToGift = user;
    }

    //=============================

    @Override
    public int getItemCount() { return items.size(); }

    @NonNull
    @Override
    public GiftListAdapter.GiftListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gift,parent,false);
        return new GiftListAdapter.GiftListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GiftListViewHolder holder, int position) {
        StoreItem storeItem     = items.get(keys.get(position));

        DBreader.get()          .readPic(KEYS.STORE,holder.itemPhoto, storeItem.getTitle());
        holder.itemTitle        .setText(storeItem.getTitle());
        holder.itemAmount       .setText(storeItem.getPrice() +"");
        holder.gift_layout      .setOnClickListener(
                    v -> {
                        Store.get().sendGift(userToGift,storeItem);
                        notifyListChanged(storeItem, position);
                    });
    }

    private void notifyListChanged(StoreItem storeItem, int position) {
        if(!DBreader.get().getUser()
                .getBoughtItems().containsKey(storeItem.getTitle())){
            items.remove(keys.get(position));
            keys.remove(position);
        }
        notifyDataSetChanged();
    }

    //==================================================

    public class GiftListViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout            gift_layout;
        private CircleImageView             itemPhoto;
        private TextView                    itemTitle;
        private TextView                    itemAmount;

        GiftListViewHolder(View itemView) {
            super(itemView);
            findViews();
        }

        //==================================================

        void findViews(){
            gift_layout = itemView.findViewById(R.id.gift_layout);
            itemPhoto   = itemView.findViewById(R.id.item_gift_photo);
            itemTitle   = itemView.findViewById(R.id.item_gift_title);
            itemAmount  = itemView.findViewById(R.id.item_gift_amount);
        }

        //==================================================

    }
}
