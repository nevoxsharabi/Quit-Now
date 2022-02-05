package dev.NevoSharabi.quitnow.store;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.NevoSharabi.quitnow.dateBase.DBreader;
import dev.NevoSharabi.quitnow.profile.User;

public class Store {

    private static Store instance;

    private List<dev.NevoSharabi.quitnow.store.StoreItem> itemsList = new ArrayList<>();

    //=============================
    //dont forget change this firebase
    public static void initStore(){
        if(instance == null) {
            instance = new Store();
          //  DBreader.get().readListData("https://fumi-app-default-rtdb.firebaseio.com/Store_items", instance.itemsList, dev.NevoSharabi.quitnow.store.StoreItem.class);
        }
    }

    /**
     * gets the singleton
     */
    public static Store get() { return instance; }

    public List<dev.NevoSharabi.quitnow.store.StoreItem> getItems() { return itemsList; }


    //=============================

    private void addStoreItem(User user, dev.NevoSharabi.quitnow.store.StoreItem item) {
        HashMap<String, dev.NevoSharabi.quitnow.store.StoreItem> userItems = user.getBoughtItems();
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

//    public void buyItem(User user, com.example.Stopi.store.StoreItem item){
//        if(item.getPrice() > user.getCoins()){
//            App.toast("Not Enough Coins!");
//            return;
//        }
//        addStoreItem(user, item);
//        user.reduceCoins(item.getPrice());
//        App.toast(item.getTitle() + " Bought!");
//    }

}


