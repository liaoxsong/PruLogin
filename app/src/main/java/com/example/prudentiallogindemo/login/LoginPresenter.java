package com.example.prudentiallogindemo.login;

public class LoginPresenter implements LoginInteractor.OnLoginCompletedListener {


    private LoginView loginView;
    private final LoginInteractor loginInteractor;

    public LoginPresenter(LoginView view, LoginInteractor interactor) {
        this.loginView = view;
        this.loginInteractor = interactor;
    }

    public void validateLogin(String username, String password) {
        loginInteractor.login(username, password, this);
    }

    @Override
    public void onUserNameError() {
        if (loginView != null) {
            loginView.setUserNameError();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
        }
    }

    @Override
    public void onSuccess() {
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }

    public void destroy() {
        loginView = null;
    }

}
