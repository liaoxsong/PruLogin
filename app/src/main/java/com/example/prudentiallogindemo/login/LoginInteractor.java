package com.example.prudentiallogindemo.login;

import android.os.Handler;
import android.text.TextUtils;

import com.example.prudentiallogindemo.utils.StringUtils;

public class LoginInteractor {

    interface OnLoginCompletedListener {
        void onUserNameError();
        void onPasswordError();
        void onSuccess();
    }

    public void login(final String username, final String password, final OnLoginCompletedListener listener) {
        //mock
        if (StringUtils.isEmpty(username)) {
            listener.onUserNameError();
            return;
        }
        if (StringUtils.isEmpty(password)) {
            listener.onPasswordError();
            return;
        }
        listener.onSuccess();
    }
}
