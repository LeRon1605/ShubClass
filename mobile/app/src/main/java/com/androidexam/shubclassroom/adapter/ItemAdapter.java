package com.androidexam.shubclassroom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.androidexam.shubclassroom.model.ClassCreateDto;
import com.androidexam.shubclassroom.shared.FragmentIndex;
import com.androidexam.shubclassroom.view.class_detail.ClassDetailActivity;
import com.androidexam.shubclassroom.view.student.BottomSheetClassStudentFragment;
import com.androidexam.shubclassroom.view.teacher.BottomSheetClassTeacherFragment;
import com.androidexam.shubclassroom.viewmodel.ClassItemViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<ClassItemViewModel> itemList = new ArrayList<>();
    private List<ClassItemViewModel> itemListCopy = new ArrayList<>();
    private Context context;
    private int fragmentIndex;
    public ItemAdapter(List<ClassItemViewModel> classList, int fragmentIndex, Context context) {
        itemList = classList;
        itemListCopy = classList;
        this.fragmentIndex = fragmentIndex;
        this.context = context;
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
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.setClassItem(itemList.get(position));
        holder.binding.coverImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragmentIndex == FragmentIndex.Teacher.getValue()) {
                    Bundle bundle = new Bundle();
                    ClassCreateDto classCreateDto = new ClassCreateDto(
                            itemList.get(position).getClassModel().getId(),
                            itemList.get(position).getClassModel().getName(),
                            itemList.get(position).getClassModel().getDescription(),
                            itemList.get(position).getClassModel().getSubjectName(),
                            itemList.get(position).getClassModel().getNumberOfStudent()
                    );
                    bundle.putSerializable("Class", (Serializable) classCreateDto);
                    bundle.putInt("FragmentIndex", FragmentIndex.Teacher.getValue());
                    Intent intent = new Intent(context, ClassDetailActivity.class);
                    intent.putExtra("myBundle", bundle);
                    context.startActivity(intent, bundle);
                }
            }
        });
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
                    if(fragmentIndex == FragmentIndex.Teacher.getValue()) {
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
                if (item.getClassModel().getId().toLowerCase().contains(searchText.toLowerCase())) {
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

