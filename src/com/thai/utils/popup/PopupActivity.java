package com.thai.utils.popup;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class PopupActivity extends Activity {
    String TAG = PopupActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        Button btnPopup = (Button) findViewById(R.id.btn_pop);
        final Button btnFun = (Button) findViewById(R.id.btn_fun);

        btnPopup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatPopup popup = new ChatPopup(getApplicationContext());
                PopupLinearLayout.bindPopup(popup);
                popup.show(btnFun);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed");
    }
}