package edu.cnm.deepdive.java_learn.view.fillInBlank;

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
import android.widget.Toast;
import edu.cnm.deepdive.java_learn.R;
import edu.cnm.deepdive.java_learn.model.db.JavaLearnDB;
import edu.cnm.deepdive.java_learn.model.entity.FBAnswer;
import edu.cnm.deepdive.java_learn.model.entity.FBQuestion;
import edu.cnm.deepdive.java_learn.model.entity.Level;
import edu.cnm.deepdive.java_learn.view.GameFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Fill blank fragment.
 */
public class FillBlankFragment extends GameFragment {

  private Spinner spinner1;
  private Spinner spinner2;
  private Spinner spinner3;
  private Spinner spinner4;
  private ImageView checkMark1;
  private ImageView checkMark2;
  private ImageView checkMark3;
  private ImageView checkMark4;
  private List<Spinner> spinners;
  private List<FBQuestion> questions;
  private List<FBAnswer> answers;
  private List<ImageView> checkMarks;
  private Button submit;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    View view = inflater.inflate(R.layout.fragment_fill_blank, container, false);

    answers = new ArrayList<>();
    questions = new ArrayList<>();
    spinners = new ArrayList<>();
    checkMarks = new ArrayList<>();


    spinner1 = view.findViewById(R.id.fill_in_one_spinner);
    spinner2 = view.findViewById(R.id.fill_in_two_spinner);
    spinner3 = view.findViewById(R.id.fill_in_three_spinner);
    spinner4 = view.findViewById(R.id.fill_in_four_spinner);

    checkMark1 = view.findViewById(R.id.check_mark_1);
    checkMark2 = view.findViewById(R.id.check_mark_2);
    checkMark3 = view.findViewById(R.id.check_mark_3);
    checkMark4 = view.findViewById(R.id.check_mark_4);

    submit = view.findViewById(R.id.fill_blank_submit_button);
    setSubmitButton();

    spinners.add(spinner1);
    spinners.add(spinner2);
    spinners.add(spinner3);
    spinners.add(spinner4);

    checkMarks.add(checkMark1);
    checkMarks.add(checkMark2);
    checkMarks.add(checkMark3);
    checkMarks.add(checkMark4);

    for (ImageView iv: checkMarks) {
      iv.setVisibility(View.INVISIBLE);
    }

    new AnswerTask().execute();

    return view;
  }

  private void setSubmitButton() {
    submit.setOnClickListener(v -> {
      int correct = 0;
      int index = 0;

      for (Spinner spinner : spinners) {
        FBAnswer answer = (FBAnswer) spinner.getSelectedItem();
        if (answer.isCorrect()) {
          correct++;
          checkMarks.get(index).setVisibility(View.VISIBLE);
        }
        index++;
      }
      Toast.makeText(getContext(), "You have " + correct + "/4 correct.", Toast.LENGTH_LONG)
          .show();
    });
  }

  private class AnswerTask extends AsyncTask<Void, Void, FBAnswer> {

    @Override
    protected FBAnswer doInBackground(Void... voids) {
      JavaLearnDB db = JavaLearnDB.getInstance(getActivity());
      Level level = db.getLevelDao().select("Fill Blank Test");

      if (level != null) {
        long levId = level.getLevelId();
        questions.addAll(db.getFBQuestionDao().select(levId));
      }

      for (FBQuestion q : questions) {
        long queId = q.getFbQuestionId();
        answers.addAll(db.getFBAnswerDao().select(queId));
      }

      return null;
    }

    @Override
    protected void onPostExecute(FBAnswer fbAnswer) {
      int index = 0;
      for (FBQuestion q : questions) {
        List<FBAnswer> ans = new ArrayList<>();
        for(FBAnswer a : answers) {
          if (a.getFbQuestionId() == q.getFbQuestionId()) {
            ans.add(a);
          }
        }
        ArrayAdapter<FBAnswer> adapter = new ArrayAdapter<>(
            getActivity(), R.layout.support_simple_spinner_dropdown_item, ans);

        spinners.get(index).setAdapter(adapter);
        index++;
      }

      setSubmitButton();
    }
  }

}
