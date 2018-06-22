package com.flagantz.forminput;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DatePickerInput extends BaseForm implements DatePickerDialog.OnDateSetListener {

    public static final int TYPE_DATE = 0;
    public static final int TYPE_TIME = 1;
    public static final int TYPE_DATETIME = 2;

    TextView mDateInputView;

    String mHint;
    int mDateTimeType;
    String mDateFormat;

    public DatePickerInput(@NonNull Context context) {
        this(context, null);
    }

    public DatePickerInput(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DatePickerInput);
            mHint = a.getString(R.styleable.DatePickerInput_bf_date_time_hint);
            mDateTimeType = a.getInt(R.styleable.DatePickerInput_bf_date_time_type, TYPE_DATE);
            mDateFormat = a.getString(R.styleable.DatePickerInput_bf_date_time_format);
            a.recycle();
        }

        inflate(context, R.layout.form_date_input_view, this);

        init();
    }

    @Override
    public void init() {
        mHeaderTextView = findViewById(R.id.text_input_header);
        mDateInputView = findViewById(R.id.date_input_box);

        //header initilize
        initHeaders();

        //input initilize
        mDateInputView.setText(mHint);

        mDateInputView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
    }

    @Override
    public void setHeaderText(String headerText) {
        mHeaderTextView.setText(headerText);
    }

    @Override
    public String getInputValue() {
        String string = mDateInputView.getText().toString();
        return string.equalsIgnoreCase(mHint) ? null : string;
    }

    private void showDatePicker() {
        Calendar myCalendar = Calendar.getInstance();

        new DatePickerDialog(getContext(), this,
                myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mDateInputView.setText(String.format("%1$d - %2$d - %3$d", dayOfMonth, month, year));
    }
}
