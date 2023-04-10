package fr.odddd.vachesdefrance.recyclerview;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.odddd.vachesdefrance.domain.VacheMieux;
import kotlin.jvm.functions.Function1;

public class VacheAdapter extends RecyclerView.Adapter<VacheViewHolder> {

    private final List<VacheMieux> vaches;
    private final Function1<VacheMieux, Void> onVacheClicked;

    public VacheAdapter(List<VacheMieux> vaches, Function1<VacheMieux, Void> onVacheClicked) {
        this.vaches = vaches;
        this.onVacheClicked = onVacheClicked;
    }

    @NonNull
    @Override
    public VacheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return VacheViewHolder.getObject(parent, onVacheClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull VacheViewHolder holder, int position) {
        holder.bind(vaches.get(position));
    }

    @Override
    public int getItemCount() {
        return vaches.size();
    }
}
