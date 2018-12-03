package edu.cnm.deepdive.java_learn.view;

//import android.os.Bundle;
import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.Button;
//import edu.cnm.deepdive.java_learn.R;

public class ProfileFragment extends Fragment {

//  private Button friendsButton;
//  private Button badgesButton;
//  private OnClickListener listener;
//
//  @Override
//  public View onCreateView(LayoutInflater inflater, ViewGroup container,
//      Bundle savedInstanceState) {
//
//    View view = inflater.inflate(R.layout.fragment_profile, container, false);
//
//
//    setupListener();
//
//    friendsButton.setOnClickListener(listener);
//    badgesButton.setOnClickListener(listener);
//
//    return view;
//  }
//
//  private void setupListener() {
//
//    listener = new OnClickListener() {
//      @Override
//      public void onClick(View v) {
//
//        int id = v.getId();
//        Fragment fragment;
//
//        switch (id) {
//          case R.id.friends_button:
//            fragment = new FriendsFragment();
//            break;
//          case R.id.badges_button:
//            fragment = new BadgesFragment();
//            break;
//          default:
//            fragment = new BadgesFragment();
//        }
//
//        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
//            .addToBackStack("profile").commit();
//      }
//    };
//  }
}
