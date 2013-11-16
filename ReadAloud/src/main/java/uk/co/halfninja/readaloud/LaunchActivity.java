package uk.co.halfninja.readaloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class LaunchActivity extends RoboActivity {

    @InjectView(R.id.editText) EditText editText;
    @InjectView(R.id.button)   Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean b) {
                boolean hasText = !editText.getText().toString().trim().isEmpty();
                button.setEnabled(hasText);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.launch, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void pressSpeakButton(View view) {
        String string = editText.getText().toString();
        Intent intent = new Intent(this, ReadActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, string);
        startActivity(intent);
    }

    public void openTtsSettings(MenuItem item) {
        Intent intent = new Intent();
        intent.setAction("com.android.settings.TTS_SETTINGS");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }
}