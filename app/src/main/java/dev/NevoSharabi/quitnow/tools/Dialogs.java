package dev.NevoSharabi.quitnow.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import dev.NevoSharabi.quitnow.R;
import dev.NevoSharabi.quitnow.dateBase.*;
import dev.NevoSharabi.quitnow.profile.User;

//import com.scrounger.countrycurrencypicker.library.CountryCurrencyPicker;
//import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;
//import com.scrounger.countrycurrencypicker.library.PickerType;

public class Dialogs {

    private static  Dialogs         instance;

    private         LayoutInflater  inflater;
    private         Context         ctx;
    private         Utils           utils;

    //=============================

    /**
     * gets the singleton
     */
    public static Dialogs get() { return instance; }

    /**
     * must call addContext for using this object methods!
     */
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

    private View createDialogView(int layoutId){ return inflater.inflate(layoutId, null); }

    //====================================================

    /**
     *   creates AlertDialog to pick a currency
     */
//    public void createCurrencyDialog(FragmentManager fragmentManager, CountryCurrencyPickerListener listener){
//        CountryCurrencyPicker pickerDialog = CountryCurrencyPicker.newInstance(PickerType.COUNTRYandCURRENCY,listener);
//        pickerDialog.show(fragmentManager, CountryCurrencyPicker.DIALOG_NAME);
//    }

    //====================================================

    /**
     * creates AlertDialog for reset button click
     * @param listener OnClickListener for button
     */
//    public GenericDialog resetDialog(View.OnClickListener listener){
//        GenericDialog genericDialog = new GenericDialog(createDialogView(R.layout.dialog_reset))
//                                .findConfirmButtonById(R.id.reset_confirm)
//                                .addEditTexts(new int[] {R.id.reset_amount})
//                                .setConfirmListener(listener);
//        return genericDialog;
//    }
//
//
//
//    //====================================================
//
//    /**
//     * creates AlertDialog for daily login reward
//     */
//    public GenericDialog rewardDialog() {
//        GenericDialog genericDialog = new GenericDialog(createDialogView(R.layout.dialog_login_reward))
//                                .addTextViews(new int[] {R.id.reward_login_text});
//        return genericDialog;
//    }
//
//
//
//    //====================================================
//
//
//
//
//    /**
//     *   creates AlertDialog with user smoker history
//     */
//    public GenericDialog feedDialog(User user) {
//        int[] text_views_layout_arr     = {R.id.feed_cigs_smoked, R.id.feed_money_wasted, R.id.feed_life_lost};
//        GenericDialog genericDialog = new GenericDialog(createDialogView(R.layout.dialog_feed))
//                                    .addTextViews(text_views_layout_arr);
//
//        genericDialog.setTVtext(R.id.feed_cigs_smoked,"Cigarettes not smoked: "+
//                                utils.formatNumber(user.cigsNotSmoked(),"##.#"));
//
//        genericDialog.setTVtext(R.id.feed_money_wasted,"Money saved: "+
//                                utils.formatNumber(user.moneySaved(),"##.#") +" "+ user.getCurrencySymbol());
//
//        genericDialog.setTVtext(R.id.feed_life_lost,"life gained: "+
//                                utils.formatNumber(user.lifeGained(),"##.#") + " days");
//        return genericDialog;
//    }
//
//    //====================================================
//
//    /**
//     *   creates AlertDialog with user smoker history
//     */
//    public GenericDialog noInternetDialog() {
//        int[] text_views_layout_arr     = {R.id.internet_title, R.id.internet_text};
//        GenericDialog genericDialog = new GenericDialog(createDialogView(R.layout.dialog_no_internet))
//                .findConfirmButtonById(R.id.internet_btn_settings)
//                .addTextViews(text_views_layout_arr)
//                .setConfirmListener(v -> {
//                    Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
//                    ctx.startActivity(intent);
//                });
//
//        return genericDialog;
//    }

    //====================================================


}
