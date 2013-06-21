package com.fsck.glassairplane;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.provider.Settings;
import android.view.View;
import android.widget.ImageButton;

public class ToggleAirplaneMode extends Activity {

        ImageButton toggleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(android.R.style.Theme_Black_NoTitleBar_Fullscreen);

        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_fullscreen);
        final View contentView = findViewById(R.id.fullscreen_content);
        toggleButton = (ImageButton) findViewById(R.id.toggle);

        if (isEnabled()) {

            toggleButton.setImageDrawable(getResources().getDrawable(R.drawable.airplane_mode_on));
        } else{
            toggleButton.setImageDrawable(getResources().getDrawable(R.drawable.wifi_on_big));


        }


        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isEnabled()) {

                    toggleButton.setImageDrawable(getResources().getDrawable(R.drawable.airplane_mode_on));
                } else{
                    toggleButton.setImageDrawable(getResources().getDrawable(R.drawable.wifi_on_big));


                }


                toggleAirplaneMode(isEnabled());




            }
        });

        // Set up the user interaction to manually show or hide the system UI.
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleButton.callOnClick();
            }

        });




    }


    void toggleAirplaneMode(boolean isEnabled) {
        // toggle airplane mode
        Settings.System.putInt(
                getContentResolver(),
                Settings.System.AIRPLANE_MODE_ON, isEnabled ? 0 : 1);

        Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intent.putExtra("state", !isEnabled);
        sendBroadcast(intent);



    }

    // read the airplane mode setting
    boolean isEnabled() {
        if (Settings.System.getInt( getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) == 1) {
            return true;
        } else {
            return false;
        }


    }


}
