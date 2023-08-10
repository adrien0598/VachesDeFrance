package fr.odddd.vachesdefrance.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import fr.odddd.vachesdefrance.databinding.ItemVacheAdapterBinding;
import fr.odddd.vachesdefrance.domain.VacheMieux;
import fr.odddd.vachesdefrance.recyclerview.base.BaseViewHolder;
import kotlin.jvm.functions.Function1;

public class VacheViewHolder extends BaseViewHolder<VacheMieux, ItemVacheAdapterBinding> {

    private final Function1<VacheMieux, Void> onVacheClicked;
    private final ItemVacheAdapterBinding binding;

    public VacheViewHolder(@NonNull ItemVacheAdapterBinding b, Function1<VacheMieux, Void> oVC) {
        super(b);
        binding = b;
        onVacheClicked = oVC;
    }

    @Override
    public void bind(VacheMieux vache) {
        binding.setVache(vache);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onVacheClicked.invoke(vache);
            }
        });
    }


    static public VacheViewHolder getObject(ViewGroup parent, Function1<VacheMieux, Void> oVC) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemVacheAdapterBinding b = ItemVacheAdapterBinding.inflate(layoutInflater, parent, false);
        return new VacheViewHolder(b, oVC);
    }
}
