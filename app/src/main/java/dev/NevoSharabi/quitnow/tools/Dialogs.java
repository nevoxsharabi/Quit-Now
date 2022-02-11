package dev.NevoSharabi.quitnow.tools;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

public class Dialogs {

    private static  Dialogs         instance;

    private         LayoutInflater  inflater;
    private         Context         ctx;
    private         Utils           utils;



    /**
     * gets the singleton
     */
    public static Dialogs get() { return instance; }

    public static void initDialogs(){
        if(instance == null){
            instance = new Dialogs();
            instance.utils = Utils.get();
        }
    }

    /**
     * adds context for layout inflater
     * needs activity context not app context
     */
    public void addContext(Context activityContext){
        if(ctx == null || ((Activity)ctx).isFinishing()) {
            ctx = activityContext;
            inflater = LayoutInflater.from(activityContext);
        }
    }


}
