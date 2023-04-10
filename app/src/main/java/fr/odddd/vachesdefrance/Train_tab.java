package fr.odddd.vachesdefrance;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Collections;
import java.util.List;

import fr.odddd.vachesdefrance.domain.VacheMieux;
import fr.odddd.vachesdefrance.recyclerview.VacheAdapter;
import fr.odddd.vachesdefrance.utils.BitmapUtils;


public class Train_tab extends AppCompatActivity {

    private Cursor l;
    private String[][] donnees;

    private Button retour;
    private CardView card;
    private ImageView photo;
    private TextView race;
    private TextView type;
    private TextView region;

    private boolean clikable;

    DataBaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_tab);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.card = (CardView) findViewById(R.id.card);
        this.retour = (Button) findViewById(R.id.Retour);
        this.photo = (ImageView) findViewById(R.id.Image);
        this.race = (TextView) findViewById(R.id.Race);
        this.type = (TextView) findViewById(R.id.Type_des);
        this.region = (TextView) findViewById(R.id.Region_des);
        clikable = true;

        retour.setOnClickListener(v -> {
            clikable = true;
            card.setVisibility(View.GONE);
        });

        List<Vache> list_vaches = getListData();
        ArrayList<VacheMieux> list_vaches_with_bitmap = new ArrayList<>();
        for (int j = 0; j < list_vaches.size(); j++) {
            Vache vache = list_vaches.get(j);
            int resId = getResources().getIdentifier(vache.getPhoto(), "drawable", getPackageName());
            Drawable drawable  = getDrawable(resId);
            //TODO
            int resIdCompressed = getResources().getIdentifier(vache.getPhoto() + "_small", "drawable", getPackageName());
            Drawable drawableCompressed  = getDrawable(resIdCompressed);
            list_vaches_with_bitmap.add(new VacheMieux(vache.getRace(), drawable, drawableCompressed,vache.getNiveau() + ""));
        }
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        VacheAdapter vacheAdapter = new VacheAdapter(
                list_vaches_with_bitmap,
                vache -> {
                    onItemClick(vache);
                    return null;
                });

        recyclerView.setAdapter(vacheAdapter);
    }

    private void onItemClick(VacheMieux selectedVache) {
        if (clikable) {
            clikable = false;
            card.setVisibility(View.VISIBLE);
            String rac = selectedVache.getRace();
            type.setText(getInfoType(rac));
            region.setText(getInfoRegion(rac));
            race.setText(rac);
            photo.setImageDrawable(selectedVache.getPhoto());
        }
    }

    private List<Vache> getListData() {

        db = new DataBaseHelper(this);
        List<Vache> list = new ArrayList<Vache>();

        //sqlite request
        l = db.getAll_lvl(1);
        for (int j = 0; j < l.getCount(); j++) {
            l.moveToPosition(j);
            list.add(new Vache(l.getString(0), raceToId(l.getString(0)), 1));
        }
        l = db.getAll_lvl(2);
        for (int j = 0; j < l.getCount(); j++) {
            l.moveToPosition(j);
            list.add(new Vache(l.getString(0), raceToId(l.getString(0)), 2));
        }
        l = db.getAll_lvl(3);
        for (int j = 0; j < l.getCount(); j++) {
            l.moveToPosition(j);
            list.add(new Vache(l.getString(0), raceToId(l.getString(0)), 3));
        }

        return list;
    }


    private String raceToId(String nom) {
        String id = "";

        id = nom.replaceAll(" ", "_");
        id = id.replaceAll("é", "e");
        id = id.replaceAll("'", "");
        id = id.replaceAll("ç", "c");

        return id.toLowerCase();
    }

    private String getInfoType(String race) {
        db = new DataBaseHelper(this);

        l = db.getType(race);
        l.moveToPosition(0);
        return l.getString(0);
    }

    private String getInfoRegion(String race) {
        db = new DataBaseHelper(this);

        l = db.getRegion(race);
        l.moveToPosition(0);
        return l.getString(0);
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