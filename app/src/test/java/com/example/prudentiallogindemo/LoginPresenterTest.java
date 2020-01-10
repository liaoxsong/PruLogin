package com.example.prudentiallogindemo;



import com.example.prudentiallogindemo.login.LoginInteractor;
import com.example.prudentiallogindemo.login.LoginPresenter;
import com.example.prudentiallogindemo.login.LoginView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {


    @Mock
    LoginView view;

    private LoginPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new LoginPresenter(view, new LoginInteractor());
    }

    @Test
    public void testLoginWithNullFieldsResultInUserNameError() {
        presenter.validateLogin(null, null);
        verify(view, times(1)).setUserNameError();
        verify(view, times(0)).setPasswordError();
    }

    @Test
    public void testLoginWithEmptyPasswordResultInPasswordError() {
        presenter.validateLogin("something", null);
        verify(view, times(0)).setUserNameError();
        verify(view, times(1)).setPasswordError();
    }

    @Test
    public void testLoginWithNonNullFieldsResultInSuccess() {
        presenter.validateLogin("something", "pass");
        verify(view, times(0)).setUserNameError();
        verify(view, times(0)).setPasswordError();
        verify(view, times(1)).navigateToHome();
    }


}
