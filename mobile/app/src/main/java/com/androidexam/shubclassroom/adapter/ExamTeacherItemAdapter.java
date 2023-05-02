package com.androidexam.shubclassroom.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.ItemTeacherExamBinding;
import com.androidexam.shubclassroom.model.exam.ExamDto;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.teacher.exam.ExamTeacherItemViewModel;

import java.util.ArrayList;

public class ExamTeacherItemAdapter extends RecyclerView.Adapter<ExamTeacherItemAdapter.ExamTeacherItemViewHolder>{
    private ArrayList<ExamDto> exams;
    private LayoutInflater layoutInflater;
    private INavigation navigation;

    public ExamTeacherItemAdapter(INavigation navigation) {
        this.exams = new ArrayList<ExamDto>();
        this.navigation = navigation;
    }

    public void setExams(ArrayList<ExamDto> exams) {
        this.exams = exams;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExamTeacherItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        ItemTeacherExamBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_teacher_exam, parent, false);
        return new ExamTeacherItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamTeacherItemViewHolder holder, int position) {
        ExamTeacherItemViewModel viewModel = new ExamTeacherItemViewModel();
        viewModel.setExamDto(exams.get(position));
        holder.binding.setExamTeacherItemViewModel(viewModel);
    }

    @Override
    public int getItemCount() {
        return exams.size();
    }

    public class ExamTeacherItemViewHolder extends RecyclerView.ViewHolder {
        private ItemTeacherExamBinding binding;
        public ExamTeacherItemViewHolder(ItemTeacherExamBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
