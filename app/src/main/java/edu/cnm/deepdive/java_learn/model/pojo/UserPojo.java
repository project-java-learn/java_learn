package edu.cnm.deepdive.java_learn.model.pojo;

import com.google.gson.annotations.Expose;


/**
 * This class creates an object that can be sent to the backend to create a user
 * or check if this user already exists on the backend.
 */
public class UserPojo {

  @Expose
  private String username;

  @Expose(serialize = false)
  private String uuid;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
}
