package com.androidexam.shubclassroom.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.ItemStudentClassFindingBinding;
import com.androidexam.shubclassroom.viewmodel.ClassItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class ItemSearchAdapter extends RecyclerView.Adapter<ItemSearchAdapter.ItemViewHolder>{
    private List<ClassItemViewModel> itemList = new ArrayList<>();
    private List<ClassItemViewModel> itemListCopy = new ArrayList<>();
    public ItemSearchAdapter(List<ClassItemViewModel> classList) {
        itemList = classList;
        itemListCopy = classList;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemStudentClassFindingBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_student_class_finding,
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

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemStudentClassFindingBinding binding;
        public ItemViewHolder(ItemStudentClassFindingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ClassItemViewModel item) {
            binding.setClassItem(item);
            binding.executePendingBindings();
        }
    }
    public void filterList(String searchText) {
        List<ClassItemViewModel> filteredList = new ArrayList<>();
        if(searchText.isEmpty()) {
            filteredList = itemListCopy;
        }
        else {
            for (ClassItemViewModel item : itemList) {
                if (item.getClassModel().getId().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }

        this.itemList = filteredList;
        notifyDataSetChanged();
    }
}
