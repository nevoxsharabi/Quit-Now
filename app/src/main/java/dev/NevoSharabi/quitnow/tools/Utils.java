package dev.NevoSharabi.quitnow.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

import com.furkanakdemir.surroundcardview.SurroundCardView;
//import com.github.drjacky.imagepicker.ImagePicker;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private static  Utils           instance;
    public static Utils get() { return instance; }


    public static void initUtils(){
        if(instance == null)
            instance = new Utils();
    }

    /**
     * formats a double by pattern
     * @param pattern #.## (number of # as the number of digits to show after . )
     */
    public String formatNumber(double number, String pattern){ return new DecimalFormat(pattern).format(number); }


    //====================================================

    public void onCardClick(SurroundCardView svc){
        if(svc.isCardSurrounded())
            svc.release();
        else
            svc.surround();
    }

    //====================================================


    public void myStartActivity(Activity activity, Class activityClass){
        Intent intent   = new Intent(activity, activityClass);
        activity        .startActivity(intent);
        activity        .finish();
    }




}
