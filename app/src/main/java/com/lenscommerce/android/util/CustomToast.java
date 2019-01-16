package com.lenscommerce.android.util;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lenscommerce.android.R;

import androidx.appcompat.widget.AppCompatTextView;

public class CustomToast {
    public static void createToast(Activity activity, String message, int duration) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) activity.findViewById(R.id.toast_root_layout));

        AppCompatTextView text = layout.findViewById(R.id.tv_toast);
        text.setText(message);

        Toast toast = new Toast(activity);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 24);
        toast.setDuration(duration);
        toast.setView(layout);
        toast.show();
    }
}
