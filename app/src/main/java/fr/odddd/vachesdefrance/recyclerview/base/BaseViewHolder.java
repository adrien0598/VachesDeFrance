package fr.odddd.vachesdefrance.recyclerview.base;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<T, B extends ViewDataBinding> extends RecyclerView.ViewHolder{

    private B binding;

    public BaseViewHolder(B binding) {
        super(binding.getRoot());
    }

    public void bind(T item) {}
}
