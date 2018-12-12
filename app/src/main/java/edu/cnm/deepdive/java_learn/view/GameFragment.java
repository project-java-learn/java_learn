package edu.cnm.deepdive.java_learn.view;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import edu.cnm.deepdive.java_learn.R;
import edu.cnm.deepdive.java_learn.model.pojo.ProgressPojo;
import edu.cnm.deepdive.java_learn.service.JavaLearnApplication;
import edu.cnm.deepdive.java_learn.service.JavaLearnService;
import java.io.IOException;
import java.util.List;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class is used to give a common super class to all of the game types for
 * modularity in the code. It also holds the logic to add progress to the user
 * on the backend.
 */
public abstract class GameFragment extends Fragment {

  private Retrofit retrofit;
  private JavaLearnService service;

  /**
   * Method makes a call to the backend to update progress for a user.
   * @param levelComplete Name of the level completed.
   */
  protected void updateProgress(String levelComplete) {
    new UpdateProgressTask().execute(levelComplete);
  }

  private void setupRetrofit() {
    retrofit = new Retrofit.Builder()
        .baseUrl(getString(R.string.base_url))
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    service = retrofit.create(JavaLearnService.class);
  }

  private class UpdateProgressTask extends AsyncTask<String, Void, Void> {

    @Override
    protected void onPreExecute() {
      setupRetrofit();
    }

    @Override
    protected Void doInBackground(String... strings) {
      ProgressPojo pojo;
      String token = getString(R.string.authorization_header_format,
          JavaLearnApplication.getInstance().getAccount().getIdToken());
      try {
        Response<ProgressPojo> response = service.checkProgress(token).execute();
        if (response.isSuccessful()) {
          pojo = response.body();
          List<String> moreLevels = pojo.getLevels();
          int score = pojo.getScore();
          moreLevels.add(strings[0]);
          score += 250;

          pojo.setLevels(moreLevels);
          pojo.setScore(score);

          service.updateProgress(token, pojo).execute();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

      return null;
    }
  }

}
