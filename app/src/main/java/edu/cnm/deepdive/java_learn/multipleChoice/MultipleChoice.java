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
import edu.cnm.deepdive.java_learn.view.GameFragment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MultipleChoice extends GameFragment {

  private View view;
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

    radiosOne = view.findViewById(R.id.radios_one);
    radiosTwo = view.findViewById(R.id.radios_two);
    radiosThree = view.findViewById(R.id.radios_three);
    radiosFour = view.findViewById(R.id.radios_four);
    radiosFive = view.findViewById(R.id.radios_five);
    radiosSix = view.findViewById(R.id.radios_six);
    submitAnswers = view.findViewById(R.id.submit_answers);

// TODO Get questions and correct answers then get random wrong answers

// TODO Populate questions

// TODO Remove test code

    McAnswer a1 = new McAnswer("This is 1.", false);
    McAnswer a2 = new McAnswer("This is 2", false);
    McAnswer a3 = new McAnswer("This is 3", false);
    McAnswer a4 = new McAnswer("This is correct.", true);

    List<McAnswer> qAnswers = new ArrayList<>();

    qAnswers.add(a1);
    qAnswers.add(a2);
    qAnswers.add(a3);
    qAnswers.add(a4);

    McQuestion question1 = new McQuestion("This is a test Question1.", qAnswers);
    McQuestion question2 = new McQuestion("This is a test Question2.", qAnswers);
    McQuestion question3 = new McQuestion("This is a test Question3.", qAnswers);
    McQuestion question4 = new McQuestion("This is a test Question4.", qAnswers);
    McQuestion question5 = new McQuestion("This is a test Question5.", qAnswers);
    McQuestion question6 = new McQuestion("This is a test Question6.", qAnswers);

    populateQuestionsAndAnswerButtons(radiosOne, question1,
        (TextView) view.findViewById(R.id.question_1));
    populateQuestionsAndAnswerButtons(radiosTwo, question2,
        (TextView) view.findViewById(R.id.question_2));
    populateQuestionsAndAnswerButtons(radiosThree, question3,
        (TextView) view.findViewById(R.id.question_3));
    populateQuestionsAndAnswerButtons(radiosFour, question4,
        (TextView) view.findViewById(R.id.question_4));
    populateQuestionsAndAnswerButtons(radiosFive, question5,
        (TextView) view.findViewById(R.id.question_5));
    populateQuestionsAndAnswerButtons(radiosSix, question6,
        (TextView) view.findViewById(R.id.question_6));

//        for (int i = 0; i < radiosOne.getChildCount(); i++) {
//          ((RadioButton) radiosOne.getChildAt(i)).setText(q.getAnswers().get(i).getAnswer());
//
//          ((RadioButton) radiosOne.getChildAt(i))
//              .setTag(q.getAnswers().get(i).isCorrect() ? Boolean.TRUE : Boolean.FALSE);
//    ((RadioButton) radiosTwo.getChildAt(i)).setText(q.getAnswers().get(i).getAnswer());
//    ((RadioButton) radiosTwo.getChildAt(i))
//        .setTag(q.getAnswers().get(i).isCorrect() ? Boolean.TRUE : Boolean.FALSE);
//    ((RadioButton) radiosThree.getChildAt(i)).setText(q.getAnswers().get(i).getAnswer());
//    ((RadioButton) radiosThree.getChildAt(i))
//        .setTag(q.getAnswers().get(i).isCorrect() ? Boolean.TRUE : Boolean.FALSE);
//    ((RadioButton) radiosFour.getChildAt(i)).setText(q.getAnswers().get(i).getAnswer());
//    ((RadioButton) radiosFour.getChildAt(i))
//        .setTag(q.getAnswers().get(i).isCorrect() ? Boolean.TRUE : Boolean.FALSE);
//        }

    submitAnswers.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        RadioButton selected1 = getActivity().findViewById(radiosOne.getCheckedRadioButtonId());
        RadioButton selected2 = getActivity().findViewById(radiosTwo.getCheckedRadioButtonId());
        RadioButton selected3 = getActivity().findViewById(radiosThree.getCheckedRadioButtonId());
        RadioButton selected4 = getActivity().findViewById(radiosFour.getCheckedRadioButtonId());

        boolean isOneCorrect = (boolean) selected1.getTag();
        boolean isTwoCorrect = (boolean) selected2.getTag();
        boolean isThreeCorrect = (boolean) selected3.getTag();
        boolean isFourCorrect = (boolean) selected4.getTag();

        //TODO Register results with Room
      }
    });

    return view;
  }

  /**
   * Populates the radio group buttons and the correct answer tag. This method will throw and
   * exception if the number of McAnswers taken from McQuestions is not equal to the number of radio
   * buttons in the radioGroup.
   *
   * @param radioGroup - the buttons for the multiple choice questions.
   * @param mcQuestion - the questions for the multiple choice
   */
  private void populateQuestionsAndAnswerButtons(RadioGroup radioGroup, McQuestion mcQuestion,
      TextView textView) {
    List<McAnswer> answers = mcQuestion.getAnswers();
    Iterator<McAnswer> it = answers.iterator();

    Collections.shuffle(answers);

    textView.setText(mcQuestion.getQuestion());

    for (int i = 0; i < radioGroup.getChildCount(); i++) {
      RadioButton button = (RadioButton) radioGroup.getChildAt(i);
      McAnswer answer = it.next();

      button.setText(answer.getAnswerText());
      button.setTag(answer.isCorrect());
    }

  }

  private class GetQuestionsTask extends AsyncTask<Void, Void, McAnswer> {

    @Override
    protected McAnswer doInBackground(Void... voids) {

//TODO Get questions from room

      return null;
    }
  }

}

