package dev.NevoSharabi.quitnow;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.navigation.ui.NavigationUI;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import dev.NevoSharabi.quitnow.dateBase.DBreader;
import dev.NevoSharabi.quitnow.dateBase.DBupdater;
import dev.NevoSharabi.quitnow.login.SharedPrefs;
import dev.NevoSharabi.quitnow.profile.OnProfileUpdate;
import dev.NevoSharabi.quitnow.profile.ProfileActivity;
import dev.NevoSharabi.quitnow.profile.User;
import dev.NevoSharabi.quitnow.store.OnCoinsChanged;
import dev.NevoSharabi.quitnow.tools.App;
import dev.NevoSharabi.quitnow.tools.Dialogs;
import dev.NevoSharabi.quitnow.tools.KEYS;
import dev.NevoSharabi.quitnow.tools.OnFragmentTransaction;
import dev.NevoSharabi.quitnow.tools.Utils;

public class MainActivity extends AppCompatActivity implements
        OnProfileUpdate, OnCoinsChanged,
        OnFragmentTransaction {
    private DrawerLayout drawerLayout;
    private NavigationView nav_view;
    private NavController navController;


    private ImageView main_drawer_btn;
    private ImageView drawer_user_pic;

    private TextView main_lbl_title;
    private TextView drawer_lbl_userName;
    private TextView user_coins;

    private User            user;
    private DBreader dbReader;

    @Override
    protected void onStop() {
        super.onStop();
       //DBupdater.get().updateStatus(KEYS.Status.Offline);
    }

    @Override
    protected void onResume() {
        super.onResume();
      //  Dialogs.get().addContext(this);
     //   DBupdater.get().updateStatus(KEYS.Status.Online);
    }
    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Utils.get().onActivityCreateSetTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSignInIntent();

//        dbReader = DBreader.get();
//        user = dbReader.getUser();
////
//        DBupdater.get().updateStatus(KEYS.Status.Online);
//   //     Dialogs.get().addContext(this);
//
//        if(SharedPrefs.get().isFirstLogin()) {
//            Utils.get().myStartActivity(this, ProfileActivity.class);
//            return;
//        }
//
        findViews();
        initDrawer();
//
//        if(!initServerConnection()) return;
//s
        setUserData();
        checkFirstLogin();

//        //test
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Hello, World!");

    }
    public void createSignInIntent() {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        // Create and launch sign-in intent
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();
        signInLauncher.launch(signInIntent);
        // [END auth_fui_create_intent]
    }

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            assert user != null;
//            Log.i("info", "User ID: " + user.getUid());
//            Log.i("info", "User Display Name: " + user.getDisplayName());
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

    public void signOut() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }
//    private void rewardDailyLogin(){
//        user.setLoggedToday(Calendar.getInstance().getTimeInMillis());
//        user.incrementCoins(1500);
//        updateWallet();
//        Dialogs.get().rewardDialog().show();
//        DBupdater.get().updateUser(user);
//    }

    private boolean initServerConnection() {
        DBreader dbReader = DBreader.get();
        if(!App.isNetworkAvailable()){
            return false;
        } else if (dbReader.getUser() == null) { // if data hasn't arrived from db yet
            App.log("initServerConnection() - trying to fetch user");
            DBreader.get().readUserData();
            Utils.get().myStartActivity(this, ActivitySplash.class);
            return false;
        }
        App.log("initServerConnection() - fetched user");
        return true;
    }

    private void checkFirstLogin() {
        long daysPassed = TimeUnit.MILLISECONDS.toDays(
                Calendar.getInstance().getTimeInMillis() - user.getLoggedToday());
        if(daysPassed < 1 && user.getLoggedToday() != -1) return;

        //rewardDailyLogin();
    }
    private void findViews() {
        drawerLayout        = findViewById(R.id.drawerLayout);
        nav_view            = findViewById(R.id.nav_view);
        main_drawer_btn     = findViewById(R.id.main_drawer_btn);
        main_lbl_title      = findViewById(R.id.main_lbl_title);
        user_coins          = findViewById(R.id.user_coins);
        drawer_lbl_userName = nav_view.getHeaderView(0).findViewById(R.id.drawer_lbl_userName);
        drawer_user_pic     = nav_view.getHeaderView(0).findViewById(R.id.drawer_user_pic);
    }


    private void initDrawer() {
        nav_view.setItemIconTintList(null);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHost_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(nav_view, navController);
        main_drawer_btn.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> main_lbl_title.setText(destination.getLabel()));
    }

    private void setUserData() {
        drawer_lbl_userName .setText(user.getName());
        user_coins          .setText("Coins - "+ user.getCoins());
        dbReader.readPicNoCache(KEYS.PROFILE, drawer_user_pic, user.getUid());
    }
    @Override
    public void updateWallet() { // called when item is bought in store or when daily login performed
        user_coins.setText("Coins - " + dbReader.getUser().getCoins());
    }

    @Override
    public void updateProfile(User user) {
        dbReader.get().readPicNoCache(KEYS.PROFILE, drawer_user_pic,user.getUid());
        drawer_lbl_userName.setText(user.getName());
    }

    @Override
    public void setFragmentToView(Fragment fragment, int layout_id){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(layout_id, fragment).commit();
    }
}