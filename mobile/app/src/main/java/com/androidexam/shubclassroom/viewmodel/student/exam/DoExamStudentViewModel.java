package com.androidexam.shubclassroom.viewmodel.student.exam;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.androidexam.shubclassroom.utilities.SharedPreferencesManager;
import com.androidexam.shubclassroom.view.ClassDetailActivity;
import com.androidexam.shubclassroom.view.student.exam.DoExamStudentActivity;

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

    private Context context;
    private ArrayList<QuestionDto> listQuestions;
    private ExamApiService examApiService;

    private String token;

    public DoExamStudentViewModel(Context context, ExamDto examDto) {
        this.context = context;
        this.examDto = examDto;
        index.setValue(0);

        listQuestions = new ArrayList<>();
        examApiService = RetrofitClient.getRetrofitInstance().create(ExamApiService.class);
//        String token = SharedPreferencesManager.getInstance(context).getAccessToken();
        token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEiLCJuYW1lIjoiTMOqIFF14buRYyBSw7RuIiwiYXZhdGFyIjoiZW1wdHkiLCJzdGF0ZSI6MSwicm9sZSI6IlN0dWRlbnQiLCJpYXQiOjE2ODQ0ODEzOTEsImV4cCI6MTY4NDc0MDU5MX0.s7kdvZ6C5D3aUZfcJ0xvSJXfXPrgWk_sHXTd96H7Az0";
        Call<SessionExamDto> call = examApiService.postSession(token, examDto.getId());
        Log.d("EXAM", examDto.getId());
        call.enqueue(new ApiCallback<SessionExamDto, MessageResponse>(MessageResponse.class) {
            @Override
            public void handleSuccess(SessionExamDto responseObject) {
                sessionExamDto = responseObject;
                Call<ArrayList<QuestionDto>> call1 = examApiService.getQuestions(token, examDto.getId());
                call1.enqueue(new ApiCallback<ArrayList<QuestionDto>, MessageResponse>(MessageResponse.class) {
                    @Override
                    public void handleSuccess(ArrayList<QuestionDto> responseObject) {
                        listQuestions = responseObject;
                        totalQuestion.setValue(String.valueOf(listQuestions.size()));
                        setQuestion();
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
            }
        });


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

    public void onButtonChoiceClicked(char chosen) {
        choice.setValue(String.valueOf(chosen));
    }

    public void onButtonSubmitClicked() {
        String choiceValue = choice.getValue();
        String userExamId = sessionExamDto.getUserAnswers()[0];

        if (choiceValue != null && (choiceValue.equals("A") || choiceValue.equals("B") || choiceValue.equals("C") || choiceValue.equals("D"))) {
            Log.d("DEBUG", String.valueOf(listQuestions.size()));
            Log.d("DEBUG", String.valueOf(index.getValue()));
//            doExamDto = new DoExamDto(questionDto.getId(), choiceValue);
//            Call<MessageResponse> postAnswer = examApiService.postAnswer(token, userExamId, doExamDto);
//            postAnswer.enqueue(new ApiCallback<MessageResponse, MessageResponse>(MessageResponse.class) {
//                @Override
//                public void handleSuccess(MessageResponse responseObject) {
//                    index.setValue(index.getValue() + 1);
//                    Log.d("DEBUG", String.valueOf(listQuestions.size()));
//                    setQuestion();
//                }
//
//                @Override
//                public void handleFailure(MessageResponse errorResponse) {
//                    Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
        } else {
            Toast.makeText(context, "Bạn chưa chọn đáp án!", Toast.LENGTH_SHORT).show();
        }

    }

    public void onButtonCancelClicked() {
        Intent i = new Intent(context, ClassDetailActivity.class);
        context.startActivity(i);
    }


}
