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
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.cnm.deepdive.java_learn.R;
import edu.cnm.deepdive.java_learn.model.db.JavaLearnDB;
import edu.cnm.deepdive.java_learn.model.entity.FillBlankAnswer;
import edu.cnm.deepdive.java_learn.model.entity.FillBlankQuestion;
import edu.cnm.deepdive.java_learn.model.entity.Level;
import edu.cnm.deepdive.java_learn.view.GameFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Fill blank fragment.
 */
public class FillBlankFragment extends GameFragment {

  @BindView(R.id.fill_in_one_spinner)
  Spinner spinner1;
  @BindView(R.id.fill_in_two_spinner)
  Spinner spinner2;
  @BindView(R.id.fill_in_three_spinner)
  Spinner spinner3;
  @BindView(R.id.fill_in_four_spinner)
  Spinner spinner4;
  @BindView(R.id.fb_check_mark_1)
  ImageView checkMark1;
  @BindView(R.id.fb_check_mark_2)
  ImageView checkMark2;
  @BindView(R.id.fb_check_mark_3)
  ImageView checkMark3;
  @BindView(R.id.fb_check_mark_4)
  ImageView checkMark4;


  private List<Spinner> spinners;
  private List<FillBlankQuestion> questions;
  private List<FillBlankAnswer> answers;
  private List<ImageView> checkMarks;
  private Button submit;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    View view = inflater.inflate(R.layout.fragment_fill_blank, container, false);
    ButterKnife.bind(this, view);

    answers = new ArrayList<>();
    questions = new ArrayList<>();
    spinners = new ArrayList<>();
    checkMarks = new ArrayList<>();

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

    for (ImageView iv : checkMarks) {
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
        FillBlankAnswer answer = (FillBlankAnswer) spinner.getSelectedItem();
        if (answer.isCorrect()) {
          correct++;
          checkMarks.get(index).setVisibility(View.VISIBLE);
        }
        index++;
      }

      if (correct == 4) {
        submit.setEnabled(false);
        updateProgress("Basic Fill-in-the-Blank");
        Toast.makeText(getContext(),
            "You have " + correct + "/4 correct. That's all of them! Points added!",
            Toast.LENGTH_LONG)
            .show();
      } else {
        Toast.makeText(getContext(), "You have " + correct + "/4 correct.", Toast.LENGTH_LONG)
            .show();
      }
    });
  }

  private class AnswerTask extends AsyncTask<Void, Void, FillBlankAnswer> {

    @Override
    protected FillBlankAnswer doInBackground(Void... voids) {
      JavaLearnDB db = JavaLearnDB.getInstance(getActivity());
      Level level = db.getLevelDao().select("Fill Blank Test");

      if (level != null) {
        long levId = level.getLevelId();
        questions.addAll(db.getFBQuestionDao().select(levId));
      }

      for (FillBlankQuestion q : questions) {
        long queId = q.getFbQuestionId();
        answers.addAll(db.getFBAnswerDao().select(queId));
      }

      return null;
    }

    @Override
    protected void onPostExecute(FillBlankAnswer fbAnswer) {
      int index = 0;
      for (FillBlankQuestion q : questions) {
        List<FillBlankAnswer> ans = new ArrayList<>();
        for (FillBlankAnswer a : answers) {
          if (a.getFbQuestionId() == q.getFbQuestionId()) {
            ans.add(a);
          }
        }
        ArrayAdapter<FillBlankAnswer> adapter = new ArrayAdapter<>(
            getActivity(), R.layout.support_simple_spinner_dropdown_item, ans);

        spinners.get(index).setAdapter(adapter);
        index++;
      }

      setSubmitButton();
    }
  }

}
