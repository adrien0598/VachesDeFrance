package fr.odddd.vachesdefrance.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import fr.odddd.vachesdefrance.databinding.ItemLvlAdapterBinding;
import fr.odddd.vachesdefrance.domain.Lvl;
import fr.odddd.vachesdefrance.recyclerview.base.BaseViewHolder;
import kotlin.jvm.functions.Function1;


public class LvlViewHolder extends BaseViewHolder<Lvl, ItemLvlAdapterBinding> {
    private final Function1<Lvl, Void> onLvlClicked;
    private final ItemLvlAdapterBinding binding;

    public LvlViewHolder(@NonNull ItemLvlAdapterBinding b, Function1<Lvl, Void> oVC) {
        super(b);
        binding = b;
        onLvlClicked = oVC;
    }

    @Override
    public void bind(Lvl lvl) {
        binding.setLvl(lvl);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLvlClicked.invoke(lvl);
            }
        });
    }


    static public LvlViewHolder getObject(ViewGroup parent, Function1<Lvl, Void> oVC) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemLvlAdapterBinding b = ItemLvlAdapterBinding.inflate(layoutInflater, parent, false);
        return new LvlViewHolder(b, oVC);
    }
}
