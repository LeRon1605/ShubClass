package com.androidexam.shubclassroom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.model.student.StudentDTO;
import com.androidexam.shubclassroom.model.student.StudentIn4;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.ClassMemberItemViewModel;
import com.androidexam.shubclassroom.databinding.ItemStudentClassMemberBinding;

import java.util.ArrayList;
import java.util.List;

public class ClassMemberItemAdapter extends RecyclerView.Adapter<ClassMemberItemAdapter.ClassMemberItemViewHolder>{
    private List<StudentIn4> students;
    private LayoutInflater layoutInflater;

    public ClassMemberItemAdapter() {
        this.students = new ArrayList<>();
    }

    @NonNull
    @Override
    public ClassMemberItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        ItemStudentClassMemberBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_student_class_member, parent, false);
        return new ClassMemberItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassMemberItemAdapter.ClassMemberItemViewHolder holder, int position) {
        ClassMemberItemViewModel viewModel = new ClassMemberItemViewModel();
        viewModel.setStudentIn4(students.get(position));
        holder.binding.setClassMemberItemViewModel(viewModel);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void setStudents(List<StudentIn4> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    public class ClassMemberItemViewHolder extends RecyclerView.ViewHolder{
        private ItemStudentClassMemberBinding binding;

        public ClassMemberItemViewHolder(ItemStudentClassMemberBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}