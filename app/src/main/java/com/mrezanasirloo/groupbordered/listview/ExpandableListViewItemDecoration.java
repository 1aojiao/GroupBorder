package com.mrezanasirloo.groupbordered.listview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

import com.mrezanasirloo.groupbordered.listview.ItemDecorationListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : M.Reza.Nasirloo@gmail.com
 *         Created on: 2018-03-25
 *
 *         Adds ItemDecoration feature like RecyclerView to ExpanableListView
 */
public class ExpandableListViewItemDecoration extends ExpandableListView {
	private List<ItemDecorationListView> itemDecorations = new ArrayList<>(1);

	public ExpandableListViewItemDecoration(Context context) {
		super(context);
	}

	public ExpandableListViewItemDecoration(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ExpandableListViewItemDecoration(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ExpandableListViewItemDecoration(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public void addItemDecoration(ItemDecorationListView item){
		itemDecorations.add(item);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		final int count = itemDecorations.size();
		for (int i = 0; i < count; i++) {
			itemDecorations.get(i).onDrawOver(canvas, this);
		}
	}
}
