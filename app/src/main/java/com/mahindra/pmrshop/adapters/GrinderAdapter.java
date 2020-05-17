package com.mahindra.pmrshop.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahindra.pmrshop.models.GrinderModel;
import com.mahindra.pmrshop.listeners.ItemClickedListener;
import com.mahindra.pmrshop.databinding.ItemRowBinding;

import java.util.List;


public class GrinderAdapter extends RecyclerView.Adapter<GrinderAdapter.GrinderViewHolder> {

    private List<GrinderModel> grinderList;
    private ItemClickedListener listener;

    public GrinderAdapter(List<GrinderModel> grinderList,ItemClickedListener listener) {
        this.grinderList = grinderList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GrinderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRowBinding itemBinding = ItemRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GrinderViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GrinderViewHolder holder, int position) {
        holder.binding.txvNote.setText(grinderList.get(position).getGrinderName());
        holder.binding.imageView2.setImageResource(grinderList.get(position).getGrinderImage());

        holder.binding.cardView.setOnClickListener(view -> listener.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return grinderList.size();
    }

    public class GrinderViewHolder extends RecyclerView.ViewHolder {

        private ItemRowBinding binding;

        public GrinderViewHolder(@NonNull ItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
