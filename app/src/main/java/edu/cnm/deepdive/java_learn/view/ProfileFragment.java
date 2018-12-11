package edu.cnm.deepdive.java_learn.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import edu.cnm.deepdive.java_learn.R;
import edu.cnm.deepdive.java_learn.model.pojo.ProgressPojo;
import edu.cnm.deepdive.java_learn.service.JavaLearnApplication;
import edu.cnm.deepdive.java_learn.service.JavaLearnService;
import java.io.IOException;
import java.util.List;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The type Profile fragment.
 */
public class ProfileFragment extends Fragment {

  @BindView(R.id.points_profile)
  TextView points;
  @BindView(R.id.username_profile)
  TextView username;
  @BindView(R.id.levels_complete)
  ListView levelsComplete;

  private Retrofit retrofit;
  private JavaLearnService service;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_profile, container, false);
    ButterKnife.bind(this, view);

    username.setText(JavaLearnApplication.getInstance().getAccount().getDisplayName());

    setupRetrofit();

    new GetUserProgress().execute();

    return view;
  }

  private void setupRetrofit() {
    retrofit = new Builder()
        .baseUrl(getString(R.string.base_url))
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    service = retrofit.create(JavaLearnService.class);
  }

  private class GetUserProgress extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {

      ProgressPojo pojo;
      String token = getString(R.string.authorization_header_format,
          JavaLearnApplication.getInstance().getAccount().getIdToken());
      try {
        Response<ProgressPojo> response = service.checkProgress(token).execute();
        if (response.isSuccessful()) {
          pojo = response.body();

          getActivity().runOnUiThread(() -> {
            points.setText(String.valueOf(pojo.getScore()));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.profile_level_text, R.id.levels_item, pojo.getLevels());

            levelsComplete.setAdapter(adapter);
          });
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      super.onPostExecute(aVoid);
    }
  }


}
