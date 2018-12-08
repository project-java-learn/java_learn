package edu.cnm.deepdive.java_learn.service;

import edu.cnm.deepdive.java_learn.model.pojo.ProgressPojo;
import edu.cnm.deepdive.java_learn.model.pojo.UserPojo;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

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

  @GET("user/{userId}/progress/{progressId}/score")
  Call<ProgressPojo> getScore(@Header("Authorization") String authorization,
      @Body ProgressPojo progress);

  @GET("user/{userId}/progress/{progressId}/levels")
  Call<ProgressPojo> getLevels(@Header("Authorization") String authorization,
      @Body ProgressPojo progress);

  @POST("user/{userId}/progress/{progressId}/score")
  Call<ProgressPojo> updateScore(@Header("Authorization") String authorization,
      @Body ProgressPojo progress);

  @POST("user/{userId}/progress/{progressId}/levels")
  Call<ProgressPojo> updateLevels(@Header("Authorization") String authorization,
      @Body ProgressPojo progress);

  @POST("users")
  Call<UserPojo> newUser(@Header("Authorization") String authorization, @Body UserPojo user);

  @POST("users/{userId}/progress")
  Call<ProgressPojo> updateProgress(@Header("Authorization") String authorization,
      @Body ProgressPojo progress);


}