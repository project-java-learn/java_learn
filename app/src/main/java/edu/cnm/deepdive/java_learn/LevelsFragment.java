package edu.cnm.deepdive.java_learn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import edu.cnm.deepdive.java_learn.model.entity.Level;
import edu.cnm.deepdive.java_learn.multipleChoice.MultipleChoice;
import edu.cnm.deepdive.java_learn.view.DefinitionsFragment;


public class LevelsFragment extends Fragment {

  private Button levelButton;

  @Override
  public View onCreateView(final LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_levels, container, false);

    levelButton = view.findViewById(R.id.test_btn);
    levelButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

        getFragmentManager().beginTransaction().replace(R.id.fragment_container,
            new DefinitionsFragment()).commit();
      }
    });

    return view;
  }
}
