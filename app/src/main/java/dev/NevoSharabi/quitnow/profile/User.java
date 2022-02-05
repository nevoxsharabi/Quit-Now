package dev.NevoSharabi.quitnow.profile;

import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import dev.NevoSharabi.quitnow.store.StoreItem;
import dev.NevoSharabi.quitnow.tools.KEYS;

public class User {
    private String  uid = "";
    private String  name = "";
    private String  currencySymbol = "";

    private int     cigsPerDay = 0;
    private int     cigsPerPack = 1;
    private int     coins = 0;
    //private int     highScore = 0;

    private double  cigsSinceQuit = 0;
    private double  yearsSmoked = 0;
    private double  pricePerPack = 0;

    private long    dateStoppedSmoking;
    private long    loggedToday = -1;
    private long    lastSeen;

    private KEYS.Status status = KEYS.Status.Offline;

    private HashMap<String, StoreItem> boughtItems = new HashMap<>();

    //=========================================

    public User(){ }


    //========================================= before stopi

    public double totalCigsSmoked(){ return (yearsSmoked * KEYS.DAYS_IN_YEAR * cigsPerDay) + cigsSinceQuit; }

    public double moneyWasted(){ return totalCigsSmoked()*cigCost(); }

    public double lifeLost(){ return timeByCig(totalCigsSmoked()); }

    //========================================= after stopi

    public double cigsNotSmoked(){ return (TimeUnit.MILLISECONDS.toHours(getRehabDuration())/24) * cigsPerDay; }

    public double moneySaved(){ return cigsNotSmoked()*cigCost();}

    public double lifeGained(){ return timeByCig(cigsNotSmoked()); }

    //=========================================

    public boolean updateTotalCigs(double cigsSmoked) {
        if(cigsSmoked > 0){
            this.setDateStoppedSmoking(Calendar.getInstance().getTimeInMillis());
            return true;
        }
        return false;
    }

    private double timeByCig(double cigs){ return (KEYS.MINUTES_LOST_PER_CIG * cigs) / 60 / 24; }

    public double cigCost(){ return pricePerPack/cigsPerPack; }

    public void incrementCoins(int amount) { this.coins += amount; }

    public void reduceCoins(int amount) { this.coins -= amount; }

    //=========================================
    public User setUid(String Uid) { uid = Uid; return this; }

    public User setName(String name) { this.name = name; return this; }


    public User setYearsSmoked(double yearsSmoked) { this.yearsSmoked = yearsSmoked; return this; }

    public User setPricePerPack(double pricePerPack) { this.pricePerPack = pricePerPack; return this; }

    public User setCigsPerDay(int cigsPerDay) { this.cigsPerDay = cigsPerDay; return this; }

    public User setCigsPerPack(int cigsPerPack) { this.cigsPerPack = cigsPerPack; return this; }

    public User setCoins(int coins) { this.coins = coins; return this; }

    public User setDateStoppedSmoking(long dateStoppedSmoking) { this.dateStoppedSmoking = dateStoppedSmoking; return this; }

    public User setLoggedToday(long loggedToday) { this.loggedToday = loggedToday; return this;}

    public User setLastSeen(long lastSeen) { this.lastSeen = lastSeen; return this; }

    public User setBoughtItems(HashMap<String,StoreItem> boughtItems) { this.boughtItems = boughtItems; return this; }

    public User setCurrencySymbol(String currencySymbol) { this.currencySymbol = currencySymbol; return this; }

    public User setStatus(KEYS.Status status) { this.status = status; return this; }

    //=========================================

    public String getUid() { return uid; }

    public String getName() { return name; }

    public double getYearsSmoked() { return yearsSmoked; }

    public double getPricePerPack() { return pricePerPack; }

    public int getCigsPerDay() { return cigsPerDay; }

    public int getCigsPerPack() { return cigsPerPack; }

    public int getCoins() { return coins; }

    public long getDateStoppedSmoking() { return dateStoppedSmoking; }

    public long getLoggedToday() { return loggedToday; }

    public long getLastSeen() { return lastSeen; }

    public long getRehabDuration(){ return Calendar.getInstance().getTimeInMillis() - dateStoppedSmoking; }

    public KEYS.Status getStatus() { return status; }

    public String getCurrencySymbol() { return currencySymbol; }

    public HashMap<String, StoreItem> getBoughtItems() { return boughtItems; }
}
