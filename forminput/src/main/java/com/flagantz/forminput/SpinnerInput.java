package com.flagantz.forminput;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class SpinnerInput extends BaseForm {

    Spinner mInputSpinnerView;

    Listener listener;

    public SpinnerInput(@NonNull Context context) {
        this(context, null);
    }

    public SpinnerInput(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.form_spinner_input_view, this);

        init();
    }

    @Override
    public void init() {
        mHeaderTextView = findViewById(R.id.text_input_header);
        LinearLayout mSpinnerContainer = findViewById(R.id.spinner_container);
        mInputSpinnerView = findViewById(R.id.spinner_input_box);
        ImageView mDropDown = findViewById(R.id.spinner_drop_down_image);

        mDropDown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mInputSpinnerView.performClick();
            }
        });

        //header initilize
        initHeaders();

        //input initilize
        if (mInputPadding > 0) {
            mInputSpinnerView.setPadding(mInputPadding, mInputPadding, mInputPadding,
                    mInputPadding);
        }

        if (mInputBackgroundDrawable > 0) {
            mSpinnerContainer.setBackground(
                    ResourcesCompat.getDrawable(getResources(), mInputBackgroundDrawable, null));
        }

    }

    @Override
    public void setHeaderText(String headerText) {
        mHeaderTextView.setText(headerText);
    }

    @Override
    public String getInputValue() {
        if (mInputSpinnerView.getSelectedItem() == null) {
            return "";
        }
        return mInputSpinnerView.getSelectedItem().toString();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setSpinnerItemList(final ArrayList<String> itemList) {
        ArrayAdapter<String> mAdapter =
                new ArrayAdapter<>(getContext(), R.layout.default_spinner_item, itemList);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mInputSpinnerView.setAdapter(mAdapter);

        if (listener != null) {
            mInputSpinnerView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position,
                                           long l) {
                    String selectedItem = itemList.get(position);
                    listener.onInputChange(selectedItem);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {}
            });
        }
    }

    public void setSpinnerItem(String item) {
        for (int i = 0; i < mInputSpinnerView.getAdapter().getCount(); i++) {
            String spinnerItem = (String) mInputSpinnerView.getAdapter().getItem(i);
            if (item.equalsIgnoreCase(spinnerItem)) {
                mInputSpinnerView.setSelection(i);
                break;
            }
        }
    }

    public interface Listener {
        void onInputChange(String newValue);
    }

}
