package fr.odddd.vachesdefrance.result;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.List;

import fr.odddd.vachesdefrance.DataBaseHelper;
import fr.odddd.vachesdefrance.R;
import fr.odddd.vachesdefrance.databinding.FragmentBestScoreBinding;
import fr.odddd.vachesdefrance.utils.TitleUtils;

public class BestScoreFragment extends Fragment {

    private Integer[] score_photo;
    private Integer[] score_carac;
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
    private FragmentBestScoreBinding binding;
    public BestScoreFragment() {
        super(R.layout.fragment_best_score);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FragmentBestScoreBinding.inflate(getLayoutInflater());

        score_photo = new Integer[7];
        score_carac = new Integer[7];

        this.photo_1 = binding.photo1;
        this.photo_2 = binding.photo2;
        this.photo_3 = binding.photo3;
        this.photo_4 = binding.photo4;
        this.photo_5 = binding.photo5;
        this.photo_6 = binding.photo6;
        this.photo_7 = binding.photo7;
        this.carac_1 = binding.carac1;
        this.carac_2 = binding.carac2;
        this.carac_3 = binding.carac3;
        this.carac_4 = binding.carac4;
        this.carac_5 = binding.carac5;
        this.carac_6 = binding.carac6;
        this.carac_7 = binding.carac7;

        SharedPreferences score_p = requireActivity().getSharedPreferences("Score_photo", 0);
        SharedPreferences score_c = requireActivity().getSharedPreferences("Score_carac", 0);

        for (int i = 1; i < 8; i++) {
            score_photo[i - 1] = score_p.getInt(String.valueOf(i), -1);
            score_carac[i - 1] = score_c.getInt(String.valueOf(i), -1);
        }

        int color_good = Color.parseColor("#FF45B39D");
        int color_bad = Color.parseColor("#FF9c640c");

        //photo
        List<TextView> scores_p = Arrays.asList(photo_1, photo_2, photo_3, photo_4, photo_5, photo_6, photo_7);
        List<Integer> score_max = Arrays.asList(getNbV(1), getNbV(2), getNbV(3), getNbV(4), getNbV(5), getNbV(6), getNbV(7));

        for (int i = 0; i < 7; i++) {
            if (score_photo[i] == score_max.get(i)) {
                scores_p.get(i).setText("100");
                scores_p.get(i).setBackgroundColor(color_good);
                Log.d("etat", "en effet yen a un mais affiche pas");
            } else if (score_photo[i] >= 0) {
                scores_p.get(i).setText(String.valueOf(Math.round(score_photo[i] * 100 / score_max.get(i))));
                scores_p.get(i).setBackgroundColor(color_bad);
            }else {
                scores_p.get(i).setText("0");
            }
        }

        //carac
        List<TextView> scores_c = Arrays.asList(carac_1, carac_2, carac_3, carac_4, carac_5, carac_6, carac_7);

        for (int i = 0; i < 7; i++) {
            if (score_carac[i] == score_max.get(i)) {
                scores_c.get(i).setText("100");
                scores_c.get(i).setBackgroundColor(color_good);
            } else if (score_carac[i] >= 0) {
                scores_c.get(i).setText(String.valueOf(Math.round(score_carac[i] * 100 / score_max.get(i))));
                scores_c.get(i).setBackgroundColor(color_bad);
            }else {
                scores_p.get(i).setText("0");
            }
        }
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                requireActivity().finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private int getNbV(int lvl) {
        int n = 0;
        db = new DataBaseHelper(requireContext());
        n = db.getAll_lvl(lvl).getCount();
        return n;
    }

    private String timeWrite(Long t) {
        String p;
        if (t >= 1000 * 60) {
            int tmp = (int) (t / 60000);
            p = tmp + "m" + (t / 1000) % 60 + "s" + t % 1000 + "ms";
        } else {
            p = (t / 1000) % 60 + "s" + t % 1000 + "ms";
        }
        return p;
    }
}