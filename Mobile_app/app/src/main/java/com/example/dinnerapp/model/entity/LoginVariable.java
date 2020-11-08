package com.example.dinnerapp.model.entity;

public class LoginVariable {

    private boolean loginSuccessful = false;
    private ChangeListener listener;

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }

    public void setLoginSuccessful(boolean successful) {
        this.loginSuccessful = successful;
        if (listener != null) listener.onChange();
    }

    public ChangeListener getListener() {
        return listener;
    }

    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }

    public interface ChangeListener {
        void onChange();
    }

}
