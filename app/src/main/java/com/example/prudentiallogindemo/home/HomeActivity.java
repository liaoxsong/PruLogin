package com.example.prudentiallogindemo.home;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prudentiallogindemo.R;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            enterReveal();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void enterReveal() {
        // previously invisible view
        final View myView = findViewById(R.id.layout_home);
        myView.post(() -> {
            //start from center bottom
            int cx = myView.getMeasuredWidth() / 2;
            int cy = myView.getMeasuredHeight();

            // get the final radius for the clipping circle
            int finalRadius = Math.max(myView.getWidth(), myView.getHeight()) / 2;

            // create the animator for this view (the start radius is zero)
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);

            // make the view visible and start the animation
            anim.start();
        });

    }
}
