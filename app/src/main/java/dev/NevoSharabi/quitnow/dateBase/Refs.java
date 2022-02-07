package dev.NevoSharabi.quitnow.dateBase;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import dev.NevoSharabi.quitnow.tools.App;
import dev.NevoSharabi.quitnow.tools.KEYS;

public class Refs {



    public static DatabaseReference getLoggedUserRef()      { return getUsersRef().child(App.getLoggedUser().getUid()); }
    public static DatabaseReference getUsersRef()           {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        return database.getReference("Users"); }

    //=============================

   public static StorageReference getStorageRef(String fullFilePath){
        return FirebaseStorage.getInstance().getReference().getStorage().getReferenceFromUrl(fullFilePath);
    }

    //=============================

}
