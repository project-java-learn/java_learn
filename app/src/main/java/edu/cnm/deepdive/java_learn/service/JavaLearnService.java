package edu.cnm.deepdive.java_learn.service;

import edu.cnm.deepdive.java_learn.model.pojo.ProgressPojo;
import edu.cnm.deepdive.java_learn.model.pojo.UserPojo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JavaLearnService {

  @POST("users")
  Call<UserPojo> newUser(@Header ("Authorization") String authorization, @Body UserPojo user);

  @POST("users/{userId}/progress")
  Call<ProgressPojo> updateProgress(@Path ("userId") String userId, @Body ProgressPojo progress);

}
