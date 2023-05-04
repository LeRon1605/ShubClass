
package com.androidexam.shubclassroom.view.student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.androidexam.shubclassroom.R;
import com.androidexam.shubclassroom.databinding.FragmentStudentClassMemberDetailBinding;
import com.androidexam.shubclassroom.shared.INavigation;
import com.androidexam.shubclassroom.viewmodel.ClassMemberViewModel;

public class ClassMemberDetailStudentFragment extends Fragment {
    private INavigation navigation;
    private FragmentStudentClassMemberDetailBinding binding;
    private ClassMemberViewModel viewModel;
    private String memberId;

    public ClassMemberDetailStudentFragment(INavigation navigation, String memberId)
    {
        this.navigation = navigation;
        this.memberId = memberId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_class_member_detail, container, false);
        viewModel = new ClassMemberViewModel(getContext(), navigation, memberId);

        binding.setMember(viewModel);
        View view = binding.getRoot();
        return view;
    }
}