package edu.cnm.deepdive.java_learn.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(
    foreignKeys = {
        @ForeignKey(
            entity =HLQuestions.class,
            parentColumns = "hl_question_id",
            childColumns = "hl_question_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class HLAnswers {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "hl_answer_id")
  private long hlAnswerId;

  @ColumnInfo(name = "hl_question_id")
  private long hlQuestionId;

  @NonNull
  @ColumnInfo(name = "hl_answer")
  private String hlAnswer;

  @ColumnInfo(name = "is_correct")
  private boolean isCorrect;

  public HLAnswers() {

  }

  public long getHlAnswerId() {
    return hlAnswerId;
  }

  public void setHlAnswerId(long hlAnswerId) {
    this.hlAnswerId = hlAnswerId;
  }

  public long getHlQuestionId() {
    return hlQuestionId;
  }

  public void setHlQuestionId(long hlQuestionId) {
    this.hlQuestionId = hlQuestionId;
  }

  @NonNull
  public String getHlAnswer() {
    return hlAnswer;
  }

  public void setHlAnswer(@NonNull String hlAnswer) {
    this.hlAnswer = hlAnswer;
  }

  public boolean isCorrect() {
    return isCorrect;
  }

  public void setCorrect(boolean correct) {
    isCorrect = correct;
  }
}
