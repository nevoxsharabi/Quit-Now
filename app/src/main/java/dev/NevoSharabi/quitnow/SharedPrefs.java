package dev.NevoSharabi.quitnow;

import android.content.Context;
import android.content.SharedPreferences;

import dev.NevoSharabi.quitnow.tools.App;

public class SharedPrefs {

    private static String TAG = "SharedPrefs";
    private String FIRST_LOGIN = "FIRST_LOGIN";
    private String THEME = "THEME";

    private static SharedPrefs instance;
    private SharedPreferences prefs;

    //====================================================

    public static void initPrefs(){
        if(instance == null) {
            instance = new SharedPrefs();
            instance.prefs =  App.getAppContext().getSharedPreferences(TAG,Context.MODE_PRIVATE);
        }
    }

    /**
     * gets the singleton
     */
    public static SharedPrefs get() { return instance; }

    //====================================================

    public Boolean isFirstLogin(){ return prefs.getBoolean(FIRST_LOGIN, true); }

    public void saveFirstLogin(){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(FIRST_LOGIN,false);
        editor.apply();
    }

    public void deleteFirstLogin() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(FIRST_LOGIN);
        editor.apply();
    }





}