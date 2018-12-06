package edu.cnm.deepdive.java_learn;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import edu.cnm.deepdive.java_learn.view.HomeFragment;

/**
 * MainActivity class allows the user to select levels and games they want to interact with.
 */
public class MainActivity extends AppCompatActivity {

  private FloatingActionButton fab;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    HomeFragment homeFragment = new HomeFragment();
    fab = findViewById(R.id.fab);

    getSupportFragmentManager()
        .beginTransaction().replace(R.id.fragment_container, homeFragment).commit();

    fab.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Fragment fragment = new HomeFragment();
        getSupportFragmentManager().popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
            .commit();
      }
    });
  }



}

