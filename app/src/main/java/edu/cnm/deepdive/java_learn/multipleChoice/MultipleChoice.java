package edu.cnm.deepdive.java_learn.multipleChoice;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import edu.cnm.deepdive.java_learn.R;
import edu.cnm.deepdive.java_learn.model.dao.MCQuestionDao;
import edu.cnm.deepdive.java_learn.model.entity.Level;
import edu.cnm.deepdive.java_learn.model.entity.MCAnswer;
import edu.cnm.deepdive.java_learn.model.entity.MCQuestion;
import edu.cnm.deepdive.java_learn.model.pojo.MCQuestionWithAnswers;
import edu.cnm.deepdive.java_learn.view.GameFragment;
import edu.cnm.deepdive.java_learn.model.db.JavaLearnDB;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * This is the MultipleChoice class game that retrieves 1 random question(populated in the
 * textView the correct answer(populated next to a random radio button in the same RadioGroup as
 * the corresponding populated question) and 3 random wrong answers.
 */
public class MultipleChoice extends GameFragment {

  private View view;
  private TextView[] questionViews;
  private RadioGroup[] answerGroups;
  private RadioGroup radiosOne;
  private RadioGroup radiosTwo;
  private RadioGroup radiosThree;
  private RadioGroup radiosFour;
  private RadioGroup radiosFive;
  private RadioGroup radiosSix;
  private Button submitAnswers;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    view = inflater.inflate(R.layout.fragment_multiple_choice, container, false);
    questionViews = new TextView[] {
        view.findViewById(R.id.question_1),
        view.findViewById(R.id.question_2),
        view.findViewById(R.id.question_3),
        view.findViewById(R.id.question_4),
        view.findViewById(R.id.question_5),
        view.findViewById(R.id.question_6)
    };
    answerGroups = new RadioGroup[] {
        view.findViewById(R.id.radios_one),
        view.findViewById(R.id.radios_two),
        view.findViewById(R.id.radios_three),
        view.findViewById(R.id.radios_four),
        view.findViewById(R.id.radios_five),
        view.findViewById(R.id.radios_six)
    };

    submitAnswers = view.findViewById(R.id.submit_answers);
    submitAnswers.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

        //TODO Iterate over answer groups and check for correct answers
        // TODO Register results with Room
      }

    });
    new GetQuestionsTask().execute(1L); // FIXME Should not be hardcoded 1
    return view;
  }

  /**
   * Populates the radio group buttons and the correct answer tag. This method will throw and
   * exception if the number of McAnswers taken from McQuestions is not equal to the number of radio
   * buttons in the radioGroup.
   *
   * @param questionView - the view to be populated for the text questions.
   * @param radioGroup - the buttons for the multiple choice questions.
   * @param question - the questions for the multiple choice
   */
  private void populateQuestionsAndAnswerButtons(TextView questionView, RadioGroup radioGroup,
      MCQuestionWithAnswers question) {
    List<MCAnswer> answers = question.getAnswers();

    Collections.shuffle(answers);

    questionView.setText(question.getQuestion().getMcQuestion());
    int answerNumber = 0;
    for (MCAnswer answer : answers) {
      RadioButton button = (RadioButton) radioGroup.getChildAt(answerNumber);
      button.setText(answer.getMcAnswer());
      button.setTag(answer.isCorrect());
      answerNumber++;
    }

  }

  /**
   *
   * @param
   */
  private class GetQuestionsTask extends AsyncTask<Long, Void, List<MCQuestionWithAnswers>> {

    @Override
    protected List<MCQuestionWithAnswers> doInBackground(Long... levels) {

      JavaLearnDB db = JavaLearnDB.getInstance(getActivity());
      return db.getMCQuestionDao().selectWithAnswers(levels[0], 6); // HACK
    }

    @Override
    protected void onPostExecute(List<MCQuestionWithAnswers> questions) {
      int questionNumber = 0;
      for (MCQuestionWithAnswers question : questions) {
        populateQuestionsAndAnswerButtons(questionViews[questionNumber], answerGroups[questionNumber], question);
        questionNumber++;
      }
    }

  }

}


