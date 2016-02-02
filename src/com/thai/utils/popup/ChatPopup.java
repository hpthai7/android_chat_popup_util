package com.thai.utils.popup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.PopupWindow;

/**
 * <p>A popup window that can be used to display an arbitrary view. The popup
 * window is a floating container that appears on top of the current
 * activity.</p>
 *
 * <p>This custom popup window aims at catching key events, i.e. back press to
 * manipulate popup and soft-keyboard's behavior in case a soft keyboard is
 * displaying.
 *
 * @see android.widget.AutoCompleteTextView
 * @see android.widget.Spinner
 */
public class ChatPopup extends PopupWindow {

    private final Context mContext;
    private final View mContentView;
    private final View mPopupView;

    @SuppressLint("InflateParams")
    public ChatPopup(Context context) {
        super(context);
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContentView = inflater.inflate(R.layout.popup, null);

        setContentView(mContentView);
        setHeight(LayoutParams.WRAP_CONTENT);
        setWidth(LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setTouchable(true);

        // Get touching focus for content view to receive key event
        // setFocusable only avail content view of being clickable
        mPopupView = getContentView();
        mPopupView.setFocusableInTouchMode(true);

        Button close = (Button) mPopupView.findViewById(R.id.close);
        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * Hide soft-keyboard and close the popup
     */
    public void interceptKeyEventPreIme() {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(mPopupView.getWindowToken(), 0);
        }
        dismiss();
    }

    /**
     * Show popup at particular view
     */
    public void show(View anchor) {
        int[] anchorLocation = new int[2];
        anchor.getLocationOnScreen(anchorLocation);
        int offsetX = -20;
        int offsetY = -20;
        showAtLocation(anchor, Gravity.NO_GRAVITY, anchorLocation[0] + offsetX, anchorLocation[1] + offsetY);
    }
}