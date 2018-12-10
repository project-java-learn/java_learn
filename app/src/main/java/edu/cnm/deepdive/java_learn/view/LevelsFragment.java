package edu.cnm.deepdive.java_learn.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import edu.cnm.deepdive.java_learn.R;
import edu.cnm.deepdive.java_learn.view.fillInBlank.FillBlankFragment;
import edu.cnm.deepdive.java_learn.view.highlight.HighlightFragment;
import edu.cnm.deepdive.java_learn.view.multipleChoice.MultipleChoice;
import edu.cnm.deepdive.java_learn.view.DefinitionsFragment;

/**
 * LevelsFragment class allows the user to select between 4 different game levels HighLight, Drag
 * and Drop, Fill in the blank, and Multiple Choice.
 */
public class LevelsFragment extends Fragment {

  private CardView levelOneButton;
  private CardView levelTwoButton;
  private CardView levelThreeButton;
  private CardView levelFourButton;

  @Override
  public View onCreateView(final LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout f
    // or this fragment
    View view = inflater.inflate(R.layout.fragment_levels_cards, container, false);
    levelOneButton = view.findViewById(R.id.highlight_card);
    levelOneButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        HighlightFragment hf = new HighlightFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, hf)
            .addToBackStack("Highlight Level").commit();
      }
    });
    levelTwoButton = view.findViewById(R.id.fill_in_card);
    levelTwoButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        FillBlankFragment fb = new FillBlankFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fb)
            .addToBackStack("Fill In The Blank Level").commit();
      }
    });
    levelThreeButton = view.findViewById(R.id.definitions_card);
    levelThreeButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        DefinitionsFragment df = new DefinitionsFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, df)
            .addToBackStack("Definitions Level").commit();
      }
    });
    levelFourButton = view.findViewById(R.id.multiple_choice_card);
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
