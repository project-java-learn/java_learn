package edu.cnm.deepdive.java_learn.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import edu.cnm.deepdive.java_learn.R;

public class ProfileFragment extends Fragment {

  private Button friendsButton;
  private Button badgesButton;
  private OnClickListener listener;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_profile, container, false);



    return view;
  }


}
