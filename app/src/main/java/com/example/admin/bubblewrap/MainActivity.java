package com.example.admin.bubblewrap;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    Button button;
    ArrayList<Integer> clicked;
    SoundPool soundPool;
    int[] offs;
    int[] sounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        offs = new int[]{R.drawable.off1, R.drawable.off2, R.drawable.off3};
        clicked = new ArrayList();
        gridView = (GridView) findViewById(R.id.gridView);
        button = (Button) findViewById(R.id.button);
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        sounds = new int[]{soundPool.load(getApplicationContext(), R.raw.clack1, 1),
                soundPool.load(getApplicationContext(), R.raw.clack2, 1),
                soundPool.load(getApplicationContext(), R.raw.clack3, 1),
                soundPool.load(getApplicationContext(), R.raw.clack4, 1),
                soundPool.load(getApplicationContext(), R.raw.clack5, 1),
                soundPool.load(getApplicationContext(), R.raw.clack6, 1)
        };
        gridView.setAdapter(new GridAdapter(this));
        gridView.setNumColumns(6);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!clicked.contains(position)) {
                    int s = (int) (Math.random() * 6);
                    soundPool.play(sounds[s], 1, 1, 1, 0, 1);
                    clicked.add(position);
                    int o = (int) (Math.random() * 3);
                    ImageView bubble = (ImageView) parent.getChildAt(position).findViewById(R.id.bubble);
                    bubble.setImageDrawable(getResources().getDrawable(offs[o]));
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridView.setAdapter(new GridAdapter(MainActivity.this));
                clicked.clear();
            }
        });
    }
}