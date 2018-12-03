package edu.cnm.deepdive.java_learn.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import edu.cnm.deepdive.java_learn.LevelsFragment;
import edu.cnm.deepdive.java_learn.R;

public class HomeFragment extends Fragment {

  private Button levelsButton;
  private Button profileButton;
  private OnClickListener listener;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_home, container, false);

    levelsButton = view.findViewById(R.id.levels_button);
    profileButton = view.findViewById(R.id.profile_button);


    setupListener();

    levelsButton.setOnClickListener(listener);
    profileButton.setOnClickListener(listener);

    return view;
  }


  private void setupListener() {
    listener = new OnClickListener() {
      @Override
      public void onClick(View v) {

        int id = v.getId();
        Fragment fragment;

        switch (id) {
          case R.id.levels_button:
            fragment = new LevelsFragment();
            break;
          case R.id.profile_button:
            fragment = new ProfileFragment();
            break;
          default:
            fragment = new LevelsFragment();
        }

        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
            .addToBackStack("home").commit();
      }
    };
  }

}
