package com.webmyne.customdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnGift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        actionListener();
    }

    private void init() {
        btnGift = (Button) findViewById(R.id.btnGift);
    }

    private void actionListener() {
        btnGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GiftDialog giftDialog = new GiftDialog(MainActivity.this);
                giftDialog.setCancelable(false);
                giftDialog.show();
            }
        });
    }
}
