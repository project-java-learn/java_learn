package edu.cnm.deepdive.java_learn.multipleChoice;


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

  private TextView question1;
  private TextView question2;
  private TextView question3;
  private TextView question4;
  private TextView question5;
  private TextView question6;
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

    View view = inflater.inflate(R.layout.fragment_multiple_choice, container, false);

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
    McAnswers a1 = new McAnswers("This is 1.", false);
    McAnswers a2 = new McAnswers("This is 2", false);
    McAnswers a3 = new McAnswers("This is 3", false);
    McAnswers a4 = new McAnswers("This is correct.", true);

    List<McAnswers> qAnswers = new ArrayList<>();

    qAnswers.add(a1);
    qAnswers.add(a2);
    qAnswers.add(a3);
    qAnswers.add(a4);

    populateButtons(radiosOne, qAnswers);
    populateButtons(radiosTwo, qAnswers);
    populateButtons(radiosThree, qAnswers);
    populateButtons(radiosFour, qAnswers);
    populateButtons(radiosFive, qAnswers);
    populateButtons(radiosSix, qAnswers);

//    String correctAnswer = "This is correct";
//

//    McQuestions q = new McQuestions("This is question one.", qAnswers);
//
//    question1 = view.findViewById(R.id.question_1);
//
//    question1.setText(q.getQuestion());

//    for (int i = 0; i < radiosOne.getChildCount(); i++) {
//      ((RadioButton) radiosOne.getChildAt(i)).setText(q.getAnswers().get(i).getAnswer());
//      ((RadioButton) radiosOne.getChildAt(i))
//          .setTag(q.getAnswers().get(i).isCorrect() ? Boolean.TRUE : Boolean.FALSE);
//      ((RadioButton) radiosTwo.getChildAt(i)).setText(q.getAnswers().get(i).getAnswer());
//      ((RadioButton) radiosTwo.getChildAt(i))
//          .setTag(q.getAnswers().get(i).isCorrect() ? Boolean.TRUE : Boolean.FALSE);
//      ((RadioButton) radiosThree.getChildAt(i)).setText(q.getAnswers().get(i).getAnswer());
//      ((RadioButton) radiosThree.getChildAt(i))
//          .setTag(q.getAnswers().get(i).isCorrect() ? Boolean.TRUE : Boolean.FALSE);
//      ((RadioButton) radiosFour.getChildAt(i)).setText(q.getAnswers().get(i).getAnswer());
//      ((RadioButton) radiosFour.getChildAt(i))
//          .setTag(q.getAnswers().get(i).isCorrect() ? Boolean.TRUE : Boolean.FALSE);
//    }

    radiosOne.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {

      }


    });

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

  private void populateButtons(RadioGroup radioGroup, List<McAnswers> answers) {
    Iterator<McAnswers> it = answers.iterator();

    Collections.shuffle(answers);

    for (int i = 0; i < radioGroup.getChildCount(); i++) {
      RadioButton button = (RadioButton) radioGroup.getChildAt(i);
      McAnswers answer = it.next();

      button.setText(answer.getAnswerText());
      button.setTag(answer.isCorrect());
    }

  }

}