package dev.NevoSharabi.quitnow.myDateBase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import dev.NevoSharabi.quitnow.tools.KEYS;

public class Reference {
    /**
     * get database reference by key
     * @param url database reference
     */
    public static DatabaseReference getDataBaseRef(String url)    { return FirebaseDatabase.getInstance().getReferenceFromUrl(url); }
    public static DatabaseReference getGiftBagsRef()        { return getDataBaseRef(KEYS.GIFT_BAGS_REF); }
    public static String getStorePicStoragePath(String fileName)     { return KEYS.STORE_PICS_REF + fileName.toLowerCase() + ".jpg"; }
    public static DatabaseReference getUsersRef()  {return getDataBaseRef(KEYS.USERS_REF);}


   public static StorageReference getStorageRef(String fullFilePath){
        return FirebaseStorage.getInstance().getReference().getStorage().getReferenceFromUrl(fullFilePath);
    }
}
