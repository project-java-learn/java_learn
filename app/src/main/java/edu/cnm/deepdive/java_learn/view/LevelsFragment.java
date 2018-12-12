package edu.cnm.deepdive.java_learn.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.cnm.deepdive.java_learn.R;
import edu.cnm.deepdive.java_learn.view.fillInBlank.FillBlankFragment;
import edu.cnm.deepdive.java_learn.view.highlight.HighlightFragment;
import edu.cnm.deepdive.java_learn.view.multipleChoice.MultipleChoiceFragment;

/**
 * LevelsFragment class allows the user to select between 4 different game levels HighLight, Drag
 * and Drop, Fill in the blank, and Multiple Choice.
 */
public class LevelsFragment extends Fragment {

  @BindView(R.id.highlight_card)
  CardView levelOneButton;
  @BindView(R.id.fill_in_card)
  CardView levelTwoButton;
  @BindView(R.id.definitions_card)
  CardView levelThreeButton;
  @BindView(R.id.multiple_choice_card)
  CardView levelFourButton;

  private OnClickListener listener;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_levels_cards, container, false);
    ButterKnife.bind(this, view);
    setListener();

    levelOneButton.setOnClickListener(listener);
    levelTwoButton.setOnClickListener(listener);
    levelThreeButton.setOnClickListener(listener);
    levelFourButton.setOnClickListener(listener);

    return view;
  }

  private void setListener() {
    final Fragment[] fragment = new Fragment[1];
    final String[] tag = {""};
    listener = (view) -> {
      switch (view.getId()) {
        case R.id.highlight_card:
          fragment[0] = new HighlightFragment();
          tag[0] = "Highlight Level";
          break;
        case R.id.fill_in_card:
          fragment[0] = new FillBlankFragment();
          tag[0] = "Fill In The Blank Level";
          break;
        case R.id.definitions_card:
          fragment[0] = new DefinitionsFragment();
          tag[0] = "Definitions Level";
          break;
        case R.id.multiple_choice_card:
          fragment[0] = new MultipleChoiceFragment();
          tag[0] = "Multiple Choice Level";
          break;
      }

      getFragmentManager()
          .beginTransaction().replace(R.id.fragment_container, fragment[0])
          .addToBackStack(tag[0])
          .commit();
    };
  }
}
