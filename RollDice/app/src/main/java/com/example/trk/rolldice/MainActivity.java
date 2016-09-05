package com.example.trk.rolldice;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static int freq[];
    static boolean isRed = true;
    static int min = 1;
    static int max = 6;

    static Random rand;
    MediaPlayer background_music;
    MediaPlayer roll_sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton dicePicture = (ImageButton) findViewById(R.id.diceImage);
        String diceImageNumber = "near_dice";

        dicePicture.setImageDrawable(getResources().getDrawable(getResourceID(diceImageNumber, "drawable", getApplicationContext() )));
        rand = new Random();
        freq = new int[7];

        background_music = MediaPlayer.create(getApplicationContext(), R.raw.near_theme);
        background_music.start();
        roll_sound =  MediaPlayer.create(getApplicationContext(), R.raw.roll_dice_sound);

    }

    public void reset_counter(View view)
    {
        for(int c = 0; c <= 6; c++)
            freq[c] = 0;
        TextView freqs = (TextView) findViewById(R.id.frequencies);

        String output = "";

        for(int c = 1; c <= 6; c++)
        {
            output += "" + c + ": " + freq[c] + "\n";
        }
        freqs.setText(output.toCharArray(), 0, output.length() );
    }

    public void change_color(View view)
    {
        isRed = !isRed;
    }
    public void tap_dice(View view)
    {
        if(background_music.isPlaying())
            background_music.stop();

        roll_sound.start();

        int number = rand.nextInt(max - min + 1) + min;
        String diceImageNumber;
        freq[number]++;
        if(isRed)
        {
            diceImageNumber = "red_die_" + number;
        }
        else
        {
            diceImageNumber = "white_die_" + number;
        }

        ImageButton dicePicture = (ImageButton) findViewById(R.id.diceImage);
        dicePicture.setImageDrawable(getResources().getDrawable(getResourceID(diceImageNumber, "drawable", getApplicationContext() )));

        TextView freqs = (TextView) findViewById(R.id.frequencies);

        String output = "";

        for(int c = 1; c <= 6; c++)
        {
            output += "" + c + ": " + freq[c] + "\n";
        }
        freqs.setText(output.toCharArray(), 0, output.length() );

    }

    protected final static int getResourceID (final String resName, final String resType, final Context ctx)
    {
        final int ResourceID =
                ctx.getResources().getIdentifier(resName, resType,
                        ctx.getApplicationInfo().packageName);
        if (ResourceID == 0)
        {
            throw new IllegalArgumentException
                    (
                            "No resource string found with name " + resName
                    );
        }
        else
        {
            return ResourceID;
        }
    }
}
