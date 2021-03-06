package dev.NevoSharabi.quitnow.store;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dev.NevoSharabi.quitnow.myDateBase.DataBaseReader;
import dev.NevoSharabi.quitnow.myDateBase.DataBaseUpDate;
import dev.NevoSharabi.quitnow.profile.User;
import dev.NevoSharabi.quitnow.tools.App;
import dev.NevoSharabi.quitnow.tools.KEYS;

import com.furkanakdemir.surroundcardview.SurroundCardView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import dev.NevoSharabi.quitnow.R;
import dev.NevoSharabi.quitnow.tools.Utils;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.StoreViewHolder> {

    private View                view;
    private List<StoreItem>     storeItems;
    private OnCoinsChanged      onCoinsChanged;

    //====================================================

    public ItemsAdapter(List<StoreItem> store_items){ this.storeItems = store_items; }

    public void setClickListener(OnCoinsChanged onCoinsChanged) { this.onCoinsChanged = onCoinsChanged; }

    //====================================================

    @Override
    public int getItemCount() { return storeItems.size(); }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store,parent,false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StoreViewHolder holder, int position) {
        StoreItem storeItem         = storeItems.get(position);
        holder.store_item_title     .setText(storeItem.getTitle());
        holder.store_item_price     .setText(""+ storeItem.getPrice());

        DataBaseReader.get()      .readPic(KEYS.STORE,holder.store_item_photo, storeItem.getTitle());

        if(onCoinsChanged != null)
            holder.svc              .setOnClickListener(v -> beginPurchase(storeItem));
        else
            holder.svc              .setOnClickListener(v -> Utils.get().onCardClick(holder.svc));
    }

    private void beginPurchase(StoreItem storeItem){
        User user = DataBaseReader.get().getUser();
        if(storeItem.getPrice() > user.getCoins()){
            App.toast("Not Enough Coins!");
            return;
        }
        Store.get().buyItem(user, storeItem);
        DataBaseUpDate.get().updateGiftBag(user);
        onCoinsChanged.updateCoins();
    }



    public class StoreViewHolder extends RecyclerView.ViewHolder {

        TextView            store_item_title;
        TextView            store_item_price;
        CircleImageView     store_item_photo;
        SurroundCardView    svc;

        StoreViewHolder(View itemView) {
            super(itemView);
            findViews();
        }

        //==================================================

        void findViews(){
            store_item_title    = itemView.findViewById(R.id.store_item_title);
            store_item_price    = itemView.findViewById(R.id.store_item_price);
            store_item_photo    = itemView.findViewById(R.id.store_item_photo);
            svc                 = itemView.findViewById(R.id.store_item_svc);
        }

        //==================================================

    }
}
