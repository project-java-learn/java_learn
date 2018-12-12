package edu.cnm.deepdive.java_learn.service;

import edu.cnm.deepdive.java_learn.model.pojo.ProgressPojo;
import edu.cnm.deepdive.java_learn.model.pojo.UserPojo;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

/**
 * Service class for communication with the backend.
 */
public interface JavaLearnService {

  @GET("users")
  Call<List<UserPojo>> list(@Header("Authorization") String authorization);

  @GET("users/me")
  Call<UserPojo> checkUser(@Header("Authorization") String authorization);

  @GET("users/me/progress")
  Call<ProgressPojo> checkProgress(@Header("Authorization") String authorization);

  @POST("progress")
  Call<ProgressPojo> postProgress(@Header("Authorization") String authorization,
      @Body ProgressPojo progressPojo);

  @PATCH("progress")
  Call<ProgressPojo> updateProgress(@Header("Authorization") String authorization,
      @Body ProgressPojo progress);
}