package com.lenscommerce.android.ui.widget.slider;

import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author S.Shahini
 * @since 2/17/18
 */

class SnapHelper extends PagerSnapHelper {
    private OnSelectedItemChange onSelectedItemChange;
    private int lastPosition;

    public SnapHelper(OnSelectedItemChange onSelectedItemChange) {
        this.onSelectedItemChange = onSelectedItemChange;
    }

    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        int position = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
        if (position != RecyclerView.NO_POSITION && lastPosition != position && position < layoutManager.getItemCount()) {
            onSelectedItemChange.onSelectedItemChange(position);
            lastPosition = position;
        }
        return position;
    }

    public interface OnSelectedItemChange {
        void onSelectedItemChange(int position);
    }
}
