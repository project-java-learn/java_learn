package edu.cnm.deepdive.java_learn;

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
import edu.cnm.deepdive.java_learn.model.pojo.UserPojo;
import edu.cnm.deepdive.java_learn.service.JavaLearnApplication;
import edu.cnm.deepdive.java_learn.service.JavaLearnService;
import edu.cnm.deepdive.java_learn.view.GameFragment;
import edu.cnm.deepdive.java_learn.view.HomeFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * MainActivity class allows the user to select levels and games they want to interact with.
 */
public class MainActivity extends AppCompatActivity {

  private FloatingActionButton fab;
  private Retrofit retrofit;
  private JavaLearnService service;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

//    String email = JavaLearnApplication.getInstance().getAccount().getEmail();
//
//    setupRetrofit();
//
//    UserPojo user = new UserPojo();
//    user.setUsername(email);
//
//    service.newUser(user).enqueue(new Callback<UserPojo>() {
//      @Override
//      public void onResponse(Call<UserPojo> call, Response<UserPojo> response) {
//        if(response.isSuccessful()) {
//
//        }
//      }
//
//      @Override
//      public void onFailure(Call<UserPojo> call, Throwable t) {
//        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
//        throw new RuntimeException(t);
//      }
//    });

    HomeFragment homeFragment = new HomeFragment();
    fab = findViewById(R.id.fab);

    getSupportFragmentManager()
        .beginTransaction().replace(R.id.fragment_container, homeFragment).commit();

    fab.setOnClickListener(v -> {
      Fragment fragment = new HomeFragment();

      if (getSupportFragmentManager()
          .findFragmentById((R.id.fragment_container)) instanceof GameFragment){
        AlertDialog.Builder builder = new Builder(this);
        builder.setTitle("Back to Home Page");
        builder.setTitle("Go back to home screen?");

        builder.setPositiveButton("Yes", (dialog, which) -> {
          getSupportFragmentManager().popBackStack("home", FragmentManager.POP_BACK_STACK_INCLUSIVE);
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

  private class InitializeDatabaseTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
      JavaLearnDB.getInstance(MainActivity.this).getLevelDao().select("");
      return null;
    }
  }


}

