package dev.NevoSharabi.quitnow.tools;

public interface KEYS {

    String  LOG_TAG                 = "-QuitNow-";
    String  USERS_REF               = "https://quit-now-4b3c3-default-rtdb.europe-west1.firebasedatabase.app/Users";
    String  STORE_REF               = "https://quit-now-4b3c3-default-rtdb.europe-west1.firebasedatabase.app/Store_items";
    String  GIFT_BAGS_REF           = "https://quit-now-4b3c3-default-rtdb.europe-west1.firebasedatabase.app/GiftBags";
    String  STORE_PICS_REF          = "gs://quit-now-4b3c3.appspot.com/store_pics/";




    int     STORE                   = 1;
    int     PROFILE                 = 2;
    int     REWARDS_AMOUNT          = 13;
    int     DAYS_IN_YEAR            = 365;
    int     MINUTES_LOST_PER_CIG    = 11;


    enum Status { Online, Offline}

}