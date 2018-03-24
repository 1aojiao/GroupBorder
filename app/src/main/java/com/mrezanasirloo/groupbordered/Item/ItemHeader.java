package com.mrezanasirloo.groupbordered.Item;

import android.support.annotation.NonNull;
import android.view.View;

import com.mrezanasirloo.groupbordered.R;
import com.mrezanasirloo.groupbordered.viewholder.ViewHolderHeader;
import com.mrezanasirloo.groupbordered.adapter.Item;

/**
 * @author : M.Reza.Nasirloo@gmail.com
 *         Created on: 2018-03-24
 */
public class ItemHeader extends Item<ViewHolderHeader> {

    private final String header;

    public ItemHeader(String header) {
        this.header = header;
    }

    @Override
    public ViewHolderHeader createViewHolder(View itemView) {
        return new ViewHolderHeader(itemView);
    }

    @Override
    public void bind(@NonNull ViewHolderHeader viewHolder, int position) {
        viewHolder.header.setText(header);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_header;
    }
}
