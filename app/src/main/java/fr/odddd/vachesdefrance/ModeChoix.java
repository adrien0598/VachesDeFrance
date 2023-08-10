package fr.odddd.vachesdefrance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import fr.odddd.vachesdefrance.utils.TitleUtils;

public class ModeChoix extends AppCompatActivity {

    private Button j1;
    private Button j2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_choix);

        new TitleUtils().setTitle(
                getSupportActionBar(),
                R.string.title_app_bar_challenge_mode_choice,
                true
        );

        this.j1 = (Button) findViewById(R.id.j1);
        this.j2 = (Button) findViewById(R.id.j2);

        j1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), LvlChoix.class);
                otherActivity.putExtra("mode", "carac");
                startActivity(otherActivity);
            }
        });

        j2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), LvlChoix.class);
                otherActivity.putExtra("mode", "photo");
                startActivity(otherActivity);
            }
        });
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}