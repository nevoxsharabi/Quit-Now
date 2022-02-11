package dev.NevoSharabi.quitnow.profile;

import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import dev.NevoSharabi.quitnow.store.StoreItem;
import dev.NevoSharabi.quitnow.tools.KEYS;

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

    private double  cigsSinceQuit = 0;
    private long    loggedToday = -1;


    private KEYS.Status status = KEYS.Status.Offline;
    public User(){ }


    public String getUserId() {
        return uid;
    }




    public double totalCigsSmoked(){ return (yearsSmoked * KEYS.DAYS_IN_YEAR * cigsPerWeek) + cigsSinceQuit; }


    public double cigsNotSmoked(){ return (TimeUnit.MILLISECONDS.toHours(getRehabDuration())) * cigsPerWeek/7/24; }

    public double moneySaved(){ return cigsNotSmoked()*cigCost();}


    private double timeByCig(double cigs){ return (KEYS.MINUTES_LOST_PER_CIG * cigs) / 60 / 24; }

    public double cigCost(){ return pricePerPack/cigsPerPack; }

    public void incrementCoins(int amount) { this.coins += amount; }

    public void reduceCoins(int amount) { this.coins -= amount; }

    //=========================================
    public User setUid(String Uid) { uid = Uid; return this; }

    public User setName(String name) { this.name = name; return this; }


    public User setYearsSmoked(double yearsSmoked) { this.yearsSmoked = yearsSmoked; return this; }

    public User setPricePerPack(double pricePerPack) { this.pricePerPack = pricePerPack; return this; }

    public User setCigsPerWeek(int cigsPerWeek) { this.cigsPerWeek = cigsPerWeek; return this; }

    public User setCigsPerPack(int cigsPerPack) { this.cigsPerPack = cigsPerPack; return this; }

    public User setCoins(int coins) { this.coins = coins; return this; }

    public User setDateStoppedSmoking(long dateStoppedSmoking) { this.dateStoppedSmoking = dateStoppedSmoking; return this; }

    public User setLoggedToday(long loggedToday) { this.loggedToday = loggedToday; return this;}

    public User setBoughtItems(HashMap<String,StoreItem> boughtItems) { this.boughtItems = boughtItems; return this; }


    public User setStatus(KEYS.Status status) { this.status = status; return this; }

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

    public KEYS.Status getStatus() { return status; }

    public HashMap<String, StoreItem> getBoughtItems() { return boughtItems; }
}
