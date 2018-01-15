package com.flagantz.forminput;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.TextView;


public abstract class BaseForm extends FrameLayout {
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_BOLD = 1;
    private static final int TYPE_ITALIC = 2;

    TextView mHeaderTextView;

    String mHeaderText;
    int mHeaderTextSize;
    int mHeaderTextStyle;
    int mHeaderTextColor;
    int mHeaderTextPadding;
    int mHeaderBackgroundColor;
    int mInputTextSize;
    int mInputColor;
    int mInputPadding;
    int mInputBackgroundDrawable;

    public BaseForm(@NonNull Context context) {
        this(context, null);
    }

    public BaseForm(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        if (attrs != null) {
            final TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.BaseForm);
            mHeaderText = a.getString(R.styleable.BaseForm_bf_header_text);
            mHeaderTextSize = a.getDimensionPixelSize(R.styleable.BaseForm_bf_header_text_size, 0);
            mHeaderTextStyle = a.getInt(R.styleable.BaseForm_bf_header_text_style, TYPE_NORMAL);
            mHeaderTextColor = a.getColor(R.styleable.BaseForm_bf_header_text_color, Color.BLACK);
            mHeaderTextPadding =
                    a.getDimensionPixelSize(R.styleable.BaseForm_bf_header_text_padding, 0);
            mHeaderBackgroundColor =
                    a.getColor(R.styleable.BaseForm_bf_header_background_color, Color.TRANSPARENT);
            mInputTextSize = a.getDimensionPixelSize(R.styleable.BaseForm_bf_input_text_size, 0);
            mInputColor = a.getColor(R.styleable.BaseForm_bf_input_text_color, Color.BLACK);
            mInputPadding = a.getDimensionPixelSize(R.styleable.BaseForm_bf_input_text_padding, 0);
            mInputBackgroundDrawable =
                    a.getResourceId(R.styleable.BaseForm_bf_input_background_drawable, 0);
            a.recycle();
        }
    }

    public abstract void init();

    public void initHeaders() {
        if (mHeaderTextView == null) {
            return;
        }

        if (mHeaderText != null) {
            setHeaderText(mHeaderText);

            if (mHeaderTextSize > 0) {
                mHeaderTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mHeaderTextSize);
            }

            mHeaderTextView.setTextColor(mHeaderTextColor);
            mHeaderTextView.setTypeface(mHeaderTextView.getTypeface(),
                    getSelectedTypeface(mHeaderTextStyle));

            if (mHeaderTextPadding > 0) {
                mHeaderTextView.setPadding(mHeaderTextPadding, mHeaderTextPadding, mHeaderTextPadding,
                        mHeaderTextPadding);
            }

            mHeaderTextView.setBackgroundColor(mHeaderBackgroundColor);
        } else {
            mHeaderTextView.setVisibility(GONE);
        }
    }

    public abstract void setHeaderText(String headerText);

    public abstract String getInputValue();

    protected int getSelectedTypeface(int typeText) {
        switch (typeText) {
            case TYPE_NORMAL :
                return Typeface.NORMAL;
            case TYPE_BOLD :
                return Typeface.BOLD;
            case TYPE_ITALIC :
            default:
                return Typeface.ITALIC;
        }
    }

}
