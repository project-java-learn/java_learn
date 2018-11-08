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
import java.util.ArrayList;
import java.util.List;

public class MultipleChoice extends Fragment {

  private RadioGroup radiosOne;
  private RadioGroup radiosTwo;
  private RadioGroup radiosThree;
  private RadioGroup radiosFour;
  private Button submitAnswers;
  private TextView question1;
  private TextView question2;
  private TextView question3;
  private TextView question4;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_multiple_choice, container, false);

    radiosOne = view.findViewById(R.id.radios_one);
    radiosTwo = view.findViewById(R.id.radios_two);
    radiosThree = view.findViewById(R.id.radios_three);
    radiosFour = view.findViewById(R.id.radios_four);
    submitAnswers = view.findViewById(R.id.submit_answers);

    McAnswers a1 = new McAnswers("This is incorrect.", false);
    McAnswers a2 = new McAnswers("This is incorrect", false);
    McAnswers a3 = new McAnswers("This is incorrect", false);
    McAnswers a4 = new McAnswers("This is correct", true);

    String correctAnswer = "This is correct";

    List<McAnswers> qAnswers = new ArrayList<>();

    qAnswers.add(a1);
    qAnswers.add(a2);
    qAnswers.add(a3);
    qAnswers.add(a4);

    McQuestions q = new McQuestions("This is question one.", qAnswers);

    question1 = view.findViewById(R.id.question_1);

    question1.setText(q.getQuestion());

    for (int i = 0; i < radiosOne.getChildCount(); i++) {
      ((RadioButton) radiosOne.getChildAt(i)).setText(q.getAnswers().get(i).getAnswer());
      ((RadioButton) radiosOne.getChildAt(i))
          .setTag(q.getAnswers().get(i).isCorrect() ? Boolean.TRUE : Boolean.FALSE);
      ((RadioButton) radiosTwo.getChildAt(i)).setText(q.getAnswers().get(i).getAnswer());
      ((RadioButton) radiosTwo.getChildAt(i))
          .setTag(q.getAnswers().get(i).isCorrect() ? Boolean.TRUE : Boolean.FALSE);
      ((RadioButton) radiosThree.getChildAt(i)).setText(q.getAnswers().get(i).getAnswer());
      ((RadioButton) radiosThree.getChildAt(i))
          .setTag(q.getAnswers().get(i).isCorrect() ? Boolean.TRUE : Boolean.FALSE);
      ((RadioButton) radiosFour.getChildAt(i)).setText(q.getAnswers().get(i).getAnswer());
      ((RadioButton) radiosFour.getChildAt(i))
          .setTag(q.getAnswers().get(i).isCorrect() ? Boolean.TRUE : Boolean.FALSE);
    }

    radiosOne.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {

      }
    });

     submitAnswers.setOnClickListener(new OnClickListener() {
       @Override
       public void onClick(View v) {
         RadioButton selected1 = getActivity().findViewById(radiosOne.getCheckedRadioButtonId());
         RadioButton selected2 = getActivity().findViewById(radiosOne.getCheckedRadioButtonId());
         RadioButton selected3 = getActivity().findViewById(radiosOne.getCheckedRadioButtonId());
         RadioButton selected4 = getActivity().findViewById(radiosOne.getCheckedRadioButtonId());
         boolean isCorrect = selected1.getTag() == Boolean.TRUE;
       }
     });
    return view;

  }
}
