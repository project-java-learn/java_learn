package edu.cnm.deepdive.java_learn.model.pojo;

import com.google.gson.annotations.Expose;
import java.util.List;

public class ProgressPojo {

  @Expose
  private int score;

  @Expose
  private List<String> levels;

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public List<String> getLevels() {
    return levels;
  }

  public void setLevels(List<String> levels) {
    this.levels = levels;
  }
}
