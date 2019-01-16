package com.lenscommerce.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;

import com.lenscommerce.android.R;
import com.lenscommerce.android.ui.widget.HorizontalDottedProgress;
import com.lenscommerce.android.util.ConnectivityReceiver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.pb_splash)
    HorizontalDottedProgress dottedProgress;
    @BindView(R.id.iv_splash)
    AppCompatImageView ivLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        boolean isConnected = ConnectivityReceiver.isConnected();
        setupAnimation();
        setupProgressBar();
        if (isConnected) {
            setupSplash();
        } else {
            // TODO: 14/01/2019 setup error Connection
        }
    }

    private void setupAnimation() {
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(500);
        ivLogo.startAnimation(fadeIn);
    }

    private void setupProgressBar() {
        dottedProgress.setAutoPlay(true);
        dottedProgress.setDotsCount(3);
        dottedProgress.setDotsSpace(4);
        dottedProgress.setDotsColor(ContextCompat.getColor(getApplicationContext(),
                R.color.primaryTextColor));

    }

    private void setupSplash() {
        new Handler().postDelayed(() -> {
            finish();
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }, 2000);
    }
}
