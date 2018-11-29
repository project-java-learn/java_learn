package edu.cnm.deepdive.java_learn.highlight;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.cnm.deepdive.java_learn.R;
import edu.cnm.deepdive.java_learn.view.GameFragment;


public class HighlightFragment extends GameFragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_highlight, container, false);
  }
}
