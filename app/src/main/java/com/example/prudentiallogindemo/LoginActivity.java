package com.example.prudentiallogindemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.ColorUtils;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter presenter;
    private EditText usernameEdit, passwordEdit;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEdit = findViewById(R.id.edit_username);
        passwordEdit = findViewById(R.id.edit_password);
        loginButton = findViewById(R.id.btn_login);
        loginButton.setOnClickListener(v -> loginClicked());

        usernameEdit.addTextChangedListener(credentialTextWatcher);
        passwordEdit.addTextChangedListener(credentialTextWatcher);

        presenter = new LoginPresenter(this, new LoginInteractor());
    }

    private void loginClicked() {
        presenter.validateLogin(usernameEdit.getText().toString(),
                passwordEdit.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }


    @Override
    public void setUserNameError() {
        flashForError(usernameEdit);
    }

    @Override
    public void setPasswordError() {
        flashForError(passwordEdit);
    }

    private void flashForError(View view) {
        ObjectAnimator anim = ObjectAnimator.ofInt(view,
                "backgroundColor",
                Color.TRANSPARENT,
                ColorUtils.setAlphaComponent(getResources().getColor(R.color.colorBtnEnabled), 120),
                Color.TRANSPARENT);
        anim.setDuration(800);
        anim.setEvaluator(new ArgbEvaluator());
        anim.start();
    }

    private TextWatcher credentialTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            boolean hasEmptyField = TextUtils.isEmpty(usernameEdit.getText().toString()) || TextUtils.isEmpty(passwordEdit.getText().toString());
            if (hasEmptyField) {
                loginButton.setBackgroundColor(getResources().getColor(R.color.colorBtnDisabled));
            } else {
                loginButton.setBackgroundColor(getResources().getColor(R.color.colorBtnEnabled));
            }
        }
    };


    @Override
    public void navigateToHome() {

    }
}
