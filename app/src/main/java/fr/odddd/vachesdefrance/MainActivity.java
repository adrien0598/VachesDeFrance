package fr.odddd.vachesdefrance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        this.challenge = (Button)findViewById(R.id.challenge);
        this.train = (Button)findViewById(R.id.train);
        this.score = (Button)findViewById(R.id.score);
        this.quitter = (Button)findViewById(R.id.quitter);

        challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), Menu2.class);
                startActivity(otherActivity);
                //finish();
            }
        });

        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent otherActivity = new Intent(getApplicationContext(), Train_tab.class);
                Intent otherActivity = new Intent(getApplicationContext(), LvlChoix.class);
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
            public void onClick(View v) {
                System.exit(0);
            }
        });

    }
}