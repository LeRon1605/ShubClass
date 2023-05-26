package com.androidexam.shubclassroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.StudentRequestItemBinding;
import com.androidexam.shubclassroom.viewmodel.class_detail.teacher.RequestViewModel;

import java.util.List;

public class StudentRequestItemAdapter extends RecyclerView.Adapter<StudentRequestItemAdapter.ItemViewHolder>{
    private List<RequestViewModel> viewModelList;
    private Context context;

    public StudentRequestItemAdapter(List<RequestViewModel> viewModelList, Context context) {
        this.viewModelList = viewModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StudentRequestItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.student_request_item,
                parent,
                false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.binding.setViewModel(viewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return viewModelList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public StudentRequestItemBinding binding;
        public ItemViewHolder(StudentRequestItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(RequestViewModel viewModel) {
            binding.setViewModel(viewModel);
            binding.executePendingBindings();
        }
    }
}
