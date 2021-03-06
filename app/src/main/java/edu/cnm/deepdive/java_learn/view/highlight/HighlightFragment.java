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
import edu.cnm.deepdive.java_learn.model.entity.HighlightAnswer;
import edu.cnm.deepdive.java_learn.model.entity.HighlightQuestion;
import edu.cnm.deepdive.java_learn.model.entity.Level;
import edu.cnm.deepdive.java_learn.view.GameFragment;
import java.util.ArrayList;
import java.util.List;


/**
 * This class holds the logic for the Highlight game. This game allows users to
 * highlight text and check if they have correctly highlighted the requested piece
 * of code. Makes a call to add progress to the user once it has been completed.
 */
public class HighlightFragment extends GameFragment {

  private List<HighlightQuestion> questions;
  private List<HighlightAnswer> answers;
  private List<Button> buttons;
  private Button button1;
  private Button button2;
  private Button button3;
  private Button submit;
  private OnClickListener listener1;
  private OnClickListener listener2;
  private int correctAnswers;
  private TextViewCursorWatcher textViewCursorWatcher;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

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
    listener1 = (view) -> {
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

  private void setSubmitListener() {
    listener2 = (view) -> {

      if (correctAnswers == 5) {
        Toast
            .makeText(getContext(),
                "You have " + correctAnswers + "/5 correct. That's all of them! Points added!",
                Toast.LENGTH_LONG)
            .show();
        submit.setEnabled(false);
        updateProgress("Basic Highlight");
      } else {
        Toast
            .makeText(getContext(), "You have " + correctAnswers + "/5 correct.", Toast.LENGTH_LONG)
            .show();
      }
    };
  }

  private boolean checkAnswer(String str, String type) {
    for (HighlightAnswer a : answers) {
      if (a.getHlAnswer().equals(str) && a.getType().equals(type)) {
        correctAnswers++;
        return true;
      }
    }
    return false;
  }

  private class QuestionTask extends AsyncTask<Void, Void, List<HighlightQuestion>> {

    @Override
    protected List<HighlightQuestion> doInBackground(Void... voids) {
      JavaLearnDB db = JavaLearnDB.getInstance(getActivity());
      Level level = db.getLevelDao().select("Highlight Test");

      if (level != null) {
        long levId = level.getLevelId();
        questions.addAll(db.getHLQuestionDao().select(levId));
      }

      return null;
    }

    @Override
    protected void onPostExecute(List<HighlightQuestion> hlQuestions) {
      int index = 0;
      for (HighlightQuestion q : questions) {
        buttons.get(index).setText(q.getHlQuestion());
        index++;
      }
      new AnswerTask().execute();
    }
  }

  private class AnswerTask extends AsyncTask<Void, Void, List<HighlightAnswer>> {

    @Override
    protected List<HighlightAnswer> doInBackground(Void... voids) {
      JavaLearnDB db = JavaLearnDB.getInstance(getActivity());

      for (HighlightQuestion q : questions) {
        long queId = db.getHLQuestionDao().select(q.getHlQuestion()).getHlQuestionId();
        answers.addAll(db.getHLAnswerDao().select(queId));
      }

      return null;
    }

    @Override
    protected void onPostExecute(List<HighlightAnswer> hlAnswers) {
      setAnswerListener();
      setSubmitListener();

      submit.setOnClickListener(listener2);
      for (Button button : buttons) {
        button.setOnClickListener(listener1);
      }
    }
  }
}
