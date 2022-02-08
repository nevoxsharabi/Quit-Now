package dev.NevoSharabi.quitnow.store;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.NevoSharabi.quitnow.dateBase.DBreader;
import dev.NevoSharabi.quitnow.profile.User;
import dev.NevoSharabi.quitnow.tools.App;

public class Store {

    private static Store instance;

    private List<StoreItem> itemsList = new ArrayList<>();

    //=============================
    //dont forget change this firebase
    public static void initStore(){
        if(instance == null) {
            instance = new Store();
            DBreader.get().readListData("https://quit-now-4b3c3-default-rtdb.europe-west1.firebasedatabase.app/Store_items", instance.itemsList,StoreItem.class);
        }
    }

    /**
     * gets the singleton
     */
    public static Store get() { return instance; }

    public List<StoreItem> getItems() { return itemsList; }


    //=============================

    private void addStoreItem(User user, StoreItem item) {
        HashMap<String,StoreItem> userItems = user.getBoughtItems();
        if(userItems.containsKey(item.getTitle()))
            userItems.get(item.getTitle()).incrementAmount();
        else {
            userItems.put(item.getTitle() ,
                            new dev.NevoSharabi.quitnow.store.StoreItem()
                            .setTitle(item.getTitle())
                            .setPrice(1));
        }
    }

    //=============================

    public void buyItem(User user, StoreItem item){
        if(item.getPrice() > user.getCoins()){
            App.toast("Not Enough Coins!");
            return;
        }
        addStoreItem(user, item);
        user.reduceCoins(item.getPrice());

    }

}


