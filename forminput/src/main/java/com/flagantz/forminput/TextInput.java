package com.flagantz.forminput;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;


public class TextInput extends BaseForm {

    private static final int DEFAULT_NUM_OF_LINES = 3;
    private static final boolean DEFAULT_MULTI_LINES = false;

    EditText mInputTextView;

    String mText;
    String mHint;
    int mTextType;

    int mMinLines;
    int mMaxLines;
    boolean mMultiLines;

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

            mMultiLines = a.getBoolean(R.styleable.TextInput_bf_input_multi_lines,
                    DEFAULT_MULTI_LINES);
            mMaxLines = a.getInt(R.styleable.TextInput_bf_input_max_lines, DEFAULT_NUM_OF_LINES);
            mMinLines = a.getInt(R.styleable.TextInput_bf_input_min_lines, DEFAULT_NUM_OF_LINES);
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

        if (mMultiLines) {
            mInputTextView.setSingleLine(false);

            if (mMaxLines < mMinLines) {
                mMaxLines = mMinLines;
            }

            mInputTextView.setMaxLines(mMaxLines);
            mInputTextView.setMinLines(mMinLines);

            mInputTextView.setGravity(Gravity.TOP);
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

    public void clearText() {
        mInputTextView.getText().clear();
    }

    public void setFocus() {
        mInputTextView.requestFocus();
    }

    public void setTextWatcher(TextWatcher textWatcher) {
        mInputTextView.addTextChangedListener(textWatcher);
    }
}
