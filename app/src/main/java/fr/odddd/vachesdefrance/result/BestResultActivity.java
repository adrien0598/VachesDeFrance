package fr.odddd.vachesdefrance.result;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fr.odddd.vachesdefrance.R;
import fr.odddd.vachesdefrance.ResultNavigationDirections;
import fr.odddd.vachesdefrance.utils.TitleUtils;

public class BestResultActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_result);

        new TitleUtils().setTitle(
                getSupportActionBar(),
                R.string.title_app_bar_scores,
                true
        );

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottom_bar);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.best_score:
                navigateToBestScore();
                return true;

            case R.id.best_time:
                navigateToBestTime();
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void navigateToBestScore() {
        NavController navController = getNavController();
        navController.navigate(
                ResultNavigationDirections.moveToBestScore()
        );
    }

    private void navigateToBestTime() {
        NavController navController = getNavController();
        navController.navigate(
                ResultNavigationDirections.moveToBestTime()
        );
    }

    private NavController getNavController() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        return navHostFragment.getNavController();
    }
}