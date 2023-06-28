package fr.odddd.vachesdefrance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Menu2 extends AppCompatActivity {

    private String mode;

    private Button j1;
    private Button j2;
    private SeekBar seekBar;
    private TextView lvl;

    int progress = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        this.j1 = (Button)findViewById(R.id.j1);
        this.j2 = (Button)findViewById(R.id.j2);
        this.seekBar = (SeekBar)findViewById(R.id.seekBar);
        this.lvl = (TextView)findViewById(R.id.level);

        seekBar.setMax(4);
        seekBar.setProgress(1);


        j1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), Challenge.class);
                Log.d("level", String.valueOf(progress));
                otherActivity.putExtra("lvl", progress);
                startActivity(otherActivity);
            }
        });

        j2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), Challenge_image.class);
                otherActivity.putExtra("lvl", progress);
                Log.d("level", String.valueOf(progress));
                startActivity(otherActivity);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            // When Progress value changed.
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
                lvl.setText("Niveau sélectionné : " + progressValue);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    // this event will enable the back
    // function to the button on press
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}