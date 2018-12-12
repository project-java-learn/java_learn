package edu.cnm.deepdive.java_learn.model.pojo;

import java.util.List;

/**
 * This class creates an object that can be sent to the backend to post/patch
 * the progress of a user.
 */
public class ProgressPojo {

  private int score;

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
