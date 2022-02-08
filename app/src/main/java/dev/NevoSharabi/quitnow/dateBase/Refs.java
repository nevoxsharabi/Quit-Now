package dev.NevoSharabi.quitnow.dateBase;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import dev.NevoSharabi.quitnow.store.StoreItem;
import dev.NevoSharabi.quitnow.tools.App;
import dev.NevoSharabi.quitnow.tools.KEYS;

public class Refs {

    public static DatabaseReference getGiftBagsRef()        { return getDBref(KEYS.GIFT_BAGS_REF); }
    public static DatabaseReference getDBref(String url)    { return FirebaseDatabase.getInstance().getReferenceFromUrl(url); }
    public static DatabaseReference getLoggedUserRef()      { return getUsersRef().child(App.getLoggedUser().getUid()); }
    public static String getStorePicStoragePath(String fileName)     { return KEYS.STORE_PICS_REF + fileName.toLowerCase() + ".jpg"; }
    public static DatabaseReference getUsersRef1()           {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        return database.getReference("GiftBags"); }
    public static DatabaseReference getUsersRef()           {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        return database.getReference("Users"); }

    //=============================

   public static StorageReference getStorageRef(String fullFilePath){
        return FirebaseStorage.getInstance().getReference().getStorage().getReferenceFromUrl(fullFilePath);
    }

    //=============================

}
