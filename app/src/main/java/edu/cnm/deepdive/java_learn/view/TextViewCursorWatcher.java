package edu.cnm.deepdive.java_learn.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.Log;
import edu.cnm.deepdive.java_learn.R;

public class TextViewCursorWatcher extends AppCompatEditText {

    public TextViewCursorWatcher(Context context) {
        this(context, null);
    }

    public TextViewCursorWatcher(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public TextViewCursorWatcher(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //Allow text to be selected but not edited
        setKeyListener(null);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        Log.v("SELECTION","Changed to "+selStart+" "+selEnd);
    }
}
