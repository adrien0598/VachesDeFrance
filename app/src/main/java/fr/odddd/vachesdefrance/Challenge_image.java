package fr.odddd.vachesdefrance;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.odddd.vachesdefrance.utils.TitleUtils;

public class Challenge_image extends AppCompatActivity {

    private Cursor l;
    public String[][] donnees;
    DataBaseHelper db;
    private TextView score;
    private ImageView question;
    private Button c1;
    private Button c2;
    private Button c3;
    private Button c4;
    private Button home;
    public int good;
    public int nb_rep = 0;
    public int bonne_rep = 0;
    private int lvl;
    private TextView fin;
    private boolean clickable;
    private TextView timer;
    private boolean isRunning = false;
    private long startTime = 0;
    private Handler handler = new Handler();
    private long t0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_image);

        ActionBar actionBar = getSupportActionBar();

        new TitleUtils().setTitle(
                getSupportActionBar(),
                R.string.title_app_bar_challenge_photo,
                true
        );

        Intent i = getIntent();
        lvl = (int) i.getIntExtra("lvl",1);

        db = new DataBaseHelper(this);
        if (lvl < 7 ){
            l = db.getAll_lvl(lvl);
        }
        else {
            l = db.getAll();
        }


        if (l.getCount() == 0){
            Log.d("etat", "base vide");
        }
        else {
            donnees = CursorToListRd(l);
        }

        this.timer = (TextView)findViewById(R.id.timer);
        this.question = (ImageView)findViewById(R.id.question);
        this.c1 = (Button)findViewById(R.id.c1);
        this.c2 = (Button)findViewById(R.id.c2);
        this.c3 = (Button)findViewById(R.id.c3);
        this.c4 = (Button)findViewById(R.id.c4);
        this.home = (Button)findViewById(R.id.button_end);
        this.fin = (TextView)findViewById(R.id.fin);
        this.score = (TextView)findViewById(R.id.score_num);

        Animation false1 = AnimationUtils.loadAnimation(Challenge_image.this, R.anim.falseanim);

        Random rd = new Random();
        good = rd.nextInt(4)+1;
        eachTurn(nb_rep);

        int duration_f = 750;
        int duration_t = 450;
        clickable = true;

        if (!isRunning) {
            startTime = System.currentTimeMillis();
            handler.post(stopwatchRunnable);
            isRunning = true;
        }
        t0 = System.currentTimeMillis();

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickable){
                    clickable = false;
                    if(good == 1){
                        nb_rep ++;
                        bonne_rep ++;
                        good = rd.nextInt(4)+1;
                        c1.setBackgroundColor(getResources().getColor(R.color.good_answer));
                        c1.setTextColor(Color.WHITE);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable(){
                            public void run() {
                                eachTurn(nb_rep);
                            }
                        }, duration_t); //delay is here
                    }
                    else {
                        c1.startAnimation(false1);
                        c1.setBackgroundColor(getResources().getColor(R.color.bad_answer));
                        c1.setTextColor(Color.WHITE);
                        switch (good){
                            case 2 :
                                //c2.startAnimation(true1);
                                c2.setBackgroundColor(getResources().getColor(R.color.good_answer));
                                c2.setTextColor(Color.WHITE);
                                break;
                            case 3 :
                                //c3.startAnimation(true1);
                                c3.setBackgroundColor(getResources().getColor(R.color.good_answer));
                                c3.setTextColor(Color.WHITE);
                                break;
                            case 4 :
                                //c4.startAnimation(true1);
                                c4.setBackgroundColor(getResources().getColor(R.color.good_answer));
                                c4.setTextColor(Color.WHITE);
                                break;
                        }

                        nb_rep ++;
                        good = rd.nextInt(4)+1;

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable(){
                            public void run() {
                                eachTurn(nb_rep);
                            }
                        }, duration_f); //delay is here
                    }
                    clickable = true;
                }
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickable) {
                    clickable = false;
                    if(good == 2){
                        nb_rep ++;
                        bonne_rep ++;
                        good = rd.nextInt(4)+1;
                        c2.setBackgroundColor(getResources().getColor(R.color.good_answer));
                        c2.setTextColor(Color.WHITE);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable(){
                            public void run() {
                                eachTurn(nb_rep);
                            }
                        }, duration_t); //delay is here
                    }
                    else {
                        c2.startAnimation(false1);
                        c2.setBackgroundColor(getResources().getColor(R.color.bad_answer));
                        c2.setTextColor(Color.WHITE);
                        switch (good){
                            case 1 :
                                //c1.startAnimation(true1);
                                c1.setBackgroundColor(getResources().getColor(R.color.good_answer));
                                c1.setTextColor(Color.WHITE);
                                break;
                            case 3 :
                                //c3.startAnimation(true1);
                                c3.setBackgroundColor(getResources().getColor(R.color.good_answer));
                                c3.setTextColor(Color.WHITE);
                                break;
                            case 4 :
                                //c4.startAnimation(true1);
                                c4.setBackgroundColor(getResources().getColor(R.color.good_answer));
                                c4.setTextColor(Color.WHITE);
                                break;
                        }

                        nb_rep ++;
                        good = rd.nextInt(4)+1;
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable(){
                            public void run() {
                                eachTurn(nb_rep);
                            }
                        }, duration_f); //delay is here
                    }
                    clickable = true;
                }
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickable) {
                    clickable = false;
                    if(good == 3){
                        nb_rep ++;
                        bonne_rep ++;
                        good = rd.nextInt(4)+1;
                        c3.setBackgroundColor(getResources().getColor(R.color.good_answer));
                        c3.setTextColor(Color.WHITE);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable(){
                            public void run() {
                                eachTurn(nb_rep);
                            }
                        }, duration_t); //delay is here
                    }
                    else {
                        c3.startAnimation(false1);
                        c3.setBackgroundColor(getResources().getColor(R.color.bad_answer));
                        c3.setTextColor(Color.WHITE);
                        switch (good){
                            case 2 :
                                //c2.startAnimation(true1);
                                c2.setBackgroundColor(getResources().getColor(R.color.good_answer));
                                c2.setTextColor(Color.WHITE);
                                break;
                            case 1 :
                                //c1.startAnimation(true1);
                                c1.setBackgroundColor(getResources().getColor(R.color.good_answer));
                                c1.setTextColor(Color.WHITE);
                                break;
                            case 4 :
                                //c4.startAnimation(true1);
                                c4.setBackgroundColor(getResources().getColor(R.color.good_answer));
                                c4.setTextColor(Color.WHITE);
                                break;
                        }

                        nb_rep ++;
                        good = rd.nextInt(4)+1;
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable(){
                            public void run() {
                                eachTurn(nb_rep);
                            }
                        }, duration_f); //delay is here
                    }
                    clickable = true;
                }
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickable) {
                    clickable = false;
                    if(good == 4){
                        nb_rep ++;
                        bonne_rep ++;
                        good = rd.nextInt(4)+1;
                        c4.setBackgroundColor(getResources().getColor(R.color.good_answer));
                        c4.setTextColor(Color.WHITE);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable(){
                            public void run() {
                                eachTurn(nb_rep);
                            }
                        }, duration_t); //delay is here
                    }
                    else {
                        c4.startAnimation(false1);
                        c4.setBackgroundColor(getResources().getColor(R.color.bad_answer));
                        c4.setTextColor(Color.WHITE);
                        switch (good){
                            case 2 :
                                //c2.startAnimation(true1);
                                c2.setBackgroundColor(getResources().getColor(R.color.good_answer));
                                c2.setTextColor(Color.WHITE);
                                break;
                            case 3 :
                                //c3.startAnimation(true1);
                                c3.setBackgroundColor(getResources().getColor(R.color.good_answer));
                                c3.setTextColor(Color.WHITE);
                                break;
                            case 1 :
                                //c1.startAnimation(true1);
                                c1.setBackgroundColor(getResources().getColor(R.color.good_answer));
                                c1.setTextColor(Color.WHITE);
                                break;
                        }

                        nb_rep ++;
                        good = rd.nextInt(4)+1;
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable(){
                            public void run() {
                                eachTurn(nb_rep);
                            }
                        }, duration_f); //delay is here
                    }
                    clickable = true;
                }
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });

    }


    public void eachTurn(int nb_rep){

        score.setText(bonne_rep + "");
        if(nb_rep < donnees.length){

            String race = raceToId(donnees[nb_rep][0]);
            Log.d("voici", race);
            int id = getResources().getIdentifier(race, "drawable", getPackageName());
            question.setImageDrawable(getDrawable(id));

            String[] tmp = tirage(decoupe(donnees, 0, nb_rep), 3);switch (good){
                case 1 :
                    c1.setText(donnees[nb_rep][0]);
                    c2.setText(tmp[0]);
                    c3.setText(tmp[1]);
                    c4.setText(tmp[2]);
                    break;
                case 2 :
                    c2.setText(donnees[nb_rep][0]);
                    c1.setText(tmp[0]);
                    c3.setText(tmp[1]);
                    c4.setText(tmp[2]);
                    break;
                case 3 :
                    c3.setText(donnees[nb_rep][0]);
                    c1.setText(tmp[0]);
                    c2.setText(tmp[1]);
                    c4.setText(tmp[2]);
                    break;
                case 4 :
                    c4.setText(donnees[nb_rep][0]);
                    c2.setText(tmp[0]);
                    c3.setText(tmp[1]);
                    c1.setText(tmp[2]);
                    break;
                default:
                    Log.d("etat", "PROBLEME");
                    break;
            }
            c1.setBackgroundColor(Color.TRANSPARENT);
            c1.setTextColor(getResources().getColor(R.color.green1));
            c2.setBackgroundColor(Color.TRANSPARENT);
            c2.setTextColor(getResources().getColor(R.color.green1));
            c3.setBackgroundColor(Color.TRANSPARENT);
            c3.setTextColor(getResources().getColor(R.color.green1));
            c4.setBackgroundColor(Color.TRANSPARENT);
            c4.setTextColor(getResources().getColor(R.color.green1));
            Animation noanim = AnimationUtils.loadAnimation(Challenge_image.this, R.anim.emptyanim);
            c1.setAnimation(noanim);
            c2.setAnimation(noanim);
            c3.setAnimation(noanim);
            c4.setAnimation(noanim);
        }
        else {
            home.setVisibility(View.VISIBLE);
            fin.setVisibility(View.VISIBLE);
            if (isRunning) {
                handler.removeCallbacks(stopwatchRunnable);
                isRunning = false;
            }
            long t = System.currentTimeMillis() - t0;
            int tmp = (int) (t/60000);
            fin.setText("C'est la fin !" + "\n" + "Vous avez " + bonne_rep +
                    " réponses justes sur " + nb_rep + "\n" + "\n" + "Temps total : " +
                    tmp + "m " + (t/1000)%60 + "s " + t%1000 + "ms");

            SharedPreferences best_score = getSharedPreferences("Score_photo", 0); // recup des meilleurs score photo absolu
            if (best_score.getInt(String.valueOf(lvl), -1) < bonne_rep){
                SharedPreferences.Editor editor = best_score.edit();
                editor.putInt(String.valueOf(lvl), bonne_rep);
                editor.apply();
                Log.d("score", "nouveau record");
            }

            SharedPreferences best_time = getSharedPreferences("Time_photo", 0); // recup des meilleurs temps photo
            if (best_time.getLong(String.valueOf(lvl), -1) < t){
                SharedPreferences.Editor editor = best_time.edit();
                editor.putLong(String.valueOf(lvl), t);
                editor.apply();
                Log.d("score", "nouveau record");
            }

            //SharedPreferences score_rel = getSharedPreferences("Score_photo_rel", 0);
            //SharedPreferences.Editor editor = score_rel.edit();
            //int a = score_rel.getInt(String.valueOf((lvl*2)-1), 0);
            //int n = score_rel.getInt(String.valueOf(lvl*2), 0);
            //editor.putInt(String.valueOf((lvl*2)-1), a+bonne_rep);
            //editor.putInt(String.valueOf(lvl*2), n+1);
            //editor.apply();


            ConstraintLayout lay = findViewById(R.id.la);
            lay.removeView(question);
            lay.removeView(c1);
            lay.removeView(c2);
            lay.removeView(c3);
            lay.removeView(c4);

        }
    }

    public String[] decoupe(String[][] liste, int n, int except){
        String[] tmp = new String[liste.length - 1];
        int i = 0;
        while (i < liste.length) {
            if (i < except){
                tmp[i] = liste[i][n];
            }
            else if (i > except){
                tmp[i-1] = liste[i][n];
            }
            i++;
        }
        return tmp;
    }

    public String[][] CursorToListRd(Cursor curseur){
        String[][] liste_1;
        liste_1 = new String[curseur.getCount()][4];
        List<Integer> random = melange(curseur.getCount());
        for (int j = 0; j < curseur.getCount(); j++) {
            curseur.moveToPosition(random.get(j));
            liste_1[j][0] = curseur.getString(0);
            liste_1[j][1] = curseur.getString(1);
            liste_1[j][2] = curseur.getString(2);
            liste_1[j][3] = curseur.getString(3);}
        return liste_1;
    }


    public List<Integer> melange(int nb){
        List<Integer> tir = new ArrayList<Integer>();
        Random rd = new Random();

        Log.d("etat", "debut melange " + nb);
        tir.add(rd.nextInt(nb));
        int i = tir.get(0);
        Log.d("i0", String.valueOf(i));
        int cont = nb-1;
        int n = nb+1;

        while (cont > 0){
            while (contains(tir, i-1)){
                i = rd.nextInt(n);
            }
            Log.d("cont", String.valueOf(cont));
            Log.d("i-1", String.valueOf(i-1));
            tir.add(i - 1);
            cont -= 1;
        }
        //tir[0] = 0;
        Log.d("etat", "fin melange");
        return tir;
    }

    public String[] tirage(String[] pool, int nb){
        int[] tir = new int[pool.length];
        String[] tir_name = new String[pool.length];
        Random rd = new Random();
        int i = -1;

        while (nb > 0){
            while (contains2(tir, i)){
                i = rd.nextInt(pool.length);
            }
            tir[nb-1] = i;
            tir_name[nb-1] = pool[i];
            nb -= 1;
        }

        return tir_name;
    }

    private boolean contains(List<Integer> liste, int a) {
        if (a == -1){
            return true;
        }
        for (int i = 0; i < liste.size(); i ++){
            if (liste.get(i) == a)
                return true;}
        return false;
    }

    private boolean contains2(int[] liste, int a) {
        if (a == -1){
            return true;
        }
        for (int i = 0; i < liste.length; i ++){
            if (liste[i] == a)
                return true;}
        return false;
    }

    private String raceToId(String nom){
        String id = "";

        id = nom.replaceAll(" ", "_");
        id = id.replaceAll("é", "e");
        id = id.replaceAll("'", "");
        id = id.replaceAll("ç", "c");

        return id.toLowerCase();
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Runnable stopwatchRunnable = new Runnable() {
        @Override
        public void run() {
            long currentTime = System.currentTimeMillis() - startTime;
            int seconds = (int) (currentTime / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timer.setText(String.format("%02d:%02d", minutes, seconds));
            handler.postDelayed(this, 100);
        }
    };
}