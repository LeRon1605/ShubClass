package com.androidexam.shubclassroom.viewmodel.student.exam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.androidexam.shubclassroom.api.ApiCallback;
import com.androidexam.shubclassroom.api.ExamApiService;
import com.androidexam.shubclassroom.api.RetrofitClient;
import com.androidexam.shubclassroom.model.MessageResponse;
import com.androidexam.shubclassroom.model.QuestionDto;
import com.androidexam.shubclassroom.model.exam.DoExamDto;
import com.androidexam.shubclassroom.model.exam.ExamDto;
import com.androidexam.shubclassroom.model.exam.SessionExamDto;
import com.androidexam.shubclassroom.model.exam.UserAnswerDto;
import com.androidexam.shubclassroom.shared.ClassDetailFragment;
import com.androidexam.shubclassroom.shared.FragmentIndex;
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;
import com.androidexam.shubclassroom.view.class_detail.ClassDetailActivity;

import java.util.ArrayList;

import retrofit2.Call;

public class DoExamStudentViewModel {
    private ExamDto examDto;
    private DoExamDto doExamDto;
    private QuestionDto questionDto;
    private SessionExamDto sessionExamDto;

    private MutableLiveData<String> choice = new MutableLiveData<>();
    private MutableLiveData<String> question = new MutableLiveData<>();
    private MutableLiveData<String> ansA = new MutableLiveData<>();
    private MutableLiveData<String> ansB = new MutableLiveData<>();
    private MutableLiveData<String> ansC = new MutableLiveData<>();
    private MutableLiveData<String> ansD = new MutableLiveData<>();
    private MutableLiveData<Integer> index = new MutableLiveData<>();
    private MutableLiveData<String> totalQuestion = new MutableLiveData<>();

    private ArrayList<UserAnswerDto> userAnswerDtos;

    private Context context;
    private ArrayList<QuestionDto> listQuestions;
    private ExamApiService examApiService;

    private String token;

    public DoExamStudentViewModel(Context context, ExamDto examDto) {
        this.context = context;
        this.examDto = examDto;
        index.setValue(0);

        userAnswerDtos = new ArrayList<>();
        listQuestions = new ArrayList<>();

        examApiService = RetrofitClient.getRetrofitInstance().create(ExamApiService.class);
        token = SharedPreferencesManager.getInstance(context).getAccessToken();

        Call<SessionExamDto> startSessionCall = examApiService.postSession("Bear " + token, examDto.getId());
        startSessionCall.enqueue(new ApiCallback<SessionExamDto, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(SessionExamDto responseObject) {
                sessionExamDto = responseObject;
                Call<ArrayList<QuestionDto>> examQuestionCall = examApiService.getQuestions(token, examDto.getId());
                examQuestionCall.enqueue(new ApiCallback<ArrayList<QuestionDto>, MessageResponse>(MessageResponse.class) {
                    @Override
                    public void handleSuccess(ArrayList<QuestionDto> responseObject) {
                        listQuestions = responseObject;
                        totalQuestion.setValue(String.valueOf(listQuestions.size()));

                        for (int i = 0;i < listQuestions.size();i++) {
                            boolean isAnswered = false;
                            for (int j = 0;j < sessionExamDto.getUserAnswers().length;j++) {
                                UserAnswerDto userAnswerDto = sessionExamDto.getUserAnswers()[j];

                                if (userAnswerDto.getExamDetailId().equals(listQuestions.get(i).getId())) {
                                    isAnswered = true;
                                    userAnswerDtos.add(userAnswerDto);
                                    break;
                                }
                            }

                            if (!isAnswered) {
                                UserAnswerDto userAnswerDto = new UserAnswerDto();
                                userAnswerDto.setUserExamId(sessionExamDto.getId());
                                userAnswerDto.setAnswer("");
                                userAnswerDto.setExamDetailId(listQuestions.get(i).getId());
                                userAnswerDtos.add(userAnswerDto);
                            }
                        }

                        setQuestion(0);
                    }

                    @Override
                    public void handleFailure(MessageResponse errorResponse) {
                        Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void handleFailure(MessageResponse errorResponse) {
                Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_LONG).show();
                redirectToExamPage();
            }
        });
    }

    public void setQuestion(int questionIndex) {
        if (questionIndex >= 0 && questionIndex < listQuestions.size()) {
            questionDto = listQuestions.get(questionIndex);
            String[] ans = questionDto.getAnswers();
            index.setValue(questionIndex);
            question.setValue(questionDto.getQuestion());
            ansA.setValue(ans[0]);
            ansB.setValue(ans[1]);
            ansC.setValue(ans[2]);
            ansD.setValue(ans[3]);
            choice.setValue(userAnswerDtos.get(questionIndex).getAnswer());
        }
    }

    public void onButtonChoiceClicked(char chosen) {
        choice.setValue(String.valueOf(chosen));
    }

    public void onButtonSaveClicked() {
        String choiceValue = choice.getValue();

        if (choiceValue != null && (choiceValue.equals("A") || choiceValue.equals("B") || choiceValue.equals("C") || choiceValue.equals("D"))) {
            doExamDto = new DoExamDto();
            doExamDto.setAnswer(choice.getValue());
            doExamDto.setExamDetailId(questionDto.getId());

            Call<MessageResponse> postAnswer = examApiService.postAnswer(token, sessionExamDto.getId(), doExamDto);
            postAnswer.enqueue(new ApiCallback<MessageResponse, MessageResponse>(MessageResponse.class) {
                @Override
                public void handleSuccess(MessageResponse responseObject) {
                    for (int i = 0;i < userAnswerDtos.size();i++) {
                        if (userAnswerDtos.get(i).getExamDetailId().equals(questionDto.getId())) {
                            userAnswerDtos.get(i).setAnswer(choice.getValue());
                            break;
                        }
                    }
                    Toast.makeText(context, responseObject.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void handleFailure(MessageResponse errorResponse) {
                    Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(context, "Bạn chưa chọn đáp án!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onButtonSubmitClicked() {
        Call<MessageResponse> submit = examApiService.postSubmit(token, sessionExamDto.getId());
        submit.enqueue(new ApiCallback<MessageResponse, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(MessageResponse responseObject) {
                Toast.makeText(context, responseObject.getMessage(), Toast.LENGTH_SHORT).show();
                redirectToExamPage();
            }

            @Override
            public void handleFailure(MessageResponse errorResponse) {
                Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBackQuestionClicked() {
        setQuestion(index.getValue() - 1);
    }

    public void onNextQuestionClicked() {
        setQuestion(index.getValue() + 1);
    }

    public void onButtonCancelClicked() {
        redirectToExamPage();
    }

    public ExamDto getExamDto() {
        return examDto;
    }

    public void setExamDto(ExamDto examDto) {
        this.examDto = examDto;
    }

    public DoExamDto getDoExamDto() {
        return doExamDto;
    }

    public void setDoExamDto(DoExamDto doExamDto) {
        this.doExamDto = doExamDto;
    }

    public QuestionDto getQuestionDto() {
        return questionDto;
    }

    public void setQuestionDto(QuestionDto questionDto) {
        this.questionDto = questionDto;
    }

    public SessionExamDto getSessionExamDto() {
        return sessionExamDto;
    }

    public void setSessionExamDto(SessionExamDto sessionExamDto) {
        this.sessionExamDto = sessionExamDto;
    }

    public MutableLiveData<String> getChoice() {
        return choice;
    }

    public void setChoice(MutableLiveData<String> choice) {
        this.choice = choice;
    }

    public MutableLiveData<String> getQuestion() {
        return question;
    }

    public void setQuestion(MutableLiveData<String> question) {
        this.question = question;
    }

    public MutableLiveData<String> getAnsA() {
        return ansA;
    }

    public void setAnsA(MutableLiveData<String> ansA) {
        this.ansA = ansA;
    }

    public MutableLiveData<String> getAnsB() {
        return ansB;
    }

    public void setAnsB(MutableLiveData<String> ansB) {
        this.ansB = ansB;
    }

    public MutableLiveData<String> getAnsC() {
        return ansC;
    }

    public void setAnsC(MutableLiveData<String> ansC) {
        this.ansC = ansC;
    }

    public MutableLiveData<String> getAnsD() {
        return ansD;
    }

    public void setAnsD(MutableLiveData<String> ansD) {
        this.ansD = ansD;
    }

    public MutableLiveData<Integer> getIndex() {
        return index;
    }

    public void setIndex(MutableLiveData<Integer> index) {
        this.index = index;
    }

    public MutableLiveData<String> getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(MutableLiveData<String> totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    private void redirectToExamPage() {
        Bundle bundle = new Bundle();
        bundle.putInt("FragmentIndex", FragmentIndex.Student.getValue());
        bundle.putInt("fragment", ClassDetailFragment.StudentExam.getValue());
        Intent intent = new Intent(context, ClassDetailActivity.class);
        intent.putExtra("myBundle", bundle);
        intent.putExtra("classId", examDto.getClassId());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public void setQuestion() {
        if (index.getValue() == listQuestions.size() - 1) {
            submitExam();
        }
        else
        {
            questionDto = listQuestions.get(index.getValue());
            question.setValue(questionDto.getQuestion());
            String[] ans = questionDto.getAnswers();
            ansA.setValue(ans[0]);
            ansB.setValue(ans[1]);
            ansC.setValue(ans[2]);
            ansD.setValue(ans[3]);
        }
    }

    public void submitExam() {
        Call<MessageResponse> submit = examApiService.postSubmit(token, examDto.getId());
        submit.enqueue(new ApiCallback<MessageResponse, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(MessageResponse responseObject) {
                Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFailure(MessageResponse errorResponse) {
                Toast.makeText(context, errorResponse.getMessage() + "submit", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
