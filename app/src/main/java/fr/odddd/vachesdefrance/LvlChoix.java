package fr.odddd.vachesdefrance;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.odddd.vachesdefrance.domain.Lvl;
import fr.odddd.vachesdefrance.recyclerview.LvlAdapter;

public class LvlChoix extends AppCompatActivity {

    private Cursor l;
    private String[][] donnees;

    private boolean clikable;

    DataBaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvl_choix);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        clikable = true;

        List<Lvl> list_vaches = getListData();
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LvlAdapter lvlAdapter = new LvlAdapter(
                list_vaches,
                vache -> {
                    onItemClick(vache);
                    return null;
                });

        recyclerView.setAdapter(lvlAdapter);
    }

    private void onItemClick(Lvl selectedVache) {
        if (clikable) {
            clikable = false;
            // démarrer l'activité avec le bon niveau
        }
    }

    private List<Lvl> getListData() {

        db = new DataBaseHelper(this);
        List<Lvl> list = new ArrayList<Lvl>();

        //sqlite request
        l = db.getNb_lvl(); // nombre de niveaux
        int nb = l.getCount();
        for (int i = 1; i <= nb; i++) {
            list.add(new Lvl("" + getNbV(i), "" + i, getLock(i))); // la honte un peu
        }

        return list;
    }

    private Boolean getLock(int lvl) {


        return Boolean.FALSE;
    }

    private int getNbV(int lvl) {
        int n = 0;

        return n;
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

