package com.lenscommerce.android.util;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.lenscommerce.android.R;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class ConnectionError {
    public static void init(CoordinatorLayout root, Activity activity, retrofit2.Call apiCall,
                            OnActionClick onActionClick) {
        if (Build.VERSION.SDK_INT >= 21) {
            Snackbar snackbar = Snackbar.make(root, activity.getString(R.string.connection_error), Snackbar.LENGTH_INDEFINITE)
                    .setAction(activity.getString(R.string.retry), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onActionClick.onClick(apiCall);
                        }
                    });

        } else {
            CustomToast.createToast(activity, activity.getString(R.string.connection_error),
                    Toast.LENGTH_LONG);
        }
    }

    public interface OnActionClick {
        void onClick(retrofit2.Call apiCall);
    }
}
