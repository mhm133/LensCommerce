package com.lenscommerce.android.ui.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.lenscommerce.android.R;

import androidx.annotation.RequiresApi;

public class MaterialProgressBar extends ProgressBar {
    public MaterialProgressBar(Context context) {
        super(context);
        setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar));
    }

    public MaterialProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar));
    }

    public MaterialProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MaterialProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar));
    }
}
