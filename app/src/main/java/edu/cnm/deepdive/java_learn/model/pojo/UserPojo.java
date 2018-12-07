package edu.cnm.deepdive.java_learn.model.pojo;

import com.google.gson.annotations.Expose;

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
