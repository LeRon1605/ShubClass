package com.androidexam.shubclassroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.ItemExamResultBinding;
import com.androidexam.shubclassroom.databinding.ItemTeacherExamBinding;
import com.androidexam.shubclassroom.model.exam.ExamDto;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.ClassItemViewModel;
import com.androidexam.shubclassroom.viewmodel.class_detail.teacher.ExamViewModel;
import com.androidexam.shubclassroom.viewmodel.teacher.exam.ExamTeacherItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExamTeacherItemAdapter extends RecyclerView.Adapter<ExamTeacherItemAdapter.ExamTeacherItemViewHolder>{
    private List<ExamDto> exams;
    private List<ExamDto> examCopy;
    private LayoutInflater layoutInflater;
    private INavigation navigation;
    private Context context;
    public ExamTeacherItemAdapter(INavigation navigation, Context context) {
        this.exams = new ArrayList<>();
        this.examCopy = new ArrayList<>();
        this.navigation = navigation;
        this.context = context;
    }

    public void setExams(List<ExamDto> exams) {
        this.exams = exams;
        this.examCopy = exams;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExamTeacherItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        ItemExamResultBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_exam_result, parent, false);
        return new ExamTeacherItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamTeacherItemViewHolder holder, int position) {
        ExamViewModel viewModel = new ExamViewModel(context, navigation);
        viewModel.setExamDto(exams.get(position));
        holder.binding.setViewModel(viewModel);
    }

    @Override
    public int getItemCount() {
        return exams.size();
    }

    public class ExamTeacherItemViewHolder extends RecyclerView.ViewHolder {
        private ItemExamResultBinding binding;
        public ExamTeacherItemViewHolder(ItemExamResultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public void filterList(String searchText) {
        List<ExamDto> filteredList = new ArrayList<>();
        if(searchText.isEmpty()) {
            filteredList = examCopy;
        }
        else {
            for (ExamDto item : exams) {
                if (item.getName().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        this.exams = filteredList;
        notifyDataSetChanged();
    }
}
