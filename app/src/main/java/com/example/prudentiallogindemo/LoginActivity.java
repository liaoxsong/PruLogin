package com.example.prudentiallogindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText usernameEdit = findViewById(R.id.edit_username);

        ObjectAnimator anim = ObjectAnimator.ofInt(usernameEdit, "backgroundColor", Color.TRANSPARENT, Color.BLUE,
                Color.TRANSPARENT);
        anim.setDuration(1500);
        anim.setStartDelay(2000);
        anim.setEvaluator(new ArgbEvaluator());
//        anim.setRepeatMode(Animation.REVERSE);
//        anim.setRepeatCount(Animation.INFINITE);
      //  anim.start();
    }
}
