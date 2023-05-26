package com.androidexam.shubclassroom.view.student.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.ActivityStudentDoExamBinding;
import com.androidexam.shubclassroom.model.exam.ExamDto;
import com.androidexam.shubclassroom.viewmodel.student.exam.DoExamStudentViewModel;

public class DoExamStudentActivity extends AppCompatActivity {

    private ActivityStudentDoExamBinding binding;
    private DoExamStudentViewModel viewModel;
    private ExamDto examDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityStudentDoExamBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);

        Bundle bundle = getIntent().getBundleExtra("doexam");
        if (bundle != null) {
            examDto = (ExamDto) bundle.getSerializable("examDto");

            viewModel = new DoExamStudentViewModel(getApplicationContext(), examDto);
            binding.setDoExamStudentViewModel(viewModel);


            viewModel.getQuestion().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    binding.tvQuestion.setText(s);
                }
            });

            viewModel.getIndex().observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    binding.tvIndex.setText(String.valueOf(integer + 1));
                }
            });

            viewModel.getAnsA().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    binding.btnA.setText(s);
                }
            });
            viewModel.getAnsB().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    binding.btnB.setText(s);
                }
            });
            viewModel.getAnsC().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    binding.btnC.setText(s);
                }
            });
            viewModel.getAnsD().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    binding.btnD.setText(s);
                }
            });

            viewModel.getChoice().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    switch (s) {
                        case "A":
                            binding.btnA.setBackgroundColor(getResources().getColor(R.color.deep_blue));
                            binding.btnB.setBackgroundColor(getResources().getColor(R.color.blue_200));
                            binding.btnC.setBackgroundColor(getResources().getColor(R.color.blue_200));
                            binding.btnD.setBackgroundColor(getResources().getColor(R.color.blue_200));
                            break;
                        case "B":
                            binding.btnB.setBackgroundColor(getResources().getColor(R.color.deep_blue));
                            binding.btnA.setBackgroundColor(getResources().getColor(R.color.blue_200));
                            binding.btnC.setBackgroundColor(getResources().getColor(R.color.blue_200));
                            binding.btnD.setBackgroundColor(getResources().getColor(R.color.blue_200));
                            break;
                        case "C":
                            binding.btnC.setBackgroundColor(getResources().getColor(R.color.deep_blue));
                            binding.btnA.setBackgroundColor(getResources().getColor(R.color.blue_200));
                            binding.btnB.setBackgroundColor(getResources().getColor(R.color.blue_200));
                            binding.btnD.setBackgroundColor(getResources().getColor(R.color.blue_200));
                            break;
                        case "D":
                            binding.btnD.setBackgroundColor(getResources().getColor(R.color.deep_blue));
                            binding.btnA.setBackgroundColor(getResources().getColor(R.color.blue_200));
                            binding.btnB.setBackgroundColor(getResources().getColor(R.color.blue_200));
                            binding.btnC.setBackgroundColor(getResources().getColor(R.color.blue_200));
                            break;
                    }
                }
            });
        }

        setContentView(binding.getRoot());
    }
}