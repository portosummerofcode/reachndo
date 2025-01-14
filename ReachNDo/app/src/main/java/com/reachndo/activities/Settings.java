package com.reachndo.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.reachndo.R;
import com.reachndo.fragments.SettingsFragment;
import com.service.LocationService;
import com.utilities.Theme;

public class Settings extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Theme.setThemeAccordingAPI(this);
        super.onCreate(savedInstanceState);
        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {

        Theme.setThemeAccordingAPI(this);
        super.onPostCreate(savedInstanceState);

        LinearLayout root = (LinearLayout) findViewById(android.R.id.list).getParent().getParent().getParent();
        Toolbar bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.settings_action_bar, root, false);
        bar.setTitleTextColor(getResources().getColor(R.color.White));
        root.addView(bar, 0); // insert at top

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Settings.this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("eventControl", LocationService.isEventControl());
        editor.commit();

        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Settings.this);
                LocationService.setEventControl(prefs.getBoolean("eventControl", false));

                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Settings.this);
        LocationService.setEventControl(prefs.getBoolean("eventControl", false));

        finish();
    }

}
