package com.example.prudentiallogindemo;

import android.os.Handler;
import android.text.TextUtils;

public class LoginInteractor {
    interface OnLoginCompletedListener {
        void onUserNameError();
        void onPasswordError();
        void onSuccess();
    }

    public void login(final String username, final String password, final OnLoginCompletedListener listener) {
        //mock
        if (TextUtils.isEmpty(username)) {
            listener.onUserNameError();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
            return;
        }
        listener.onSuccess();
    }
}
