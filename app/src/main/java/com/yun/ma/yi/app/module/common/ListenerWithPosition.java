package com.yun.ma.yi.app.module.common;

import android.view.View;

/**
 * Author: ys
 * Date: 2017-04-07  16:31
 */

public class ListenerWithPosition implements View.OnClickListener {

    private int mPosition;
    private Object mHolder;
    private OnClickWithPositionListener mOnClickListener;

    public ListenerWithPosition(int position, Object holder) {
        this.mPosition = position;
        this.mHolder = holder;
    }

    @Override
    public void onClick(View v) {
        if (mOnClickListener != null)
            mOnClickListener.onClick(v, mPosition, mHolder);
    }

    public interface OnClickWithPositionListener<T> {
        void onClick(View v, int position, T holder);
    }

    public void setOnClickListener(OnClickWithPositionListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }
}
