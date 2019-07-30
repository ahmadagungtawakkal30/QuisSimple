package com.example.quissimple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CekPoin extends AppCompatActivity {
    TextView textAwal, textStart, textTema;
    ImageView gbUtama;
    Animation smalltobig;
    int skor;
    private static final int TIME_LIMIT = 2000;
    private static long backPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_poin);

        smalltobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);

        Typeface stylejenishuruf = Typeface.createFromAsset(getAssets(), "fonts/FredokaOneRegular.ttf");

        textTema = (TextView) findViewById(R.id.textTema);
        textAwal = (TextView) findViewById(R.id.textAwal);
        textStart = (TextView) findViewById(R.id.textStart);

        gbUtama = (ImageView) findViewById(R.id.bgUtama);
        gbUtama.startAnimation(smalltobig);

        textTema.setTypeface(stylejenishuruf);
        textStart.setTypeface(stylejenishuruf);
        textAwal.setTypeface(stylejenishuruf);

        skor = getIntent().getIntExtra("score",0);
        textAwal.setText("Score : " + skor);

        textStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(CekPoin.this,MainActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed(){
        if(TIME_LIMIT + backPressed > System.currentTimeMillis()){
            Intent i = new Intent(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
            System.exit(0);
        }else {
            Toast.makeText(this,"Tekan kembali untuk keluar :(.", Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();

    }

}
