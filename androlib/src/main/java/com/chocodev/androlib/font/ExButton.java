package com.chocodev.androlib.font;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import com.chocodev.androlib.R;


/**
 * Created by DRM on 26/07/13.
 */
public class ExButton extends Button {
    public ExButton(Context context) {
        super(context);
    }

    public ExButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFromAttributes(context, attrs);
    }

    public ExButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initFromAttributes(context,attrs);
    }



    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
    }

    private void initFromAttributes(Context context,AttributeSet attrs) {
        if(!this.isInEditMode())
        {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ExButton, 0, 0);
            String fontName = ta.getString(R.styleable.ExButton_fontName);
            if(fontName!=null && !fontName.equals(""))
            {
                Typeface typeface= FontFactory.getInstance().getTypeface(context,fontName);
                if(typeface!=null)
                {
                    setTypeface(typeface);
                }
            }
        }
    }
}
