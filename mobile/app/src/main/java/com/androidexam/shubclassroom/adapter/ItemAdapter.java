package com.androidexam.shubclassroom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.ClassItemBinding;
import com.androidexam.shubclassroom.model.Class;
import com.androidexam.shubclassroom.viewmodel.ClassItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<ClassItemViewModel> itemList = new ArrayList<>();
    public ItemAdapter(List<ClassItemViewModel> classList) {
        itemList = classList;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_item, parent, false);

        ClassItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.class_item,
                parent,
                false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.binding.setClassItem(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void updateItemList(List<ClassItemViewModel> newItemList) {
        itemList.clear();
        itemList.addAll(newItemList);
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ClassItemBinding binding;
        public ItemViewHolder(ClassItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ClassItemViewModel item) {
            binding.setClassItem(item);
            binding.executePendingBindings();
        }
    }

//    @BindingAdapter("items")
//    public static void setItems(RecyclerView recyclerView, List<ClassItemViewModel> items) {
//        ItemAdapter adapter = (ItemAdapter) recyclerView.getAdapter();
//        adapter.updateItemList(items);
//    }
}

