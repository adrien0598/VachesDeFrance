package fr.odddd.vachesdefrance.recyclerview;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.odddd.vachesdefrance.domain.Lvl;
import kotlin.jvm.functions.Function1;

public class LvlAdapter extends RecyclerView.Adapter<LvlViewHolder> {

    private final List<Lvl> lvl;
    private final Function1<Lvl, Void> onLvlClicked;

    public LvlAdapter(List<Lvl> lvl, Function1<Lvl, Void> onLvlClicked) {
        this.lvl = lvl;
        this.onLvlClicked = onLvlClicked;
    }

    @NonNull
    @Override
    public LvlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return LvlViewHolder.getObject(parent, onLvlClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull LvlViewHolder holder, int position) {
        holder.bind(lvl.get(position));
    }

    @Override
    public int getItemCount() {
        return lvl.size();
    }
}
