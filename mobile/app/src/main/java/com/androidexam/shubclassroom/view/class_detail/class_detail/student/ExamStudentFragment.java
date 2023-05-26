package com.androidexam.shubclassroom.view.class_detail.class_detail.student;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.adapter.ItemExamStudentAdapter;
import com.androidexam.shubclassroom.api.ClassApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.databinding.FragmentStudentExamBinding;
import com.androidexam.shubclassroom.model.exam.ExamDto;
import com.androidexam.shubclassroom.shared.ClassDetailFragment;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.student.exam.ExamStudentViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamStudentFragment extends Fragment {

    private INavigation navigation;
    private FragmentStudentExamBinding binding;
    private ExamStudentViewModel viewModel;

    private String classId;

    public ExamStudentFragment(INavigation navigation, String classId)
    {
        this.navigation = navigation;
        this.classId = classId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_student_exam, container, false);
        viewModel = new ExamStudentViewModel(getContext(), navigation , classId);

        binding.setExamStudentViewModel(viewModel);

        binding.rvExam.setAdapter(viewModel.getAdapter());
        binding.rvExam.setLayoutManager(new LinearLayoutManager(getContext()));


        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewModel.getAdapter().getFilter().filter(newText);
                return false;
            }
        });
    }
}