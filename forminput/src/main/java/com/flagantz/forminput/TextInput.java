package com.flagantz.forminput;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.EditText;


public class TextInput extends BaseForm {

    EditText mInputTextView;

    String mText;
    String mHint;
    int mTextType;

    public TextInput(@NonNull Context context) {
        this(context, null);
    }

    public TextInput(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextInput);
            mText = a.getString(R.styleable.TextInput_bf_input_text);
            mHint = a.getString(R.styleable.TextInput_bf_input_hint);
            mTextType = a.getInt(R.styleable.TextInput_bf_input_text_type, 0);

            a.recycle();
        }

        inflate(context, R.layout.form_text_input_view, this);

        init();
    }

    @Override
    public void init() {
        mHeaderTextView = findViewById(R.id.text_input_header);
        mInputTextView = findViewById(R.id.text_input_box);

        //header initilize
        initHeaders();

        //input initilize
        setInputText(mText);
        mInputTextView.setHint(mHint);
        if (mInputTextSize > 0) {
            mInputTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mInputTextSize);
        }

        mInputTextView.setTextColor(mInputColor);
        mInputTextView.setInputType(InputUtils.getInputType(mTextType));

        if (mInputPadding > 0) {
            mInputTextView.setPadding(mInputPadding, mInputPadding, mInputPadding, mInputPadding);
        }

        if (mInputBackgroundDrawable > 0) {
            mInputTextView.setBackground(
                    ResourcesCompat.getDrawable(getResources(), mInputBackgroundDrawable, null));
        }

    }

    @Override
    public void setHeaderText(String headerText) {
        mHeaderTextView.setText(headerText);
    }

    public void setInputText(String inputText) {
        mInputTextView.setText(inputText);
    }

    @Override
    public String getInputValue() {
        return mInputTextView.getText().toString();
    }
}
