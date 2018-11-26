package edu.cnm.deepdive.java_learn.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = FBQuestions.class,
            parentColumns = "fb_question_id",
            childColumns = "fb_question_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class FBAnswers {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "fb_answer_id")
  private long fbAnswerId;

  @ColumnInfo(name = "fb_question_id")
  private long fbQuestionId;

  @NonNull
  @ColumnInfo(name = "fb_answer")
  private String fbAnswer;

  @ColumnInfo(name = "is_correct")
  private boolean isCorrect;

  public FBAnswers() {

  }

  public long getFbAnswerId() {
    return fbAnswerId;
  }

  public void setFbAnswerId(long fbAnswerId) {
    this.fbAnswerId = fbAnswerId;
  }

  public long getFbQuestionId() {
    return fbQuestionId;
  }

  public void setFbQuestionId(long fbQuestionId) {
    this.fbQuestionId = fbQuestionId;
  }

  @NonNull
  public String getFbAnswer() {
    return fbAnswer;
  }

  public void setFbAnswer(@NonNull String fbAnswer) {
    this.fbAnswer = fbAnswer;
  }

  public boolean isCorrect() {
    return isCorrect;
  }

  public void setCorrect(boolean correct) {
    isCorrect = correct;
  }
}
