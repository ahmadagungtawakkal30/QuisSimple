package com.example.quissimple.quis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quissimple.R;

import java.util.Random;

public class Quis2 extends AppCompatActivity {

    private int presCounter = 0;
    private int maxPresCounter = 4;
    private String[] keys = {"K", "I", "A", "D", "N"};
    private String textAnswer = "IKAN";
    TextView textPenyemangat, textPertanyaan, textKlik;
    Animation smallbigforth;
    int skor = 0;
    private static final int TIME_LIMIT = 2000;
    private static long backPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quis1);

        smallbigforth = AnimationUtils.loadAnimation(this, R.anim.smallbigforth);

        keys = shuffleArray(keys);

        for (String key : keys) {
            addView(((LinearLayout) findViewById(R.id.layoutParent)), key, ((EditText) findViewById(R.id.editText)));
        }

        maxPresCounter = 4;
    }


    private String[] shuffleArray(String[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }


    private void addView(LinearLayout viewParent, final String text, final EditText editText) {
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        linearLayoutParams.rightMargin = 30;

        final TextView textView = new TextView(this);

        textView.setLayoutParams(linearLayoutParams);
        textView.setBackground(this.getResources().getDrawable(R.drawable.bgtombol));
        textView.setTextColor(this.getResources().getColor(R.color.colorBiru));
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTextSize(32);

        Typeface stylejenishuruf = Typeface.createFromAsset(getAssets(), "fonts/FredokaOneRegular.ttf");

        textPenyemangat = (TextView) findViewById(R.id.textPenyemangat);
        textPertanyaan = (TextView) findViewById(R.id.textPertanyaan);
        textKlik = (TextView) findViewById(R.id.textKlik);

        textPertanyaan.setTypeface(stylejenishuruf);
        textPenyemangat.setTypeface(stylejenishuruf);
        textKlik.setTypeface(stylejenishuruf);
        editText.setTypeface(stylejenishuruf);
        textView.setTypeface(stylejenishuruf);

        textPertanyaan.setText("Hewan yang tidak bisa hidup di Darat?");
        textPenyemangat.setText("Ayo Jawab!");

        textView.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (presCounter < maxPresCounter) {
                    if (presCounter == 0)
                        editText.setText("");

                    editText.setText(editText.getText().toString() + text);
                    textView.startAnimation(smallbigforth);
                    textView.animate().alpha(0).setDuration(300);
                    presCounter++;

                    if (presCounter == maxPresCounter)
                        doValidate();
                }
            }
        });


        viewParent.addView(textView);


    }


    private void doValidate() {
        presCounter = 0;

        EditText editText = findViewById(R.id.editText);
        LinearLayout linearLayout = findViewById(R.id.layoutParent);

        skor = getIntent().getIntExtra("score",0);

        if (editText.getText().toString().equals(textAnswer)) {
            Toast.makeText(Quis2.this, "Benar!", Toast.LENGTH_SHORT).show();


            skor = skor + 20;
            Intent i = new Intent(Quis2.this, Quis3.class);
            i.putExtra("score",skor);
            startActivity(i);

            editText.setText("");
        } else {
            Toast.makeText(Quis2.this, "Salah!", Toast.LENGTH_SHORT).show();

            skor = skor - 5;
            Intent i = new Intent(Quis2.this,Quis3.class);
            i.putExtra("score",skor);
            startActivity(i);

        }

        keys = shuffleArray(keys);
        linearLayout.removeAllViews();
        for (String key : keys) {
            addView(linearLayout, key, editText);
        }

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