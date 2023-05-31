package com.androidexam.shubclassroom.view.class_detail.class_detail.student;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.adapter.ClassMemberItemAdapter;
import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.ClassApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.databinding.FragmentShowStudentInClassStudentBinding;
import com.androidexam.shubclassroom.model.ClassCreateDto;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.student.StudentIn4;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;
import com.androidexam.shubclassroom.viewmodel.class_detail.student.ClassDetailStudentViewModel;

import java.util.List;

import retrofit2.Call;

public class ShowStudentInClassStudentFragment extends Fragment {
    private INavigation navigation;
    private FragmentShowStudentInClassStudentBinding binding;
    private String classId;
    private RecyclerView recyclerView;
    private ClassMemberItemAdapter adapter;
    private ClassApiService apiService;
    private String token;

    public ShowStudentInClassStudentFragment(INavigation navigation, String classId) {
        this.navigation = navigation;
        this.classId = classId;
        Log.d("TAG", "ShowStudentInClassStudentFragment: zo day roi");
        apiService = RetrofitClient.getRetrofitInstance().create(ClassApiService.class);
        adapter = new ClassMemberItemAdapter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentManager fm = getParentFragmentManager();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_student_in_class_student, container, false);
        binding.setViewModel(new ClassDetailStudentViewModel(getContext(), navigation, fm));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.rcv_list_student1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        token = SharedPreferencesManager.getInstance(getContext()).getAccessToken();
        Call<List<StudentIn4>> call = apiService.getAllStudentsInClass("Bear " + token, classId);
        call.enqueue(new ApiCallback<List<StudentIn4>, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(List<StudentIn4> responseObject) {
                adapter.setStudents(responseObject);
            }

            @Override
            public void handleFailure(MessageResponse errorResponse) {
                Toast.makeText(getContext(), errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}