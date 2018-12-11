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

public abstract class GameFragment extends Fragment {

  private Retrofit retrofit;
  private JavaLearnService service;

  protected void updateProgress(String levelComplete) {
    new UpdateProgressTask().execute(levelComplete);
  }

  private void setupRetrofit() {
    retrofit = new Retrofit.Builder()
        .baseUrl("http://10.0.2.2:28082/rest/")
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
