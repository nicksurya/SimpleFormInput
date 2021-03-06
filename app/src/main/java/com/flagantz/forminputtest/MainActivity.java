package com.flagantz.forminputtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.flagantz.forminput.SpinnerInput;
import com.flagantz.forminput.TextInput;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpinnerInput spinnerInput = (SpinnerInput) findViewById(R.id.spinner_input);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Satu");
        arrayList.add("Dua");
        arrayList.add("Tiga");
        arrayList.add("Empat");
        arrayList.add("Lima");
        arrayList.add("Enam");

        spinnerInput.setSpinnerItemList(arrayList);

        TextInput textInput = (TextInput) findViewById(R.id.name_text_input);
        textInput.setFocusListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

    }
}
