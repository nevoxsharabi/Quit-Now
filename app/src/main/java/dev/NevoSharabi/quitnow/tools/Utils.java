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

    private         MediaPlayer     mp;

    //=============================

    /**
     * gets the singleton
     */
    public static Utils get() { return instance; }

    /**
     * must call addContext for using this object methods!
     */
    public static void initUtils(){
        if(instance == null)
            instance = new Utils();
    }





    //====================================================

    /**
     * formats a double by pattern
     * @param pattern #.## (number of # as the number of digits to show after . )
     */
    public String formatNumber(double number, String pattern){ return new DecimalFormat(pattern).format(number); }

    public String formatToDate(long time){
        SimpleDateFormat formatter= new SimpleDateFormat("MM-dd 'at' HH:mm");
        Date date = new Date(time);
        return formatter.format(date);
    }

    //====================================================

    public void onCardClick(SurroundCardView svc){
        if(svc.isCardSurrounded())
            svc.release();
        else
            svc.surround();
    }

    //====================================================


    /**
     * plays sound
     * @param rawSound sound id
     */
    public void playSound(Context context, int rawSound) {
        if(mp != null) mp.reset();
        mp = MediaPlayer.create(context,rawSound);
        mp.start();
    }

    //====================================================

    /**
     *   starts activity
     */
    public void myStartActivity(Activity activity, Class activityClass){
        Intent intent   = new Intent(activity, activityClass);
        activity        .startActivity(intent);
        activity        .finish();
    }



//    /**
//     *   gets image from imagePicker in fragment
//     */
//    public void getImage(Fragment fragment) {
//        ImagePicker.Companion
//                    .with(fragment)
//                    .crop()
//                    .cropOval()
//                    .cropSquare()
//                    .compress(1024)
//                    .maxResultSize(1080, 1080)
//                    .start();
//    }
//
//    /**
//     *   gets image from imagePicker in activity
//     */
//    public void getImage(Activity activity) {
//        ImagePicker.Companion
//                    .with(activity)
//                    .crop()
//                    .cropOval()
//                    .cropSquare()
//                    .compress(1024)
//                    .maxResultSize(1080, 1080)
//                    .start();
//    }

    //====================================================


}
