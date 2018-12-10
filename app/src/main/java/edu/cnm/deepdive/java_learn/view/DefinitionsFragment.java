package edu.cnm.deepdive.java_learn.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import edu.cnm.deepdive.java_learn.R;
import edu.cnm.deepdive.java_learn.model.db.JavaLearnDB;
import edu.cnm.deepdive.java_learn.model.entity.DDAnswer;
import edu.cnm.deepdive.java_learn.model.entity.DDQuestion;
import edu.cnm.deepdive.java_learn.model.entity.Level;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Definitions fragment.
 */
public class DefinitionsFragment extends GameFragment {

  private List<DDQuestion> questions;
  private List<DDAnswer> answers;
  private List<Spinner> spinners;
  private List<TextView> questionTexts;
  private List<ImageView> checkMarks;
  private Spinner spinner1;
  private Spinner spinner2;
  private Spinner spinner3;
  private Spinner spinner4;
  private Spinner spinner5;
  private TextView question1;
  private TextView question2;
  private TextView question3;
  private TextView question4;
  private TextView question5;
  private ImageView checkMark1;
  private ImageView checkMark2;
  private ImageView checkMark3;
  private ImageView checkMark4;
  private ImageView checkMark5;
  private Button submitButton;


  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_definitions, container, false);
    questions = new ArrayList<>();
    answers = new ArrayList<>();
    spinners = new ArrayList<>();
    questionTexts = new ArrayList<>();
    checkMarks = new ArrayList<>();

    spinner1 = view.findViewById(R.id.definition_one_spinner);
    spinner2 = view.findViewById(R.id.definition_second_spinner);
    spinner3 = view.findViewById(R.id.definition_third_spinner);
    spinner4 = view.findViewById(R.id.definition_fourth_spinner);
    spinner5 = view.findViewById(R.id.definition_fifth_spinner);

    question1 = view.findViewById(R.id.first_definition);
    question2 = view.findViewById(R.id.second_definition);
    question3 = view.findViewById(R.id.third_definition);
    question4 = view.findViewById(R.id.fourth_definition);
    question5 = view.findViewById(R.id.fifth_definition);

    checkMark1 = view.findViewById(R.id.check_mark_1);
    checkMark2 = view.findViewById(R.id.check_mark_2);
    checkMark3 = view.findViewById(R.id.check_mark_3);
    checkMark4 = view.findViewById(R.id.check_mark_4);
    checkMark5 = view.findViewById(R.id.check_mark_5);

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

    for (ImageView iv: checkMarks) {
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
        DDAnswer answer = (DDAnswer) spinner.getSelectedItem();
        if (answer.isCorrect()) {
          correct++;
          checkMarks.get(index).setVisibility(View.VISIBLE);
        }
        index++;
      }
      Toast.makeText(getContext(), "You have " + correct + "/5 correct.", Toast.LENGTH_LONG)
          .show();
    });
  }

  private class QuestionTask extends AsyncTask<Void, Void, List<DDQuestion>> {

    @Override
    protected List<DDQuestion> doInBackground(Void... voids) {
      JavaLearnDB db = JavaLearnDB.getInstance(getActivity());
      Level level = db.getLevelDao().select("Definitions Test");

      if (level != null) {
        long levId = level.getLevelId();
        questions.addAll(db.getDDQuestionDao().select(levId));
      }

      return null;
    }

    @Override
    protected void onPostExecute(List<DDQuestion> ddQuestions) {
      new AnswerTask().execute();
    }
  }

  private class AnswerTask extends AsyncTask<Void, Void, List<DDAnswer>> {

    @Override
    protected List<DDAnswer> doInBackground(Void... voids) {

      JavaLearnDB db = JavaLearnDB.getInstance(getActivity());

      for (DDQuestion q : questions) {
        long queId = db.getDDQuestionDao().select(q.getDdQuestion()).getDdQuestionId();
        answers.addAll(db.getDDAnswerDao().select(queId));
      }

      return null;
    }

    @Override
    protected void onPostExecute(List<DDAnswer> ddAnswers) {
      Collections.shuffle(questions);
      int index = 0;
      for (DDQuestion q : questions) {
        List<DDAnswer> ans = new ArrayList<>();
        questionTexts.get(index).setText(q.getDdQuestion());
        for (DDAnswer a : answers) {
          if ((a.getDdQuestionId() == q.getDdQuestionId())) {
            ans.add(a);
          }
        }
        ArrayAdapter<DDAnswer> adapter = new ArrayAdapter<>(
            getActivity(), R.layout.support_simple_spinner_dropdown_item, ans);

        spinners.get(index).setAdapter(adapter);
        index++;
      }
    }
  }

}
