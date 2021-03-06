package edu.cnm.deepdive.java_learn.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * This is the entity class that creates a HighlightQuestion object. Each
 * HighlightQuestion object is associated with one Level entity.
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
public class HighlightQuestion {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "hl_question_id")
  private long hlQuestionId;

  @ColumnInfo(name = "level_id")
  private long levelId;

  @NonNull
  @ColumnInfo(name = "hl_question")
  private String hlQuestion;

  /**
   * Creates a HighlightQuestion object.
   * @param hlQuestion Text of the question.
   * @param levelId Id of the level this object is associated with.
   */
  public HighlightQuestion(@NonNull String hlQuestion, long levelId) {
    this.levelId = levelId;
    this.hlQuestion = hlQuestion;
  }

  public long getHlQuestionId() {
    return hlQuestionId;
  }

  public void setHlQuestionId(long hlQuestionId) {
    this.hlQuestionId = hlQuestionId;
  }

  public long getLevelId() {
    return levelId;
  }

  public void setLevelId(long levelId) {
    this.levelId = levelId;
  }

  @NonNull
  public String getHlQuestion() {
    return hlQuestion;
  }

  public void setHlQuestion(@NonNull String hlQuestion) {
    this.hlQuestion = hlQuestion;
  }
}
