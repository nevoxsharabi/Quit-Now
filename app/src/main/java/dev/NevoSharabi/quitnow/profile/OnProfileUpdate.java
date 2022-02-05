package dev.NevoSharabi.quitnow.profile;

public interface OnProfileUpdate {

    /**
     * called when data is updated in settings
     */
    void updateProfile(User user);

}
