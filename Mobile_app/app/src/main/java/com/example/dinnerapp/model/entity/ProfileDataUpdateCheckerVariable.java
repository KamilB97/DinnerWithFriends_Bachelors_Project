package com.example.dinnerapp.model.entity;

public class ProfileDataUpdateCheckerVariable {

    private boolean profileDataDownloaded = false;
    private ProfileDataUpdateCheckerVariable.ChangeListener listener;

    public boolean isProfileDataDownloaded() {
        return profileDataDownloaded;
    }

    public void setProfilUpdated(boolean successful) {
        this.profileDataDownloaded = successful;
        if (listener != null) listener.onChange();
    }

    public ProfileDataUpdateCheckerVariable.ChangeListener getListener() {
        return listener;
    }

    public void setListener(ProfileDataUpdateCheckerVariable.ChangeListener listener) {
        this.listener = listener;
    }

    public interface ChangeListener {
        void onChange();
    }

}
