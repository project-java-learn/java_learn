package edu.cnm.deepdive.java_learn.model.pojo;

import com.google.gson.annotations.Expose;

public class UserPojo {

  @Expose
  private String username;

  @Expose(serialize = false)
  private String uuid;

  @Expose
  private String oauthId;

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

  public String getOauthId() {
    return oauthId;
  }

  public void setOauthId(String oauthId) {
    this.oauthId = oauthId;
  }
}
