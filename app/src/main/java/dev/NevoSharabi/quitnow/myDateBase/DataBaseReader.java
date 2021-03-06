package dev.NevoSharabi.quitnow.myDateBase;

import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;

import dev.NevoSharabi.quitnow.R;
import dev.NevoSharabi.quitnow.tools.App;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;


import java.util.ArrayList;
import java.util.List;

import dev.NevoSharabi.quitnow.profile.User;

public class DataBaseReader {

    private static DataBaseReader instance;

    private User user;
    private List rewards_info = new ArrayList<>();

    public static void initReader() {
        if (instance == null) {
            instance = new DataBaseReader();
            instance.readData();
        }
    }


    public static DataBaseReader get() {
        return instance;
    }


    private void readData() {
        if (App.getLoggedUser() != null)
            get().readUserData();
        for (int i = 1; i < 20; i++) {
            rewards_info.add("congratulation you earned " + i*1000 + " Coins!" );
        }

    }


    public void readUserData() {
        Reference.getUsersRef().child(App.getLoggedUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        user = dataSnapshot.getValue(User.class);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });
    }

    public void readListData(String Ref, List list, Class ObjectClass) {
        Reference.getDataBaseRef(Ref).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    list.add(snapshot.getValue(ObjectClass));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private StorageReference photoPathRef(int key, String fileName) {
        String  ref = Reference.getStorePicStoragePath(fileName);
        return Reference.getStorageRef(ref);
    }

    /**
     * @param key       STORE = 1 , PROFILE = 2 found in KEYS
     * @param imageView imageView to load into
     * @param fileName  name of the file to load
     */
    public void readPic(int key, ImageView imageView, String fileName) {
        StorageReference ref = photoPathRef(key, fileName);
        Log.d("info", ref.getPath());
        Glide.with(App.getAppContext())
                .load(ref)
                .placeholder(R.drawable.img_default_pic)
                .centerInside()
                .dontAnimate()
                .into(imageView);
    }

    /**
     * @param imageView imageView to load into
     * @param fileName  name of the file to load
     */
    public void readPicNoCache(int key, ImageView imageView, String fileName) {
        StorageReference ref = photoPathRef(key, fileName);
        Glide.with(App.getAppContext())
                .load(ref)
                .placeholder(R.drawable.img_default_pic)
                .centerInside()
                .dontAnimate()
                .signature(new ObjectKey(System.currentTimeMillis()))
                .into(imageView);
    }
    public User getUser() {
        return user;
    }
    public List getRewardsInfo() {
        return rewards_info;
    }

}
