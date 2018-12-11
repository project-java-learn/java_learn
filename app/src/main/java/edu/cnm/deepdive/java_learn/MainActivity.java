package edu.cnm.deepdive.java_learn;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import edu.cnm.deepdive.java_learn.model.db.JavaLearnDB;
import edu.cnm.deepdive.java_learn.model.pojo.ProgressPojo;
import edu.cnm.deepdive.java_learn.model.pojo.UserPojo;
import edu.cnm.deepdive.java_learn.service.JavaLearnApplication;
import edu.cnm.deepdive.java_learn.service.JavaLearnService;
import edu.cnm.deepdive.java_learn.view.GameFragment;
import edu.cnm.deepdive.java_learn.view.HomeFragment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

  private FloatingActionButton fab;
  private Retrofit retrofit;
  private JavaLearnService service;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    new InitializeDatabaseTask().execute();

    setupRetrofit();

    new CheckBackEndTask().execute();

    HomeFragment homeFragment = new HomeFragment();
    fab = findViewById(R.id.fab);

    getSupportFragmentManager()
        .beginTransaction().replace(R.id.fragment_container, homeFragment).commit();

    fab.setOnClickListener(v -> {
      Fragment fragment = new HomeFragment();

      if (getSupportFragmentManager()
          .findFragmentById((R.id.fragment_container)) instanceof GameFragment) {
        AlertDialog.Builder builder = new Builder(this);
        builder.setTitle("Back to Home Page");
        builder.setTitle("Go back to home screen?");

        builder.setPositiveButton("Yes", (dialog, which) -> {
          getSupportFragmentManager()
              .popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);
          getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
              .commit();
        });

        builder.setNegativeButton("No", (dialog, which) -> {
        /*Nothing here; pressing "No" will go back to the game this was called
         on, which is the desired functionality.*/
        });
        builder.show();
      } else {
        getSupportFragmentManager().popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
            .commit();
      }
    });
  }

  private void setupRetrofit() {
    retrofit = new Retrofit.Builder()
        .baseUrl("http://10.0.2.2:28082/rest/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    service = retrofit.create(JavaLearnService.class);
  }

  /**
   * Google sign out.
   */
  public void googleSignOut() {
    JavaLearnApplication application = JavaLearnApplication.getInstance();
    application.getClient().signOut().addOnCompleteListener(this, (task) -> {
      application.setAccount(null);
      Intent intent = new Intent(this, LoginActivity.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
    });
  }

  private void testCheckUser() {
    UserPojo user = new UserPojo();
    String email = JavaLearnApplication.getInstance().getAccount().getEmail();
    user.setUsername(email);

    String token = getString(
        R.string.authorization_header_format,
        JavaLearnApplication.getInstance().getAccount().getIdToken());

    try {
      Response<UserPojo> response = service.checkUser(token).execute();
      if (response.isSuccessful()) {
        testPostProgress();
      } else {
        googleSignOut();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void testPostProgress() {
    ProgressPojo progress = new ProgressPojo();
    List<String> test = new ArrayList<>();
    progress.setScore(500);
    progress.setLevels(test);

    String token = getString(
        R.string.authorization_header_format,
        JavaLearnApplication.getInstance().getAccount().getIdToken());

    try {
      Response<ProgressPojo> response = service.postProgress(token, progress).execute();
      if (!response.isSuccessful()) {
        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void testUpdateProgress() {
    ProgressPojo pojo;
    String token = getString(R.string.authorization_header_format,
        JavaLearnApplication.getInstance().getAccount().getIdToken());
    try {
      Response<ProgressPojo> response = service.checkProgress(token).execute();
      if (response.isSuccessful()) {
        pojo = response.body();
        List<String> moreLevels = pojo.getLevels();
        int score = pojo.getScore();
        moreLevels.add("check check");
        score += 250;

        pojo.setLevels(moreLevels);
        pojo.setScore(score);

        Response response1 = service.updateProgress(token, pojo).execute();
        if (!response1.isSuccessful()) {
          Toast.makeText(MainActivity.this, "Update progress failed", Toast.LENGTH_LONG).show();
        }

      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private class InitializeDatabaseTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
      JavaLearnDB.getInstance(MainActivity.this).getLevelDao().select("");
      return null;
    }
  }

  private class CheckBackEndTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
      testCheckUser();
      return null;
    }
  }
}

