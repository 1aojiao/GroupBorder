package com.mrezanasirloo.groupbordered;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.Locale;

/**
 * @author : M.Reza.Nasirloo@gmail.com
 *         Created on: 2018-03-24
 */
public class ItemDecoratorBorder extends RecyclerView.ItemDecoration {

    private final Paint paint = new Paint();
    private final int size;

    public ItemDecoratorBorder(int size, @ColorInt int color) {
        this.size = size;
        paint.setColor(color);
        paint.setStrokeWidth(size);
        paint.setStyle(Paint.Style.STROKE);
    }

    public static final String TAG = ItemDecoratorBorder.class.getSimpleName();

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (parent.getLayoutManager() == null) { return; }
        int childCount = parent.getChildCount();
        RecyclerView.LayoutManager lm = parent.getLayoutManager();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            if (isHeader(child, parent)) {
                for (int j = i + 1; j < childCount; j++) {
                    View childEnd = parent.getChildAt(j);
                    boolean end = isHeader(childEnd, parent) || j == childCount - 1;
                    if (end) {
                        if (BuildConfig.DEBUG) { Log.d(TAG, String.format(Locale.ENGLISH, "Draw called i: %d, j: %d", i, j)); }
                        childEnd = parent.getChildAt(j - 1);
                        if (j == childCount - 1) { childEnd = parent.getChildAt(j); }
                        float top = child.getTop() + child.getTranslationY() + size + child.getPaddingTop();
                        float bottom = lm.getDecoratedBottom(childEnd) + childEnd.getTranslationY() - size - childEnd
                                .getPaddingBottom();

                        float right = lm.getDecoratedRight(child) + child.getTranslationX() - size - child.getPaddingRight();
                        float left = lm.getDecoratedLeft(child) + child.getTranslationX() + size + child.getPaddingLeft();
                        c.drawRect(left, top, right, bottom, paint);
                        i = j - 1;
                        break;
                    }
                }
            }
        }
    }

    public boolean isHeader(View child, RecyclerView parent) {
        int viewType = parent.getLayoutManager().getItemViewType(child);
        return viewType == R.layout.layout_header;
    }
}
