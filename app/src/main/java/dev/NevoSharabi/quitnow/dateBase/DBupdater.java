package dev.NevoSharabi.quitnow.dateBase;



import dev.NevoSharabi.quitnow.profile.*;

public class DBupdater {

    private static DBupdater instance;

    //=============================

    public static void initUpdater(){
        if(instance == null)
            instance = new DBupdater();
    }

    /**
     * gets the singleton
     */
    public static DBupdater get() { return instance; }

    //=============================

    /**
     * deletes all data , emails and profile pic
     * @param Uid user firebase id
     */public void deleteUserData(String Uid){
        Refs.getUsersRef().child(Uid).removeValue();
        Refs.getGiftBagsRef().child(Uid).removeValue();
//        DBupdater.get().deleteProfilePic(Uid);
    }



    //=============================

    /**
     * saves user in database
     * @param user user for update (not for logged user)
     */
    public void updateUser(User user){ Refs.getUsersRef().child(user.getUid()).setValue(user); }



    /**
     * saves current logged user in database
     */
    public void saveLoggedUser(){
        User loggedUser = DBreader.get().getUser();
        Refs.getUsersRef().child(loggedUser.getUid()).setValue(loggedUser);
    }

    //=============================

    /**
     * saves giftBag in db ref after changes in bag
     * @param user
     */
    public void updateGiftBag(User user){ Refs.getGiftBagsRef().child(user.getUid()).setValue(user.getBoughtItems()); }


    /**
     * updates user status in database
     * on login and logout
     */
    public void updateStatus(){
        User user = DBreader.get().getUser();
        if(user == null) return;
        DBupdater.get().saveLoggedUser();
    }

}
