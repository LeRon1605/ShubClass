package com.androidexam.shubclassroom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.ClassItemBinding;
import com.androidexam.shubclassroom.view.student.BottomSheetClassStudentFragment;
import com.androidexam.shubclassroom.view.teacher.BottomSheetClassTeacherFragment;
import com.androidexam.shubclassroom.viewmodel.ClassItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<ClassItemViewModel> itemList = new ArrayList<>();
    private List<ClassItemViewModel> itemListCopy = new ArrayList<>();
    private int fragmentIndex;
    public ItemAdapter(List<ClassItemViewModel> classList, int fragmentIndex) {
        itemList = classList;
        itemListCopy = classList;
        this.fragmentIndex = fragmentIndex;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_item, parent, false);

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
            binding.btnMoreAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(fragmentIndex == 0) {
                        BottomSheetClassTeacherFragment dialog = new BottomSheetClassTeacherFragment(binding.getClassItem());
                        binding.getClassItem().setBottomSheetClassTeacherFragment(dialog);
                        dialog.show(((AppCompatActivity) v.getContext()).getSupportFragmentManager(), dialog.getTag());
                    }
                    else {
                        BottomSheetClassStudentFragment dialog = new BottomSheetClassStudentFragment(binding.getClassItem());
                        binding.getClassItem().setBottomSheetClassStudentFragment(dialog);
                        dialog.show(((AppCompatActivity) v.getContext()).getSupportFragmentManager(), dialog.getTag());
                    }
                }
            });
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
                if (item.getId().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }

        this.itemList = filteredList;
        notifyDataSetChanged();
    }
//    @BindingAdapter("items")
//    public static void setItems(RecyclerView recyclerView, List<ClassItemViewModel> items) {
//        ItemAdapter adapter = (ItemAdapter) recyclerView.getAdapter();
//        adapter.updateItemList(items);
//    }
}
