package dev.NevoSharabi.quitnow.dateBase;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import dev.NevoSharabi.quitnow.R;
import dev.NevoSharabi.quitnow.tools.App;
import dev.NevoSharabi.quitnow.tools.KEYS;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageReference;


import java.util.ArrayList;
import java.util.List;

import dev.NevoSharabi.quitnow.profile.User;

public class DBreader {

    private static  DBreader    instance;

    private         User         user;
    private         List        rewards_info = new ArrayList<>();

    //=============================

    public static void initReader(){
        if(instance == null) {
            instance = new DBreader();
            instance.readData();
        }
    }


    public static DBreader get() { return instance; }


    private void readData() {
        if (App.getLoggedUser() != null)
            get().readUserData();
        //get().readListData(KEYS.REWARDS_INFO_REF, rewards_info, String.class);
    }



    public void readUserData() {
        Refs.getUsersRef().child(App.getLoggedUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        user = dataSnapshot.getValue(User.class);
                        App.log("readUserData() - read user");
                    }

                    @Override
                    public void onCancelled(DatabaseError error) { }
                });
    }
//
//    //=========================================

    //=========================================

    public User getUser(){ return user; }


    public List getRewardsInfo(){ return rewards_info; }

}
