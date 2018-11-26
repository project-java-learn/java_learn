package edu.cnm.deepdive.java_learn.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.media.Image;
import android.support.annotation.NonNull;

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
public class FBQuestions {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "fb_question_id")
  private long fbQuestionId;

  @ColumnInfo(name = "level_id")
  private long levelId;

  @NonNull
  @ColumnInfo(name = "fb_question")
  private Image fbQuestion;

  public FBQuestions(@NonNull Image fbQuestion, long levelId) {
    this.levelId = levelId;
    this.fbQuestion = fbQuestion;
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

  @NonNull
  public Image getFbQuestion() {
    return fbQuestion;
  }

  public void setFbQuestion(@NonNull Image fbQuestion) {
    this.fbQuestion = fbQuestion;
  }
}
