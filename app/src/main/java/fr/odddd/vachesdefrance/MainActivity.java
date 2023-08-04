package fr.odddd.vachesdefrance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.database.Cursor;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView plage;
    private Cursor l;
    public String[][] donnees;

    private Button challenge;
    private Button train;

    private Integer[] version;
    private Button score;
    private Button quitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseHelper db = new DataBaseHelper(MainActivity.this);
        try {
            db.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // version de l'appli (database, découpage lvl etc)
        int v = 1;
        SharedPreferences version = getSharedPreferences("version_app", 0);
        if (version.getInt("version", -1) < v){
            //reset best score
            SharedPreferences settings = getSharedPreferences("Score_photo", 0); // recup des meilleurs score photo absolu
            SharedPreferences.Editor editor = settings.edit();
            for (int i = 1; i < 7; i++) {
                editor.putInt(String.valueOf(i), -1);
            }
            editor.apply();

            SharedPreferences settings2 = getSharedPreferences("Score_carac", 0); // recup des meilleurs score photo absolu
            SharedPreferences.Editor editor3 = settings2.edit();
            for (int i = 1; i < 7; i++) {
                editor3.putInt(String.valueOf(i), -1);
            }
            editor3.apply();

            SharedPreferences.Editor editor2 = version.edit();
            editor2.putInt("version", v);
            editor2.apply();
        }

        this.challenge = (Button)findViewById(R.id.challenge);
        this.train = (Button)findViewById(R.id.train);
        this.score = (Button)findViewById(R.id.score);
        this.quitter = (Button)findViewById(R.id.quitter);

        challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent otherActivity = new Intent(getApplicationContext(), Menu2.class);
                Intent otherActivity = new Intent(getApplicationContext(), ModeChoix.class);
                startActivity(otherActivity);
                //finish();
            }
        });

        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), Train_tab.class);
                startActivity(otherActivity);
                //finish();
            }
        });

        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), BestScore.class);
                startActivity(otherActivity);
            }
        });

        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            //public void onClick(View v) {System.exit(0);}
            public void onClick(View v) {
            finish();
        }
        });

    }
}