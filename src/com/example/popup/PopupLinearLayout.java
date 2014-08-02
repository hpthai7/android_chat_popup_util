package com.example.popup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.LinearLayout;

public class PopupLinearLayout extends LinearLayout {

    private final String TAG = PopupLinearLayout.class.getSimpleName();
    private static ChatPopup mBindedPopup;

    public PopupLinearLayout(Context context) {
        super(context);
    }

    public PopupLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("NewApi")
    public PopupLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        Log.d(TAG, "dispatchKeyEventPreIme(" + event + ")");
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            KeyEvent.DispatcherState state = getKeyDispatcherState();
            if (state != null) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                    state.startTracking(event, this);
                    return true;
                } else if (event.getAction() == KeyEvent.ACTION_UP && state.isTracking(event) && !event.isCanceled()) {
                    // Notify the popup that the window's behavior when pushing back button is overriden
                    mBindedPopup.interceptKeyEventPreIme();
                    return true;
                }
            }
        }
        return super.dispatchKeyEventPreIme(event);
    }

    public static void bindPopup(ChatPopup popup) {
        mBindedPopup = popup;
    }

}
