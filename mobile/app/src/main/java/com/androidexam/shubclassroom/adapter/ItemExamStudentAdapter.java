package com.androidexam.shubclassroom.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.ItemStudentExamBinding;
import com.androidexam.shubclassroom.model.exam.ExamDto;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.view.student.exam.DoExamStudentActivity;
import com.androidexam.shubclassroom.viewmodel.student.exam.ExamStudentItemViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ItemExamStudentAdapter extends RecyclerView.Adapter<ItemExamStudentAdapter.ViewHolder> implements Filterable {

    private ArrayList<ExamDto> examDtos;
    private ArrayList<ExamDto> examDtosCopy;
    private Inflater inflater;
    private INavigation navigation;

    public ItemExamStudentAdapter(INavigation navigation) {
        this.examDtos = new ArrayList<ExamDto>();
        this.navigation = navigation;
    }

    public void setExams(ArrayList<ExamDto> examDtos)
    {
        this.examDtos = examDtos;
        this.examDtosCopy = examDtos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStudentExamBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_student_exam, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemExamStudentAdapter.ViewHolder holder, int position) {
        ExamStudentItemViewModel viewModel = new ExamStudentItemViewModel();
        viewModel.setExamDto(examDtos.get(position));
        holder.binding.setExamStudentItemViewModel(viewModel);
    }

    @Override
    public int getItemCount() {
        return examDtos.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String input = constraint.toString().toLowerCase();
                List<ExamDto> filteredExams = new ArrayList<>();
                if (input.isEmpty())
                {
                    filteredExams.addAll(examDtosCopy);
                }
                else
                {
                    for (ExamDto exam : examDtosCopy)
                    {
                        if(exam.getName().toLowerCase().contains(input))
                        {
                            filteredExams.add(exam);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredExams;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                examDtos = new ArrayList<>();
                examDtos.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public ItemStudentExamBinding binding;

        public ViewHolder(ItemStudentExamBinding itemStudentExamBinding) {
            super(itemStudentExamBinding.getRoot());

            this.binding = itemStudentExamBinding;

            binding.btnDoexam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExamDto exam = examDtos.get(getAdapterPosition());
                    if (exam.getIsDone() == false) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("examDto", exam);
                        Intent intent = new Intent(v.getContext(), DoExamStudentActivity.class);
                        intent.putExtra("doexam", bundle);
                        v.getContext().startActivity(intent);
                    } else {
                        Toast.makeText(v.getContext(), "Bài tập đã hoàn thành", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

    }
}
