package edu.cnm.deepdive.java_learn.view.highlight;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView.BufferType;
import android.widget.Toast;
import edu.cnm.deepdive.java_learn.R;
import edu.cnm.deepdive.java_learn.model.db.JavaLearnDB;
import edu.cnm.deepdive.java_learn.model.entity.HLAnswer;
import edu.cnm.deepdive.java_learn.model.entity.HLQuestion;
import edu.cnm.deepdive.java_learn.model.entity.Level;
import edu.cnm.deepdive.java_learn.view.GameFragment;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Highlight fragment.
 */
public class HighlightFragment extends GameFragment {

  private List<HLQuestion> questions;
  private List<HLAnswer> answers;
  private List<Button> buttons;
  private Button button1;
  private Button button2;
  private Button button3;
  private Button submit;
  private OnClickListener listener;
  private int correctAnswers;
  private TextViewCursorWatcher textViewCursorWatcher;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_highlight, container, false);
    textViewCursorWatcher = view.findViewById(R.id.highlight_text);

    button1 = view.findViewById(R.id.red_button);
    button2 = view.findViewById(R.id.blue_button);
    button3 = view.findViewById(R.id.green_button);
    submit = view.findViewById(R.id.hl_submit_button);
    buttons = new ArrayList<>();
    questions = new ArrayList<>();
    answers = new ArrayList<>();

    buttons.add(button1);
    buttons.add(button2);
    buttons.add(button3);

    textViewCursorWatcher.setTextColor(Color.BLACK);
    textViewCursorWatcher.setText("public static class Solution {\n" +
        "        double[] quotient;\n "
        + "       double[] remainder;\n" +
        " \n" +
        "    private void set(double[] q, double[] r) {\n" +
        "           this.quotient = q;\n" +
        "           this.remainder = r;\n" +
        "      }\n" +
        "  }", BufferType.SPANNABLE);

    new QuestionTask().execute();
    return view;
  }

  private void setAnswerListener() {
    listener = (view) -> {
      int start = textViewCursorWatcher.getSelectionStart();
      int end = textViewCursorWatcher.getSelectionEnd();
      String str = textViewCursorWatcher.getText().toString().substring(start, end);
      Spannable spannable = textViewCursorWatcher.getText();

      switch (view.getId()) {
        case (R.id.red_button):
          if (checkAnswer(str, "1")) {
            spannable.setSpan(new BackgroundColorSpan(Color.RED), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
          } else {
            Toast.makeText(getContext(), "Wrong.", Toast.LENGTH_LONG).show();
          }
          break;
        case (R.id.blue_button):
          if (checkAnswer(str, "2")) {
            spannable.setSpan(new BackgroundColorSpan(Color.CYAN), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
          } else {
            Toast.makeText(getContext(), "Wrong.", Toast.LENGTH_LONG).show();
          }
          break;
        case (R.id.green_button):
          if (checkAnswer(str, "3")) {
            spannable.setSpan(new BackgroundColorSpan(Color.GREEN), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
          } else {
            Toast.makeText(getContext(), "Wrong.", Toast.LENGTH_LONG).show();
          }
          break;
      }
    };
  }

  private boolean checkAnswer(String str, String type) {
    for (HLAnswer a : answers) {
      if (a.getHlAnswer().equals(str) && a.getType().equals(type)) {
        correctAnswers++;
        Toast
            .makeText(getContext(), "You have " + correctAnswers + "/4 correct.", Toast.LENGTH_LONG)
            .show();
        if (correctAnswers == 4) {
          updateProgress("Basic Highlight");
        }
        return true;
      }
    }
    return false;
  }

  private class QuestionTask extends AsyncTask<Void, Void, List<HLQuestion>> {

    @Override
    protected List<HLQuestion> doInBackground(Void... voids) {
      JavaLearnDB db = JavaLearnDB.getInstance(getActivity());
      Level level = db.getLevelDao().select("Highlight Test");

      if (level != null) {
        long levId = level.getLevelId();
        questions.addAll(db.getHLQuestionDao().select(levId));
      }

      return null;
    }

    @Override
    protected void onPostExecute(List<HLQuestion> hlQuestions) {
      int index = 0;
      for (HLQuestion q : questions) {
        buttons.get(index).setText(q.getHlQuestion());
        index++;
      }
      new AnswerTask().execute();
    }
  }

  private class AnswerTask extends AsyncTask<Void, Void, List<HLAnswer>> {

    @Override
    protected List<HLAnswer> doInBackground(Void... voids) {
      JavaLearnDB db = JavaLearnDB.getInstance(getActivity());

      for (HLQuestion q : questions) {
        long queId = db.getHLQuestionDao().select(q.getHlQuestion()).getHlQuestionId();
        answers.addAll(db.getHLAnswerDao().select(queId));
      }

      return null;
    }

    @Override
    protected void onPostExecute(List<HLAnswer> hlAnswers) {
      setAnswerListener();

      for (Button button : buttons) {
        button.setOnClickListener(listener);
      }
    }
  }
}
