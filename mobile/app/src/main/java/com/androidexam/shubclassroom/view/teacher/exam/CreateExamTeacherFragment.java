package com.androidexam.shubclassroom.view.teacher.exam;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.FragmentTeacherCreateExamBinding;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.utilities.TimeTextWatcher;
import com.androidexam.shubclassroom.utilities.setDate;
import com.androidexam.shubclassroom.viewmodel.teacher.exam.CreateExamTeacherViewModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;


public class CreateExamTeacherFragment extends Fragment {
    private FragmentTeacherCreateExamBinding binding;
    private INavigation navigation;
    private CreateExamTeacherViewModel viewModel;
    private String classId;

    private String[] types = {"Bài tập", "Bài kiểm tra"};

    private ActivityResultLauncher<String> selectFileLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    BufferedReader reader = null;
                    try {
                        InputStream in = getContext().getContentResolver().openInputStream(uri);
                        reader = new BufferedReader(new InputStreamReader(in));
                        String line;
                        StringBuilder builder = new StringBuilder();
                        while ((line = reader.readLine()) != null){
                            builder.append(line);
                            builder.append("\n");
                        }
                        viewModel.setDetail(builder.toString(), uri.toString().substring(uri.toString().lastIndexOf("\\") + 1));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (reader != null) {
                            try {
                                reader.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
    );

    public CreateExamTeacherFragment(INavigation navigation, String classId) {
        this.navigation = navigation;
        this.classId = classId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_teacher_create_exam, container, false);

        binding.etType.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.list_item, types));
        viewModel = new CreateExamTeacherViewModel(getContext(), navigation, classId);

        viewModel.getBtnUploadClickObservable().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean value) {
                if (value) {
                    selectFileLauncher.launch("text/plain");
                }
            }
        });

        new setDate(binding.etDatestart, getContext());
        new setDate(binding.etDateend, getContext());

        binding.etTimestart.addTextChangedListener(new TimeTextWatcher(binding.etTimestart));
        binding.etTimeend.addTextChangedListener(new TimeTextWatcher(binding.etTimeend));

        binding.setCreateExamTeacherViewModel(viewModel);

        View viewRoot = binding.getRoot();

        return viewRoot;
    }
}