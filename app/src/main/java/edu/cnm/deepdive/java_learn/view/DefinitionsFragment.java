package edu.cnm.deepdive.java_learn.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.cnm.deepdive.java_learn.R;
import edu.cnm.deepdive.java_learn.model.db.JavaLearnDB;
import edu.cnm.deepdive.java_learn.model.entity.DefinitionsAnswer;
import edu.cnm.deepdive.java_learn.model.entity.DefinitionsQuestion;
import edu.cnm.deepdive.java_learn.model.entity.Level;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Definitions fragment.
 */
public class DefinitionsFragment extends GameFragment {

  @BindView(R.id.definition_one_spinner)
  Spinner spinner1;
  @BindView(R.id.definition_second_spinner)
  Spinner spinner2;
  @BindView(R.id.definition_third_spinner)
  Spinner spinner3;
  @BindView(R.id.definition_fourth_spinner)
  Spinner spinner4;
  @BindView(R.id.definition_fifth_spinner)
  Spinner spinner5;
  @BindView(R.id.first_definition)
  TextView question1;
  @BindView(R.id.second_definition)
  TextView question2;
  @BindView(R.id.third_definition)
  TextView question3;
  @BindView(R.id.fourth_definition)
  TextView question4;
  @BindView(R.id.fifth_definition)
  TextView question5;
  @BindView(R.id.def_check_mark_1)
  ImageView checkMark1;
  @BindView(R.id.def_check_mark_2)
  ImageView checkMark2;
  @BindView(R.id.def_check_mark_3)
  ImageView checkMark3;
  @BindView(R.id.def_check_mark_4)
  ImageView checkMark4;
  @BindView(R.id.def_check_mark_5)
  ImageView checkMark5;

  private Button submitButton;
  private List<DefinitionsQuestion> questions;
  private List<DefinitionsAnswer> answers;
  private List<Spinner> spinners;
  private List<TextView> questionTexts;
  private List<ImageView> checkMarks;


  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_definitions, container, false);
    ButterKnife.bind(this, view);
    questions = new ArrayList<>();
    answers = new ArrayList<>();
    spinners = new ArrayList<>();
    questionTexts = new ArrayList<>();
    checkMarks = new ArrayList<>();

    submitButton = view.findViewById(R.id.submit_definitions);
    setSubmitButton();

    spinners.add(spinner1);
    spinners.add(spinner2);
    spinners.add(spinner3);
    spinners.add(spinner4);
    spinners.add(spinner5);

    questionTexts.add(question1);
    questionTexts.add(question2);
    questionTexts.add(question3);
    questionTexts.add(question4);
    questionTexts.add(question5);

    checkMarks.add(checkMark1);
    checkMarks.add(checkMark2);
    checkMarks.add(checkMark3);
    checkMarks.add(checkMark4);
    checkMarks.add(checkMark5);

    for (ImageView iv : checkMarks) {
      iv.setVisibility(View.INVISIBLE);
    }

    new QuestionTask().execute();

    return view;
  }

  private void setSubmitButton() {
    submitButton.setOnClickListener(v -> {
      int correct = 0;
      int index = 0;

      for (Spinner spinner : spinners) {
        DefinitionsAnswer answer = (DefinitionsAnswer) spinner.getSelectedItem();
        if (answer.isCorrect()) {
          correct++;
          checkMarks.get(index).setVisibility(View.VISIBLE);
        }
        index++;
      }

      if (correct == 5) {
        submitButton.setEnabled(false);
        updateProgress("Basic Definitions");
        Toast.makeText(getContext(),
            "You have " + correct + "/5 correct. That's all of them! Points added!",
            Toast.LENGTH_LONG)
            .show();
      } else {
        Toast.makeText(getContext(), "You have " + correct + "/5 correct.", Toast.LENGTH_LONG)
            .show();
      }
    });
  }

  private class QuestionTask extends AsyncTask<Void, Void, List<DefinitionsQuestion>> {

    @Override
    protected List<DefinitionsQuestion> doInBackground(Void... voids) {
      JavaLearnDB db = JavaLearnDB.getInstance(getActivity());
      Level level = db.getLevelDao().select("Definitions Test");

      if (level != null) {
        long levId = level.getLevelId();
        questions.addAll(db.getDDQuestionDao().select(levId));
      }

      return null;
    }

    @Override
    protected void onPostExecute(List<DefinitionsQuestion> definitionsQuestions) {
      new AnswerTask().execute();
    }
  }

  private class AnswerTask extends AsyncTask<Void, Void, List<DefinitionsAnswer>> {

    @Override
    protected List<DefinitionsAnswer> doInBackground(Void... voids) {

      JavaLearnDB db = JavaLearnDB.getInstance(getActivity());

      for (DefinitionsQuestion q : questions) {
        long queId = db.getDDQuestionDao().select(q.getDefQuestion()).getDefQuestionId();
        answers.addAll(db.getDDAnswerDao().select(queId));
      }

      return null;
    }

    @Override
    protected void onPostExecute(List<DefinitionsAnswer> definitionsAnswers) {
      Collections.shuffle(questions);
      int index = 0;
      for (DefinitionsQuestion q : questions) {
        List<DefinitionsAnswer> ans = new ArrayList<>();
        questionTexts.get(index).setText(q.getDefQuestion());
        for (DefinitionsAnswer a : answers) {
          if ((a.getDefQuestionId() == q.getDefQuestionId())) {
            ans.add(a);
          }
        }
        ArrayAdapter<DefinitionsAnswer> adapter = new ArrayAdapter<>(
            getActivity(), R.layout.support_simple_spinner_dropdown_item, ans);

        spinners.get(index).setAdapter(adapter);
        index++;
      }
    }
  }

}
