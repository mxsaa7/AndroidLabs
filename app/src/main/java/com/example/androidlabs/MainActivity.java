package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final String SHARED_PREFS = "lab3";
    private static final String EMAIL_PREF_KEY = "email";

    private EditText emailText;
    private Button login;
    private SharedPreferences prefs;
    private String savedEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check the shared preferences for a saved email and display it in the email box, otherwise display an empty string.
        SharedPreferences pref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String savedEmail = pref.getString(EMAIL_PREF_KEY, "");
        ((EditText) findViewById(R.id.email)).setText(savedEmail);

        // When the login button is clicked, transition to the Profile activity.
        ((Button) findViewById(R.id.login)).setOnClickListener(clk -> {
            // Saving the email to the shared preferences while clicking the button wasn't required, but it's helpful.
            saveEmailToPreferences();
            startActivity(new Intent(this, ProfileActivity.class));
        });
    }

    private void saveEmailToPreferences() {
        SharedPreferences pref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String enteredEmail = ((EditText) findViewById(R.id.email)).getText().toString();
        pref.edit().putString(EMAIL_PREF_KEY, enteredEmail).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Ensure the contents of the email box are saved in the shared preferences
        saveEmailToPreferences();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}