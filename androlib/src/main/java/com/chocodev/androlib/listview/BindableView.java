package com.chocodev.androlib.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by mobivery on 19/09/13.
 */
public abstract class BindableView<T> extends RelativeLayout {
    private ListEventListener<T> listEventListener;

    public BindableView(Context context) {
        super(context);
    }

    public BindableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BindableView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public abstract void bind(T object);

    public void setListEventListener(ListEventListener<T> listEventListener) {
        this.listEventListener = listEventListener;
    }

    public ListEventListener<T> getListEventListener() {
        return this.listEventListener;
    }
}
