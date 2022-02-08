package dev.NevoSharabi.quitnow.store;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.NevoSharabi.quitnow.dateBase.DBreader;
import dev.NevoSharabi.quitnow.dateBase.DBupdater;
import dev.NevoSharabi.quitnow.profile.User;
import dev.NevoSharabi.quitnow.tools.App;
import dev.NevoSharabi.quitnow.tools.KEYS;

public class Store {

    private static Store instance;

    private List<StoreItem> itemsList = new ArrayList<>();

    //=============================

    public static void initStore(){
        if(instance == null) {
            instance = new Store();
            DBreader.get().readListData(KEYS.STORE_REF, instance.itemsList,StoreItem.class);
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
                            new StoreItem()
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
        App.toast(item.getTitle() + " Bought!");
    }
    public void sendGift(User userToGift, StoreItem itemToGift){
        User loggedUser = DBreader.get().getUser();
        addStoreItem(userToGift, itemToGift);
        DBupdater dbUpdate =  DBupdater.get();
        dbUpdate.updateGiftBag(userToGift);

        if(itemToGift.getPrice() > 1)
            itemToGift.reduceAmount();
        else
            loggedUser.getBoughtItems().remove(itemToGift.getTitle());
        dbUpdate.updateGiftBag(loggedUser);

        App.toast("Gift Sent!");
    }


}


