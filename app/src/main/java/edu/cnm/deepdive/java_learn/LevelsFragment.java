package edu.cnm.deepdive.java_learn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import edu.cnm.deepdive.java_learn.multipleChoice.MultipleChoice;


public class LevelsFragment extends Fragment {

  private Button level1a;

  @Override
  public View onCreateView(final LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_levels, container, false);
    /*level1a = view.findViewById(R.id.level_1_a);
    level1a.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        MultipleChoice mc = new MultipleChoice();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, mc)
            .addToBackStack("Levels").commit();
      }
    });*/
    return view;
  }
}
