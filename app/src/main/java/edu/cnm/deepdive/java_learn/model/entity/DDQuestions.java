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
public class DDQuestions {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "dd_question_id")
  private long ddQuestionId;

  @ColumnInfo(name = "level_id")
  private long levelId;

  @NonNull
  @ColumnInfo(name = "dd_question")
  private String ddQuestion;

  public DDQuestions() {

  }

  public long getDdQuestionId() {
    return ddQuestionId;
  }

  public void setDdQuestionId(long ddQuestionId) {
    this.ddQuestionId = ddQuestionId;
  }

  @NonNull
  public String getDdQuestion() {
    return ddQuestion;
  }

  public void setDdQuestion(@NonNull String DDQuestion) {
    this.ddQuestion = DDQuestion;
  }
}
