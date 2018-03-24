package com.mrezanasirloo.groupbordered.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.mrezanasirloo.groupbordered.R;
import com.mrezanasirloo.groupbordered.adapter.ViewHolder;

/**
 * @author : M.Reza.Nasirloo@gmail.com
 *         Created on: 2018-03-24
 */
public class ViewHolderItem extends ViewHolder {

    public final TextView text;

    public ViewHolderItem(@NonNull View rootView) {
        super(rootView);
        text = rootView.findViewById(R.id.textView_row);
    }
}
