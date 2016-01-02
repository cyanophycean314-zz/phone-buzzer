package com.example.kevin.buzzersystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuzzerActivity extends AppCompatActivity {
    List<View> buttons = new ArrayList<View>();
    List<View> titles = new ArrayList<>();
    boolean lockout = false;
    MediaPlayer mp;
    boolean soundon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzzer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        buttons = Arrays.asList(findViewById(R.id.button1), findViewById((R.id.button2)), findViewById(R.id.button3), findViewById(R.id.button4));
        titles = Arrays.asList(findViewById(R.id.player1title), findViewById(R.id.player2title), findViewById(R.id.player3title), findViewById(R.id.player4title));

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.buzzer);

        onResume();
    }

    public void pushed(View view) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        if (!lockout) {
            ImageView imageview = (ImageView) view;
            imageview.setImageResource(R.drawable.pressed);
            lockout = true;
            if (soundon) {
                mp.start();
            }
        }
    }

    public void clear(View view) {
        for (View iview : buttons) {
            ImageView imageview = (ImageView) iview;
            imageview.setImageResource(R.drawable.unpressed);
        }
        lockout = false;
    }

    @Override
    public void onResume() {
        super.onResume();

        //Load settings
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        soundon = sharedPref.getBoolean("sound",true);

        for (int i = 0; i < titles.size(); i++) {
            TextView title = (TextView) titles.get(i);
            title.setText(sharedPref.getString("p" + (i + 1) + "name","Dank bro " + (i + 1)));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buzzer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
