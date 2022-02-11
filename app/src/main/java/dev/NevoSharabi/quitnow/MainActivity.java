package dev.NevoSharabi.quitnow;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.navigation.ui.NavigationUI;

import java.util.Arrays;
import java.util.List;

import dev.NevoSharabi.quitnow.myDateBase.DBreader;
import dev.NevoSharabi.quitnow.myDateBase.DBupdater;
import dev.NevoSharabi.quitnow.profile.CreateProfileActivity;
import dev.NevoSharabi.quitnow.profile.OnProfileUpdate;
import dev.NevoSharabi.quitnow.profile.User;
import dev.NevoSharabi.quitnow.store.OnCoinsChanged;
import dev.NevoSharabi.quitnow.tools.App;
import dev.NevoSharabi.quitnow.tools.Dialogs;
import dev.NevoSharabi.quitnow.tools.Utils;

public class MainActivity extends AppCompatActivity implements
        OnProfileUpdate, OnCoinsChanged{
    private DrawerLayout drawerLayout;
    private NavigationView nav_view;
    private NavController navController;


    private ImageView main_drawer_btn;
    private ImageView drawer_user_pic;

    private TextView main_lbl_title;
    private TextView drawer_lbl_userName;
    private TextView user_coins;

    private User user;
    private DBreader dbReader;
    private Activity activity;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        createSignInIntent();

        findViews();
        initDrawer();



    }

    @Override
    protected void onStop() {
        super.onStop();
        DBupdater.get().updateStatus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Dialogs.get().addContext(this);
        DBupdater.get().updateStatus();
    }

    public void createSignInIntent() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();
        signInLauncher.launch(signInIntent);

    }

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        Log.i("info", "==========i am before login check=====");
        if (result.getResultCode() == RESULT_OK) {
            Log.i("info", "==========successfully logged in=====");
            user = null;
            FirebaseUser firebase_user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference_users = database.getReference("Users");
            reference_users.child(firebase_user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //getting user from database if exists
                    user = snapshot.getValue(User.class);
                    if (user == null) {
                        //user does not exist in database
                        Log.i("info", "user is null");
                        Log.i("info", "im in readuserdata() UUID = " + firebase_user.getUid());
                        Intent intent   = new Intent(activity, CreateProfileActivity.class);
                        activity.startActivity(intent);
                    } else {
                        //user exists in database
                        Log.i("info", "user is not null");
                        Log.i("info", "im in readuserdata() UUID = " + user.getUid());
                        dbReader = DBreader.get();
                        user = dbReader.getUser();
                        if (!initServerConnection()) return;
                        setUserData();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        } else {
            Log.i("info", "==========didn't login=====");
        }
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
        drawer_lbl_userName.setText(user.getName());
        user_coins.setText("Coins - " + user.getCoins());
        //dbReader.get()      .readPicNoCache(KEYS.PROFILE, drawer_user_pic, user.getUid());
    }

    private boolean initServerConnection() {
        DBreader dbReader = DBreader.get();
        if (!App.isNetworkAvailable()) {
            return false;
        } else if (dbReader.getUser() == null) {
            DBreader.get().readUserData();
            Utils.get().myStartActivity(this, ActivitySplash.class);
            return false;
        }
        return true;
    }

    private void findViews() {
        drawerLayout = findViewById(R.id.drawerLayout);
        nav_view = findViewById(R.id.nav_view);
        main_drawer_btn = findViewById(R.id.main_drawer_btn);
        main_lbl_title = findViewById(R.id.main_lbl_title);
        user_coins = findViewById(R.id.user_coins);
        drawer_lbl_userName = nav_view.getHeaderView(0).findViewById(R.id.drawer_lbl_userName);
        drawer_user_pic = nav_view.getHeaderView(0).findViewById(R.id.drawer_user_pic);
    }





    @Override
    public void updateCoins() { // called when item is bought in store or when daily login performed
        user_coins.setText("Coins - " + dbReader.getUser().getCoins());
    }

    @Override
    public void updateProfile(User user) {
        // dbReader.get().readPicNoCache(KEYS.PROFILE, drawer_user_pic,user.getUid());
        drawer_lbl_userName.setText(user.getName());
    }


}