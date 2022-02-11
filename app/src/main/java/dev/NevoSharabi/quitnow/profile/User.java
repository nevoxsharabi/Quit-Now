package dev.NevoSharabi.quitnow.profile;

import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import dev.NevoSharabi.quitnow.store.StoreItem;


public class User {
    private String  uid = "";
    private String  name = "";
    private double  yearsSmoked = 0;
    private int cigsPerWeek = 0;
    private double  pricePerPack = 0;
    private long    dateStoppedSmoking;

    private int     cigsPerPack = 1;
    private int     coins = 0;
    private HashMap<String, StoreItem> boughtItems = new HashMap<>();
    private long    loggedToday = -1;



    public User(){ }


    public String getUserId() {
        return uid;
    }







    public double cigsNotSmoked(){ return (TimeUnit.MILLISECONDS.toHours(getRehabDuration())) * cigsPerWeek/7/24; }

    public double moneySaved(){ return cigsNotSmoked()*cigCost();}


    public double cigCost(){ return pricePerPack; }

    public void incrementCoins(int amount) { this.coins += amount; }

    public void reduceCoins(int amount) { this.coins -= amount; }

    //=========================================
    public User setUid(String Uid) { uid = Uid; return this; }

    public User setName(String name) { this.name = name; return this; }


    public User setYearsSmoked(double yearsSmoked) { this.yearsSmoked = yearsSmoked; return this; }

    public User setPricePerPack(double pricePerPack) { this.pricePerPack = pricePerPack; return this; }

    public User setCigsPerWeek(int cigsPerWeek) { this.cigsPerWeek = cigsPerWeek; return this; }


    public User setCoins(int coins) { this.coins = coins; return this; }

    public User setDateStoppedSmoking(long dateStoppedSmoking) { this.dateStoppedSmoking = dateStoppedSmoking; return this; }

    public User setLoggedToday(long loggedToday) { this.loggedToday = loggedToday; return this;}

    public User setBoughtItems(HashMap<String,StoreItem> boughtItems) { this.boughtItems = boughtItems; return this; }




    //=========================================

    public String getUid() { return uid; }

    public String getName() { return name; }

    public double getYearsSmoked() { return yearsSmoked; }

    public double getPricePerPack() { return pricePerPack; }

    public int getCigsPerWeek() { return cigsPerWeek; }

    public int getCigsPerPack() { return cigsPerPack; }

    public int getCoins() { return coins; }

    public long getDateStoppedSmoking() { return dateStoppedSmoking; }

    public long getLoggedToday() { return loggedToday; }


    public long getRehabDuration(){ return Calendar.getInstance().getTimeInMillis() - dateStoppedSmoking; }


    public HashMap<String, StoreItem> getBoughtItems() { return boughtItems; }

    public User setCigsPerPack(int cigsPerPack) { this.cigsPerPack = cigsPerPack; return this; }
}
