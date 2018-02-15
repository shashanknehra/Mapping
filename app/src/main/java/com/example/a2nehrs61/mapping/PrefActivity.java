package com.example.a2nehrs61.mapping;

/**
 * Created by 2nehrs61 on 15/02/2018.
 */
import android.preference.PreferenceActivity;
import android.os.Bundle;

public class PrefActivity extends PreferenceActivity
{
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}

