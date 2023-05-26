package com.androidexam.shubclassroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.ItemExamResultBinding;
import com.androidexam.shubclassroom.databinding.ItemExamResultsStudentBinding;
import com.androidexam.shubclassroom.model.exam_result.ExamResult;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.class_detail.teacher.ExamResultsViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExamResultItemAdapter extends RecyclerView.Adapter<ExamResultItemAdapter.ViewHolder>{
    private List<ExamResult> examResultList;
    private LayoutInflater layoutInflater;
    private Context context;
    private INavigation navigation;
    public  ExamResultItemAdapter(Context context, INavigation navigation) {
        this.context = context;
        this.navigation = navigation;
        this.examResultList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        ItemExamResultsStudentBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_exam_results_student, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExamResultsViewModel viewModel = new ExamResultsViewModel(context, navigation, examResultList.get(position));
        holder.binding.setViewModel(viewModel);
    }

    @Override
    public int getItemCount() {
        return examResultList.size();
    }

    public void setExamResultList(List<ExamResult> examResultList) {
        this.examResultList = examResultList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemExamResultsStudentBinding binding;

        public ViewHolder(ItemExamResultsStudentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
