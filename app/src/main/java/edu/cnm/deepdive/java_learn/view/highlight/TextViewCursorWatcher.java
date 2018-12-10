package edu.cnm.deepdive.java_learn.view.highlight;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.Log;
import edu.cnm.deepdive.java_learn.R;

/**
 * The type Text view cursor watcher.
 */
public class TextViewCursorWatcher extends AppCompatEditText {

    /**
     * Instantiates a new Text view cursor watcher.
     *
     * @param context the context
     */
    public TextViewCursorWatcher(Context context) {
        this(context, null);
    }

    /**
     * Instantiates a new Text view cursor watcher.
     *
     * @param context the context
     * @param attrs the attrs
     */
    public TextViewCursorWatcher(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    /**
     * Instantiates a new Text view cursor watcher.
     *
     * @param context the context
     * @param attrs the attrs
     * @param defStyleAttr the def style attr
     */
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
