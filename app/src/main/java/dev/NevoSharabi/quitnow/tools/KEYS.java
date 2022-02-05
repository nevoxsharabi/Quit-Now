package dev.NevoSharabi.quitnow.tools;

public interface KEYS {

    String  LOG_TAG                 = "-QuitNow-";

    String  USERS_REF               = "https://quit-now-4b3c3-default-rtdb.firebaseio.com/Users";
    String  TIPS_REF                = "https://fumi-app-default-rtdb.firebaseio.com/Tips";
    String  REWARDS_INFO_REF        = "https://fumi-app-default-rtdb.firebaseio.com/Rewards_Info";
    String  STORE_REF               = "https://fumi-app-default-rtdb.firebaseio.com/Store_items";
    String  CHATS_REF               = "https://fumi-app-default-rtdb.firebaseio.com/Chats";
    String  GIFT_BAGS_REF           = "https://fumi-app-default-rtdb.firebaseio.com/GiftBags";

    String  STORE_PICS_REF          = "gs://fumi-app.appspot.com/store_pics/";
    String  FULL_PROFILE_PIC_URL    = "gs://fumi-app.appspot.com/profile_pics/";

    int     GOOGLE_SIGN_IN_CODE     = 123;

    int     THEME_PURPLE            = 0;
    int     THEME_TEAL              = 1;
    int     THEME_BLUE              = 2;
    int     THEME_ORANGE            = 3;

    int     STORE                   = 1;
    int     PROFILE                 = 2;
    int     REWARDS_AMOUNT          = 13;
    int     DAYS_IN_YEAR            = 365;
    int     MINUTES_LOST_PER_CIG    = 11;
    int     BALL_RADIUS             = 40;

    float   REBOUND                 = 0.6f;

    enum Status { Online, Offline}

}