package edu.cnm.deepdive.java_learn.view.multipleChoice;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.cnm.deepdive.java_learn.R;
import edu.cnm.deepdive.java_learn.model.entity.Level;
import edu.cnm.deepdive.java_learn.model.entity.MultipleChoiceA;
import edu.cnm.deepdive.java_learn.model.pojo.MultipleChoiceQWithA;
import edu.cnm.deepdive.java_learn.view.GameFragment;
import edu.cnm.deepdive.java_learn.model.db.JavaLearnDB;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is the MultipleChoice class game that retrieves 1 random question(populated in the textView
 * the correct answer(populated next to a random radio button in the same RadioGroup as the
 * corresponding populated question) and 3 random wrong answers.
 */
public class MultipleChoice extends GameFragment {

  @BindView(R.id.check_mark_1)
  ImageView checkMark1;
  @BindView(R.id.check_mark_2)
  ImageView checkMark2;
  @BindView(R.id.check_mark_3)
  ImageView checkMark3;
  @BindView(R.id.check_mark_4)
  ImageView checkMark4;
  @BindView(R.id.check_mark_5)
  ImageView checkMark5;
  @BindView(R.id.check_mark_6)
  ImageView checkMark6;

  private List<ImageView> checkMarks;
  private View view;
  private TextView[] questionViews;
  private RadioGroup[] answerGroups;
  private Button submitAnswers;

  @SuppressLint("RestrictedApi")
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    view = inflater.inflate(R.layout.fragment_multiple_choice, container, false);
    ButterKnife.bind(this, view);

    checkMarks = new ArrayList<>();

    questionViews = new TextView[]{
        view.findViewById(R.id.question_1),
        view.findViewById(R.id.question_2),
        view.findViewById(R.id.question_3),
        view.findViewById(R.id.question_4),
        view.findViewById(R.id.question_5),
        view.findViewById(R.id.question_6)
    };
    answerGroups = new RadioGroup[]{
        view.findViewById(R.id.radios_one),
        view.findViewById(R.id.radios_two),
        view.findViewById(R.id.radios_three),
        view.findViewById(R.id.radios_four),
        view.findViewById(R.id.radios_five),
        view.findViewById(R.id.radios_six)
    };

    checkMarks.add(checkMark1);
    checkMarks.add(checkMark2);
    checkMarks.add(checkMark3);
    checkMarks.add(checkMark4);
    checkMarks.add(checkMark5);
    checkMarks.add(checkMark6);

    for (ImageView iv: checkMarks) {
      iv.setVisibility(View.INVISIBLE);
    }

    submitAnswers = view.findViewById(R.id.submit_answers);
    submitAnswers.setOnClickListener(v -> {
      int correct = 0;
      int index =0;

      for (RadioGroup group : answerGroups) {
        int checkedId = group.getCheckedRadioButtonId();
        if (checkedId >= 0) {
          RadioButton button = getActivity().findViewById(checkedId);
          if ((Boolean) button.getTag()) {
            correct++;
            checkMarks.get(index).setVisibility(View.VISIBLE);
            Log.d(MultipleChoice.class.getSimpleName(), "Correct!");
          }
        }
        index++;
      }

      Toast.makeText(getContext(), "You have " + correct + " out of 6", Toast.LENGTH_LONG).show();

      // TODO Register results with Room
    });
    new GetQuestionsTask().execute(); // FIXME Should not be hardcoded 1
    return view;
  }

  private void populateQuestionsAndAnswerButtons(TextView questionView, RadioGroup radioGroup,
      MultipleChoiceQWithA question) {
    List<MultipleChoiceA> answers = question.getAnswers();

    Collections.shuffle(answers);

    questionView.setText(question.getQuestion().getMcQuestion());
    int answerNumber = 0;
    for (MultipleChoiceA answer : answers) {
      RadioButton button = (RadioButton) radioGroup.getChildAt(answerNumber);
      button.setText(answer.getMcAnswer());
      button.setTag(answer.isCorrect());
      answerNumber++;
    }

  }

  private class GetQuestionsTask extends AsyncTask<Void, Void, List<MultipleChoiceQWithA>> {

    @Override
    protected List<MultipleChoiceQWithA> doInBackground(Void... voids) {

      JavaLearnDB db = JavaLearnDB.getInstance(getActivity());
      Level level = db.getLevelDao().select("Multiple Choice Test");
      return db.getMCQuestionDao().selectWithAnswers(level.getLevelId(), 6); // HACK
    }


    @Override
    protected void onPostExecute(List<MultipleChoiceQWithA> questions) {
      int questionNumber = 0;
      for (MultipleChoiceQWithA question : questions) {
        populateQuestionsAndAnswerButtons(questionViews[questionNumber],
            answerGroups[questionNumber], question);
        questionNumber++;
      }
    }

  }

}


