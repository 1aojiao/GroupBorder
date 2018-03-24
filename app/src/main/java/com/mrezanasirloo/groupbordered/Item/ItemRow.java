package com.mrezanasirloo.groupbordered.Item;

import android.view.View;

import com.mrezanasirloo.groupbordered.R;
import com.mrezanasirloo.groupbordered.viewholder.ViewHolderItem;
import com.mrezanasirloo.groupbordered.adapter.Item;

/**
 * @author : M.Reza.Nasirloo@gmail.com
 *         Created on: 2018-03-24
 */
public class ItemRow extends Item<ViewHolderItem> {

    private final String name;

    public ItemRow(String name) {
        this.name = name;
    }

    @Override
    public ViewHolderItem createViewHolder(View itemView) {
        return new ViewHolderItem(itemView);
    }

    @Override
    public void bind(ViewHolderItem viewHolder, int position) {
        viewHolder.text.setText(name);

    }

    @Override
    public int getLayout() {
        return R.layout.layout_row;
    }
}
