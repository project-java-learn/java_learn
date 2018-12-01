package edu.cnm.deepdive.java_learn.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = DDQuestion.class,
            parentColumns = "dd_question_id",
            childColumns = "dd_question_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class DDAnswer {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "dd_answer_id")
  private long ddAnswerId;

  @ColumnInfo(name = "dd_question_id")
  private long ddQuestionId;

  @NonNull
  @ColumnInfo(name = "dd_answer")
  private String ddAnswer;

  @ColumnInfo(name = "is_correct")
  private boolean isCorrect;

  public DDAnswer( @NonNull String ddAnswer, boolean isCorrect, long ddQuestionId) {
    this.ddQuestionId = ddQuestionId;
    this.ddAnswer = ddAnswer;
    this.isCorrect = isCorrect;
  }

  @NonNull
  @Override
  public String toString() {
    return ddAnswer;
  }

  public long getDdAnswerId() {
    return ddAnswerId;
  }

  public void setDdAnswerId(long ddAnswerId) {
    this.ddAnswerId = ddAnswerId;
  }

  public long getDdQuestionId() {
    return ddQuestionId;
  }

  public void setDdQuestionId(long ddQuestionId) {
    this.ddQuestionId = ddQuestionId;
  }

  @NonNull
  public String getDdAnswer() {
    return ddAnswer;
  }

  public void setDdAnswer(@NonNull String ddAnswer) {
    this.ddAnswer = ddAnswer;
  }

  public boolean isCorrect() {
    return isCorrect;
  }

  public void setCorrect(boolean correct) {
    isCorrect = correct;
  }
}
