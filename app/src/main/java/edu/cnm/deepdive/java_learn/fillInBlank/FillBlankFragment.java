package edu.cnm.deepdive.java_learn.fillInBlank;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class FillBlankFragment extends GameFragment {

  private Spinner spinner1;
  private Spinner spinner2;
  private Spinner spinner3;
  private Spinner spinner4;
  private List<Spinner> spinners;
  private List<FBAnswer> answers;
  private Button submit;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    View view = inflater.inflate(R.layout.fragment_fill_blank, container, false);

    spinner1 = view.findViewById(R.id.fill_in_one_spinner);
    spinner2 = view.findViewById(R.id.fill_in_two_spinner);
    spinner3 = view.findViewById(R.id.fill_in_three_spinner);
    spinner4 = view.findViewById(R.id.fill_in_four_spinner);

    submit = view.findViewById(R.id.fill_blank_submit_button);

    spinners.add(spinner1);
    spinners.add(spinner2);
    spinners.add(spinner3);
    spinners.add(spinner4);


    return view;
  }

  private void setSubmitButton() {
    submit.setOnClickListener(v -> {
      int correct = 0;

      for (Spinner spinner : spinners) {
        FBAnswer answer = (FBAnswer) spinner.getSelectedItem();
        if (answer.isCorrect()) {
          correct++;
        }
      }
      Toast.makeText(getContext(), "You have " + correct + "/5 correct.", Toast.LENGTH_LONG)
          .show();
    });
  }

  private class AnswerTask extends AsyncTask<Void, Void, FBAnswer> {

    @Override
    protected FBAnswer doInBackground(Void... voids) {
      JavaLearnDB db = JavaLearnDB.getInstance(getActivity());
      Level level = db.getLevelDao().select("Highlight Test");
      List<FBQuestion> questions = new ArrayList<>();


      if (level != null) {
        long levId = level.getLevelId();
        questions.addAll(db.getFBQuestionDao().select(levId));
      }

      for (FBQuestion q : questions) {
        long queId = q.getFbQuestionId();
        answers.addAll(db.getFBAnswerDao().select(queId));
      }

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

      return null;
    }

    @Override
    protected void onPostExecute(FBAnswer fbAnswer) {
      setSubmitButton();
    }
  }

}
