package com.example.prudentiallogindemo.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.ColorUtils;

import com.example.prudentiallogindemo.R;
import com.example.prudentiallogindemo.home.HomeActivity;

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
        View errorView = view.getId() == R.id.edit_username ? findViewById(R.id.error_username) : findViewById(R.id.error_password);
        errorView.setVisibility(View.VISIBLE);
        ObjectAnimator anim = ObjectAnimator.ofInt(errorView,
                "backgroundColor",
                Color.TRANSPARENT,
                ColorUtils.setAlphaComponent(getResources().getColor(R.color.colorBtnEnabled), 80),
                Color.TRANSPARENT);
        anim.setDuration(1200);
        anim.setEvaluator(new ArgbEvaluator());
        anim.start();
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                errorView.setVisibility(View.GONE);
            }
        });
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
                loginButton.setBackgroundResource(R.drawable.rounded_button_invalid);
            } else {
                loginButton.setBackgroundResource(R.drawable.rounded_button_valid);
            }
        }
    };


    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, HomeActivity.class));
        this.overridePendingTransition(0, 0);//with animation
        finish();
    }
}
