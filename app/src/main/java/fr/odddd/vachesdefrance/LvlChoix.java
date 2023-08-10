package fr.odddd.vachesdefrance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.odddd.vachesdefrance.domain.Lvl;
import fr.odddd.vachesdefrance.recyclerview.LvlAdapter;
import fr.odddd.vachesdefrance.utils.TitleUtils;

public class LvlChoix extends AppCompatActivity {

    private Cursor l;
    public String mode;
    private boolean clikable;
    private Intent otherActivity;
    DataBaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvl_choix);

        new TitleUtils().setTitle(
                getSupportActionBar(),
                R.string.title_app_bar_challenge_lvl_choice,
                true
        );

        Intent i = getIntent();
        mode = i.getStringExtra("mode");
        Log.d("mode", mode);

        clikable = true;

        List<Lvl> list_lvls = getListData();
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LvlAdapter lvlAdapter = new LvlAdapter(
                list_lvls,
                lvl -> {
                    onItemClick(lvl);
                    return null;
                });

        recyclerView.setAdapter(lvlAdapter);
    }

    private void onItemClick(Lvl selectedLvl) {
        if (clikable & !selectedLvl.getIsBlocked()) {
            clikable = false;

            if (Objects.equals(mode, "photo")){
                otherActivity = new Intent(getApplicationContext(), Challenge_image.class);
                Log.d("level", selectedLvl.getNiveau());
            }
            if (Objects.equals(mode, "carac")){
                otherActivity = new Intent(getApplicationContext(), Challenge.class);
                Log.d("level", selectedLvl.getNiveau());
            }

            otherActivity.putExtra("lvl", Integer.valueOf(selectedLvl.getNiveau()));
            startActivity(otherActivity);
            this.finish();
        }
    }

    private List<Lvl> getListData() {

        db = new DataBaseHelper(this);
        List<Lvl> list = new ArrayList<Lvl>();

        //sqlite request
        l = db.getNb_lvl(); // nombre de niveaux
        int nb = l.getCount();
        for (int i = 1; i <= nb; i++) {
            list.add(new Lvl("" + getNbV(i), "" + i, getLvlDrawable(i), getLock(i))); // la honte un peu
        }
        list.add(new Lvl(db.getAll().getCount() + "", "" + (nb+1), getLvlDrawable(nb+1), getLock(nb+1)));
        return list;
    }

    private Drawable getLvlDrawable(int lvl) {
        SharedPreferences score = null;
        if (Objects.equals(mode, "photo")){
            score = getSharedPreferences("Score_photo", 0); // recup des meilleurs score photo absolu
        }
        else if (Objects.equals(mode, "carac")) {
            score = getSharedPreferences("Score_carac", 0);
        }
        int s = score.getInt(String.valueOf(lvl), -1);
        int s_max = getNbV(lvl);

        if(s >= s_max){
            int resId = getResources().getIdentifier("border", "drawable", getPackageName());
            Drawable drawable  = getDrawable(resId);
            return drawable;
        } else if (lvl == 1) {
            int resId = getResources().getIdentifier("border_unl", "drawable", getPackageName());
            Drawable drawable  = getDrawable(resId);
            return drawable;
        } else if (score.getInt(String.valueOf(lvl-1), -1) >= getNbV(lvl-1)) {
            int resId = getResources().getIdentifier("border_unl", "drawable", getPackageName());
            Drawable drawable  = getDrawable(resId);
            return drawable;
        }

        int resId = getResources().getIdentifier("border_loc", "drawable", getPackageName());
        Drawable drawable  = getDrawable(resId);
        return drawable;
    }

    private Boolean getLock(int lvl) {
        SharedPreferences score = null;
        if (Objects.equals(mode, "photo")){
            score = getSharedPreferences("Score_photo", 0); // recup des meilleurs score photo absolu
        }
        else if (Objects.equals(mode, "carac")) {
            score = getSharedPreferences("Score_carac", 0);
        }
        int s = score.getInt(String.valueOf(lvl), -1);
        int s_max = getNbV(lvl);

        if(s >= s_max | lvl == 1 | score.getInt(String.valueOf(lvl-1), -1) >= getNbV(lvl-1)){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private int getNbV(int lvl) {
        int n = 0;
        db = new DataBaseHelper(this);
        n = db.getAll_lvl(lvl).getCount();
        return n;
    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

