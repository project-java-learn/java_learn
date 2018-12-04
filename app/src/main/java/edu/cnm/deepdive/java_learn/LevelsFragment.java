package edu.cnm.deepdive.java_learn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import edu.cnm.deepdive.java_learn.fillInBlank.FillBlank;
import edu.cnm.deepdive.java_learn.highlight.HighlightFragment;
import edu.cnm.deepdive.java_learn.multipleChoice.MultipleChoice;
import edu.cnm.deepdive.java_learn.view.DefinitionsFragment;


public class LevelsFragment extends Fragment {

  private Button levelOneButton;
  private Button levelTwoButton;
  private Button levelThreeButton;
  private Button levelFourButton;

  @Override
  public View onCreateView(final LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_levels_cards, container, false);
    levelOneButton = view.findViewById(R.id.basic_level_one);
    levelOneButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        HighlightFragment hf = new HighlightFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, hf)
            .addToBackStack("Highlight Level").commit();
      }
    });
    levelTwoButton = view.findViewById(R.id.basic_level_two);
    levelTwoButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        FillBlank fb = new FillBlank();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fb)
            .addToBackStack("Fill In The Blank Level").commit();
      }
    });
    levelThreeButton = view.findViewById(R.id.basic_level_three);
    levelThreeButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        DefinitionsFragment df = new DefinitionsFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, df)
            .addToBackStack("Definitions Level").commit();
      }
    });
    levelFourButton = view.findViewById(R.id.basic_level_four);
    levelFourButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        MultipleChoice mc = new MultipleChoice();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, mc)
            .addToBackStack("Multiple Choice Level").commit();
      }
    });
    return view;
  }
}
