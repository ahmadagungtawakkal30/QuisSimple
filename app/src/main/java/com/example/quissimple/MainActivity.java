package com.example.quissimple;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quissimple.quis.Quis1;

public class MainActivity extends AppCompatActivity {

    public static MainActivity ma;
    TextView textAwal, textStart;
    ImageView gbUtama;
    Animation smalltobig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smalltobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);

        Typeface stylejenishuruf = Typeface.createFromAsset(getAssets(), "fonts/FredokaOneRegular.ttf");

        textAwal = (TextView) findViewById(R.id.textAwal);
        textStart = (TextView) findViewById(R.id.textStart);

        gbUtama = (ImageView) findViewById(R.id.bgUtama);
        gbUtama.startAnimation(smalltobig);

        textStart.setTypeface(stylejenishuruf);
        textAwal.setTypeface(stylejenishuruf);

        textStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, Quis1.class);
                startActivity(i);
            }
        });

    }

    public void RefreshList() {
    }
}
