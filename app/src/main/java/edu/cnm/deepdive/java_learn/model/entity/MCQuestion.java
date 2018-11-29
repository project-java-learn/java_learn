package edu.cnm.deepdive.java_learn.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
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
public class MCQuestion {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "mc_question_id")
  private long mcQuestionId;

  @ColumnInfo(name = "level_id")
  private long levelId;

  @NonNull
  @ColumnInfo(name = "mc_question")
  private String mcQuestion;

  public MCQuestion(@NonNull String mcQuestion, long levelId) {
    this.levelId = levelId;
    this.mcQuestion = mcQuestion;
  }

  public long getMcQuestionId() {
    return mcQuestionId;
  }

  public void setMcQuestionId(long mcQuestionId) {
    this.mcQuestionId = mcQuestionId;
  }

  public long getLevelId() {
    return levelId;
  }

  public void setLevelId(long levelId) {
    this.levelId = levelId;
  }

  @NonNull
  public String getMcQuestion() {
    return mcQuestion;
  }

  public void setMcQuestion(@NonNull String mcQuestion) {
    this.mcQuestion = mcQuestion;
  }
}
