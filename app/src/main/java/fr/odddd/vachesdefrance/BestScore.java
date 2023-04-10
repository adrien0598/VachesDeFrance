package fr.odddd.vachesdefrance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class BestScore extends AppCompatActivity {

    private Integer[] score_photo;
    private Integer[] score_carac;
    private Integer[] score_photo_rel;
    private Integer[] score_carac_rel;
    private TextView photo_1;
    private TextView photo_2;
    private TextView photo_3;
    private TextView photo_4;
    private TextView carac_1;
    private TextView carac_2;
    private TextView carac_3;
    private TextView carac_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_score);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        score_photo = new Integer[4];
        score_carac = new Integer[4];
        score_carac_rel = new Integer[8];
        score_photo_rel = new Integer[8];

        this.photo_1 = (TextView)findViewById(R.id.photo_1);
        this.photo_2 = (TextView)findViewById(R.id.photo_2);
        this.photo_3 = (TextView)findViewById(R.id.photo_3);
        this.photo_4 = (TextView)findViewById(R.id.photo_4);
        this.carac_1 = (TextView)findViewById(R.id.carac_1);
        this.carac_2 = (TextView)findViewById(R.id.carac_2);
        this.carac_3 = (TextView)findViewById(R.id.carac_3);
        this.carac_4 = (TextView)findViewById(R.id.carac_4);

        SharedPreferences score_p = getSharedPreferences("Score_photo", 0);
        SharedPreferences score_c = getSharedPreferences("Score_carac", 0);
        SharedPreferences score_p_rel = getSharedPreferences("Score_photo_rel", 0);
        SharedPreferences score_c_rel = getSharedPreferences("Score_carac_rel", 0);

        for (int i = 1; i < 5; i++) {
            score_photo[i-1] = score_p.getInt(String.valueOf(i), -1);
            score_carac[i-1] = score_c.getInt(String.valueOf(i), -1);
        }
        for (int i = 1; i < 9; i++) {
            score_photo_rel[i-1] = score_p_rel.getInt(String.valueOf(i), -1);
            score_carac_rel[i-1] = score_c_rel.getInt(String.valueOf(i), -1);
        }

        int color_good = Color.parseColor("#FF45B39D");
        int color_bad = Color.parseColor("#FF9c640c");

        //photo
        if (score_photo[0] == 12){
            photo_1.setText("100");
            photo_1.setBackgroundColor(color_good);
        }
        else if (score_photo[0] >= 0){
            photo_1.setText(String.valueOf(Math.round(score_photo[0]*100/12)));
            photo_1.setBackgroundColor(color_bad);
        }

        if (score_photo[1] == 12){
            photo_2.setText("100");
            photo_2.setBackgroundColor(color_good);
        }
        else if (score_photo[1] >= 0){
            photo_2.setText(String.valueOf(Math.round(score_photo[1]*100/12)));
            photo_2.setBackgroundColor(color_bad);
        }

        if (score_photo[2] == 13){
            photo_3.setText("100");
            photo_3.setBackgroundColor(color_good);
        }
        else if (score_photo[2] >= 0){
            photo_3.setText(String.valueOf(Math.round(score_photo[2]*100/13)));
            photo_3.setBackgroundColor(color_bad);
        }

        if (score_photo[3] == 37){
            photo_4.setText("100");
            photo_4.setBackgroundColor(color_good);
        }
        else if (score_photo[3] >= 0){
            photo_4.setText(String.valueOf(Math.round(score_photo[3]*100/37)));
            photo_4.setBackgroundColor(color_bad);
        }

        //carac
        if (score_carac[0] == 12){
            carac_1.setText("100");
            carac_1.setBackgroundColor(color_good);
        }
        else if (score_carac[0] >= 0){
            carac_1.setText(String.valueOf(Math.round(score_carac[0]*100/12)));
            carac_1.setBackgroundColor(color_bad);
        }

        if (score_carac[1] == 12){
            carac_2.setText("100");
            carac_2.setBackgroundColor(color_good);
        }
        else if (score_carac[1] >= 0){
            carac_2.setText(String.valueOf(Math.round(score_carac[1]*100/12)));
            carac_2.setBackgroundColor(color_bad);
        }

        if (score_carac[2] == 13){
            carac_3.setText("100");
            carac_3.setBackgroundColor(color_good);
        }
        else if (score_carac[2] >= 0){
            carac_3.setText(String.valueOf(Math.round(score_carac[2]*100/13)));
            carac_3.setBackgroundColor(color_bad);
        }

        if (score_carac[3] == 37){
            carac_4.setText("100");
            carac_4.setBackgroundColor(color_good);
        }
        else if (score_carac[3] >= 0){
            carac_4.setText(String.valueOf(Math.round(score_carac[3]*100/37)));
            carac_4.setBackgroundColor(color_bad);
        }
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}