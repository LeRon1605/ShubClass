package com.androidexam.shubclassroom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.model.student.StudentDTO;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.ClassMemberItemViewModel;
import com.androidexam.shubclassroom.databinding.ItemStudentClassMemberBinding;

import java.util.ArrayList;

public class ClassMemberItemAdapter extends RecyclerView.Adapter<ClassMemberItemAdapter.ClassMemberItemViewHolder>{
    private ArrayList<StudentDTO> students;
    private LayoutInflater layoutInflater;
    private INavigation navigation;

    public ClassMemberItemAdapter(INavigation navigation) {
        this.students = new ArrayList<StudentDTO>();
        this.navigation = navigation;
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
        viewModel.setStudentDTO(students.get(position));
        holder.binding.setClassMemberItemViewModel(viewModel);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void setStudents(ArrayList<StudentDTO> students) {
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