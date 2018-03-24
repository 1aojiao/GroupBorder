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
public class ViewHolderHeader extends ViewHolder {

    public final TextView header;

    public ViewHolderHeader(@NonNull View rootView) {
        super(rootView);
        header = rootView.findViewById(R.id.textView_header);
    }
}
