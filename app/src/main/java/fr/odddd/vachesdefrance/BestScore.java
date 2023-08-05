package fr.odddd.vachesdefrance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.odddd.vachesdefrance.utils.TitleUtils;

public class BestScore extends AppCompatActivity {

    private Integer[] score_photo;
    private Integer[] score_carac;
    private Long[] temps_carac;
    private Long[] temps_photo;
    //private Integer[] score_photo_rel;
    //private Integer[] score_carac_rel;
    private TextView photo_1;
    private TextView photo_2;
    private TextView photo_3;
    private TextView photo_4;
    private TextView photo_5;
    private TextView photo_6;
    private TextView photo_7;
    private TextView carac_1;
    private TextView carac_2;
    private TextView carac_3;
    private TextView carac_4;
    private TextView carac_5;
    private TextView carac_6;
    private TextView carac_7;
    DataBaseHelper db;
    private Button changement;
    private boolean temps;
    private TextView texte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_score);

        new TitleUtils().setTitle(
                getSupportActionBar(),
                R.string.title_app_bar_scores,
                true
        );

        temps = false;
        score_photo = new Integer[7];
        score_carac = new Integer[7];
        temps_photo = new Long[7];
        temps_carac = new Long[7];
        //score_carac_rel = new Integer[8];
        //score_photo_rel = new Integer[8];

        this.texte = findViewById(R.id.explication);
        this.changement = (Button)findViewById(R.id.changement);
        this.photo_1 = (TextView)findViewById(R.id.photo_1);
        this.photo_2 = (TextView)findViewById(R.id.photo_2);
        this.photo_3 = (TextView)findViewById(R.id.photo_3);
        this.photo_4 = (TextView)findViewById(R.id.photo_4);
        this.photo_5 = (TextView)findViewById(R.id.photo_5);
        this.photo_6 = (TextView)findViewById(R.id.photo_6);
        this.photo_7 = (TextView)findViewById(R.id.photo_7);
        this.carac_1 = (TextView)findViewById(R.id.carac_1);
        this.carac_2 = (TextView)findViewById(R.id.carac_2);
        this.carac_3 = (TextView)findViewById(R.id.carac_3);
        this.carac_4 = (TextView)findViewById(R.id.carac_4);
        this.carac_5 = (TextView)findViewById(R.id.carac_5);
        this.carac_6 = (TextView)findViewById(R.id.carac_6);
        this.carac_7 = (TextView)findViewById(R.id.carac_7);

        SharedPreferences score_p = getSharedPreferences("Score_photo", 0);
        SharedPreferences score_c = getSharedPreferences("Score_carac", 0);
        SharedPreferences temps_p = getSharedPreferences("Time_photo", 0);
        SharedPreferences temps_c = getSharedPreferences("Time_carac", 0);
        //SharedPreferences score_p_rel = getSharedPreferences("Score_photo_rel", 0);
        //SharedPreferences score_c_rel = getSharedPreferences("Score_carac_rel", 0);

        for (int i = 1; i < 8; i++) {
            score_photo[i-1] = score_p.getInt(String.valueOf(i), -1);
            score_carac[i-1] = score_c.getInt(String.valueOf(i), -1);
        }

        for (int i = 1; i < 8; i++) {
            temps_photo[i-1] = temps_p.getLong(String.valueOf(i), -1);
            temps_carac[i-1] = temps_c.getLong(String.valueOf(i), -1);
        }
        //for (int i = 1; i < 8; i++) {
        //    score_photo_rel[i-1] = score_p_rel.getInt(String.valueOf(i), -1);
        //    score_carac_rel[i-1] = score_c_rel.getInt(String.valueOf(i), -1);
        //}

        int color_good = Color.parseColor("#FF45B39D");
        int color_bad = Color.parseColor("#FF9c640c");

        //photo

        List<TextView> scores_p = Arrays.asList(photo_1, photo_2, photo_3, photo_4, photo_5, photo_6, photo_7);
        List<Integer> score_max = Arrays.asList(getNbV(1), getNbV(2), getNbV(3), getNbV(4), getNbV(5), getNbV(6), getNbV(7));

        for (int i = 0; i < 7; i++) {
            if (score_photo[i] == score_max.get(i)){
                scores_p.get(i).setText("100");
                scores_p.get(i).setBackgroundColor(color_good);
            }
            else if (score_photo[i] >= 0){
                scores_p.get(i).setText(String.valueOf(Math.round(score_photo[i]*100/ score_max.get(i))));
                scores_p.get(i).setBackgroundColor(color_bad);
            }
        }

        //carac

        List<TextView> scores_c = Arrays.asList(carac_1, carac_2, carac_3, carac_4, carac_5, carac_6, carac_7);

        for (int i = 0; i < 7; i++) {
            if (score_carac[i] == score_max.get(i)){
                scores_c.get(i).setText("100");
                scores_c.get(i).setBackgroundColor(color_good);
            }
            else if (score_carac[i] >= 0){
                scores_c.get(i).setText(String.valueOf(Math.round(score_carac[i]*100/ score_max.get(i))));
                scores_c.get(i).setBackgroundColor(color_bad);
            }
        }

        changement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!temps){
                    changement.setText(getResources().getString(R.string.switch_to_pr));
                    texte.setText(getResources().getString(R.string.mt_bestscore));

                    for (int i = 0; i < 7; i++) {
                        if (score_photo[i] == score_max.get(i)){
                            scores_p.get(i).setText(timeWrite(temps_photo[i]));
                            scores_p.get(i).setBackgroundColor(color_good);
                        }
                        else {
                            scores_p.get(i).setText("");
                        }
                    }

                    for (int i = 0; i < 7; i++) {
                        if (score_carac[i] == score_max.get(i)){
                            scores_c.get(i).setText(timeWrite(temps_carac[i]));
                            scores_c.get(i).setBackgroundColor(color_good);
                        }
                        else {
                            scores_c.get(i).setText("");
                        }
                    }
                    temps = true;
                }else{
                    changement.setText(getResources().getString(R.string.switch_to_mt));
                    texte.setText(getResources().getString(R.string.pr_bestscore));

                    for (int i = 0; i < 7; i++) {
                        if (score_photo[i] == score_max.get(i)){
                            scores_p.get(i).setText("100");
                            scores_p.get(i).setBackgroundColor(color_good);
                        }
                        else if (score_photo[i] >= 0){
                            scores_p.get(i).setText(String.valueOf(Math.round(score_photo[i]*100/ score_max.get(i))));
                            scores_p.get(i).setBackgroundColor(color_bad);
                        }
                    }

                    for (int i = 0; i < 7; i++) {
                        if (score_carac[i] == score_max.get(i)){
                            scores_c.get(i).setText("100");
                            scores_c.get(i).setBackgroundColor(color_good);
                        }
                        else if (score_carac[i] >= 0){
                            scores_c.get(i).setText(String.valueOf(Math.round(score_carac[i]*100/ score_max.get(i))));
                            scores_c.get(i).setBackgroundColor(color_bad);
                        }
                    }
                    temps = false;
                }
            }
        });
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private int getNbV(int lvl) {
        int n = 0;
        db = new DataBaseHelper(this);
        n = db.getAll_lvl(lvl).getCount();
        return n;
    }

    private String timeWrite(Long t){
        String p;
        if (t >= 1000*60){
            int tmp = (int) (t/60000);
            p = tmp + "m" + (t/1000)%60 + "s" + t%1000 + "ms";
        }
        else {
            p = (t/1000)%60 + "s" + t%1000 + "ms";
        }
        return p;
    }
}