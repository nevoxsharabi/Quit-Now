package dev.NevoSharabi.quitnow.myDateBase;
import dev.NevoSharabi.quitnow.profile.*;

public class DataBaseUpDate {

    private static DataBaseUpDate instance;


    public static void initUpdater(){
        if(instance == null)
            instance = new DataBaseUpDate();
    }

    /**
     * gets the singleton
     */
    public static DataBaseUpDate get() { return instance; }



    /**
     * @param Uid user firebase id
     */public void deleteUserData(String Uid){
        Reference.getUsersRef().child(Uid).removeValue();
        Reference.getGiftBagsRef().child(Uid).removeValue();
    }


    /**
     * saves user in database
     * @param user user for update (not for logged user)
     */
    public void updateUser(User user){ Reference.getUsersRef().child(user.getUid()).setValue(user); }



    /**
     * saves current logged user in database
     */
    public void saveLoggedUser(){
        User loggedUser = DataBaseReader.get().getUser();
        Reference.getUsersRef().child(loggedUser.getUid()).setValue(loggedUser);
    }

    //=============================

    /**
     * saves giftBag in db ref after changes in bag
     * @param user
     */
    public void updateGiftBag(User user){ Reference.getGiftBagsRef().child(user.getUid()).setValue(user.getBoughtItems()); }


    /**
     * updates user status in database
     * on login and logout
     */
    public void updateStatus(){
        User user = DataBaseReader.get().getUser();
        if(user == null) return;
        DataBaseUpDate.get().saveLoggedUser();
    }

}
