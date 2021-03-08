package com.lmx.common.weight;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.lmx.common.R;


public class ClearEditText
        extends TextInputEditText
        implements View.OnFocusChangeListener, View.OnTouchListener, TextWatcher {

    /**
     * 删除按钮图片资源
     */
    private Drawable clearDrawable;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        clearDrawable = getCompoundDrawables()[2];
        if (clearDrawable == null) {
            clearDrawable = getResources().getDrawable(R.drawable.ic_clear);
            clearDrawable.setBounds(0, 0, clearDrawable.getIntrinsicWidth(), clearDrawable.getMinimumHeight());
        }
        setClearIconVisible(false);
        setOnFocusChangeListener(this);
        setOnTouchListener(this);
        addTextChangedListener(this);
    }

    /**
     * 设置清除图标可见
     *
     * @param visible true为可见，false为不可见
     */
    public void setClearIconVisible(boolean visible) {
        Drawable right = visible ? clearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        setClearIconVisible(hasFocus && getText().length() > 0);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (getCompoundDrawables()[2] != null) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                // 点击在图标的横坐标上
                boolean onIconWidth = event.getX() > (getWidth() - getTotalPaddingRight())
                        && event.getX() < (getWidth() - getPaddingRight());
                if (onIconWidth) {
                    setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        setClearIconVisible(s.length() > 0);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
