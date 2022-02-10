package dev.NevoSharabi.quitnow.tools;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import dev.NevoSharabi.quitnow.dateBase.DBreader;
import dev.NevoSharabi.quitnow.dateBase.DBupdater;
import dev.NevoSharabi.quitnow.login.SharedPrefs;
import dev.NevoSharabi.quitnow.store.Store;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class App extends Application {

    private static Context context;

    private static Toast myToast;

    private static ConnectivityManager connectivityManager;

    //====================================================

    @Override
    public void onCreate() {
        super.onCreate();
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("Users");
//        DatabaseReference myRef2 = database.getReference("Store_items");
        context         = getApplicationContext();
        myToast         = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //SharedPrefs     .initPrefs();
        DBupdater       .initUpdater();
        DBreader        .initReader();
        Utils           .initUtils();
        Store.initStore();
        Dialogs         .initDialogs();

    }

    //====================================================

    public static boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static Toast toast(String msg){
        myToast     .setText(msg);
        myToast     .show();
        return      myToast;
    }

    public static void log(String msg)          { Log.d(KEYS.LOG_TAG, msg); }

    public static Context getAppContext()       { return context; }

   public static FirebaseUser getLoggedUser()  { return FirebaseAuth.getInstance().getCurrentUser(); }

}
