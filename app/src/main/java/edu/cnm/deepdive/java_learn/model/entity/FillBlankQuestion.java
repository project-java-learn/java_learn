package edu.cnm.deepdive.java_learn.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.media.Image;
import android.support.annotation.NonNull;

/**
 * This is the entity class that creates a FillBlankQuestion object. Each
 * FillBlankQuestion object is associated with one Level entity.
 */
@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = Level.class,
            parentColumns = "level_id",
            childColumns = "level_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class FillBlankQuestion {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "fb_question_id")
  private long fbQuestionId;

  @ColumnInfo(name = "level_id")
  private long levelId;

  /**
   * Creates a FillBlankQuestion object.
   * @param levelId Id of level this object is associated with.
   */
  public FillBlankQuestion(long levelId) {
    this.levelId = levelId;
  }

  public long getFbQuestionId() {
    return fbQuestionId;
  }

  public void setFbQuestionId(long fbQuestionId) {
    this.fbQuestionId = fbQuestionId;
  }

  public long getLevelId() {
    return levelId;
  }

  public void setLevelId(long levelId) {
    this.levelId = levelId;
  }

}
