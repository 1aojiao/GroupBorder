package com.mrezanasirloo.groupbordered.listview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.mrezanasirloo.groupbordered.BuildConfig;
import com.mrezanasirloo.groupbordered.R;

import java.util.Locale;

/**
 * @author : M.Reza.Nasirloo@gmail.com
 *         Created on: 2018-03-24
 */
public class ItemDecoratorBorderListView extends ItemDecorationListView {

	private final Paint paint = new Paint();
	private final int size;

	public ItemDecoratorBorderListView(int size, @ColorInt int color) {
		this.size = size;
		paint.setColor(color);
		paint.setStrokeWidth(size);
		paint.setStyle(Paint.Style.STROKE);
	}

	public static final String TAG = ItemDecoratorBorderListView.class.getSimpleName();

	@Override
	public void onDrawOver(Canvas c, ListView parent) {
		super.onDrawOver(c, parent);
		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = parent.getChildAt(i);
			if (isHeader(child, parent, i)) {
				for (int j = i + 1; j < childCount; j++) {
					View childEnd = parent.getChildAt(j);
					boolean end = isHeader(childEnd, parent, i) || j == childCount - 1;
					if (end) {
						if (BuildConfig.DEBUG) { Log.d(TAG, String.format(Locale.ENGLISH, "Draw called i: %d, j: %d", i, j)); }
						childEnd = parent.getChildAt(j - 1);
						if (j == childCount - 1) { childEnd = parent.getChildAt(j); }
						float top = child.getTop() + child.getTranslationY() + size + child.getPaddingTop();
						float bottom = childEnd.getBottom() + childEnd.getTranslationY() - size - childEnd
								.getPaddingBottom();

						float right = child.getRight() + child.getTranslationX() - size - child.getPaddingRight();
						float left = child.getLeft() + child.getTranslationX() + size + child.getPaddingLeft();
						c.drawRect(left, top, right, bottom, paint);
						i = j - 1;
						break;
					}
				}
			}
		}
	}

	public boolean isHeader(View child, ListView parent, int position) {
		//Know a better way to get the viewType? Open an Issue
		return child.getId() == R.id.header;
	}

}
