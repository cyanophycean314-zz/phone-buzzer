package com.example.kevin.buzzersystem;

import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Bundle;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuzzerActivity extends AppCompatActivity {
    List<ImageView> buttons = new ArrayList<ImageView>();
    boolean lockout = false;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzzer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        buttons = Arrays.asList((ImageView) findViewById(R.id.button1), (ImageView) findViewById((R.id.button2)), (ImageView) findViewById(R.id.button3), (ImageView) findViewById(R.id.button4));

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.buzzer);
    }

    public void pushed(View view) {
        if (!lockout) {
            ImageView imageview = (ImageView) view;
            imageview.setImageResource(R.drawable.pressed);
            lockout = true;
            mp.start();
        }
    }

    public void clear(View view) {
        for (ImageView imageview : buttons) {
            imageview.setImageResource(R.drawable.unpressed);
        }
        lockout = false;
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
