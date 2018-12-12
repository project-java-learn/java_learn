package edu.cnm.deepdive.java_learn.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * This is the entity class that creates a Level object.
 */
@Entity
public class Level {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "level_id")
  private long levelId;

  @NonNull
  @ColumnInfo(name = "level")
  private String level;

  /**
   * Creates a Level object.
   * @param level Name of the level.
   */
  public Level(@NonNull String level) {
    this.level = level;
  }

  public Level() {

  }

  public long getLevelId() {
    return levelId;
  }

  public void setLevelId(long levelId) {
    this.levelId = levelId;
  }

  @NonNull
  public String getLevel() {
    return level;
  }

  public void setLevel(@NonNull String level) {
    this.level = level;
  }
}
