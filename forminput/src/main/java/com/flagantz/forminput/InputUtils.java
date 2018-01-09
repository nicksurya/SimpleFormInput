package com.flagantz.forminput;


import android.graphics.Typeface;

final class InputUtils {


    private static final int TYPE_TEXT = 0;
    private static final int TYPE_NUMBER = 1;
    private static final int TYPE_EMAIL = 2;
    private static final int TYPE_PASSWORD = 3;
    private static final int TYPE_DATE = 4;

    static int getInputType(int mInputType) {
        switch (mInputType) {
            case TYPE_NUMBER :
                return android.text.InputType.TYPE_CLASS_NUMBER;
            case TYPE_EMAIL :
                return android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
            case TYPE_DATE :
                return android.text.InputType.TYPE_DATETIME_VARIATION_DATE;
            case TYPE_PASSWORD :
                return android.text.InputType.TYPE_CLASS_TEXT
                        | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
            case TYPE_TEXT :
            default:
                return android.text.InputType.TYPE_CLASS_TEXT;
        }
    }


}
