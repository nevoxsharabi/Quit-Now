package dev.NevoSharabi.quitnow.tools;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import dev.NevoSharabi.quitnow.myDateBase.DataBaseReader;
import dev.NevoSharabi.quitnow.myDateBase.DataBaseUpDate;

import dev.NevoSharabi.quitnow.store.Store;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class App extends Application {

    private static Context context;
    private static Toast myToast;
    private static ConnectivityManager connectivityManager;



    @Override
    public void onCreate() {
        super.onCreate();
        context         = getApplicationContext();
        myToast         = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        DataBaseUpDate.initUpdater();
        DataBaseReader.initReader();
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


    public static Context getAppContext()       { return context; }

   public static FirebaseUser getLoggedUser()  { return FirebaseAuth.getInstance().getCurrentUser(); }

}
