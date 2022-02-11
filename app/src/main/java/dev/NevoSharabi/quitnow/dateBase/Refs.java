package dev.NevoSharabi.quitnow.dateBase;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import dev.NevoSharabi.quitnow.tools.KEYS;

public class Refs {
    /**
     * get database reference by key
     * @param url database ref (references found in dataManage.KEYS)
     */
    public static DatabaseReference getDBref(String url)    { return FirebaseDatabase.getInstance().getReferenceFromUrl(url); }
    public static DatabaseReference getGiftBagsRef()        { return getDBref(KEYS.GIFT_BAGS_REF); }
    public static String getStorePicStoragePath(String fileName)     { return KEYS.STORE_PICS_REF + fileName.toLowerCase() + ".jpg"; }
    public static DatabaseReference getUsersRef()  {return getDBref(KEYS.USERS_REF);}


   public static StorageReference getStorageRef(String fullFilePath){
        return FirebaseStorage.getInstance().getReference().getStorage().getReferenceFromUrl(fullFilePath);
    }


}
