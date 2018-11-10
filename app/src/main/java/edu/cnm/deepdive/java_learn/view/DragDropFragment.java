package edu.cnm.deepdive.java_learn.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.cnm.deepdive.java_learn.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DragDropFragment extends Fragment {


  public DragDropFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_drag_drop, container, false);
  }

}
