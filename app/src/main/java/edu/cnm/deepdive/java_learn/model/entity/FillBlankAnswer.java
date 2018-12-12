package edu.cnm.deepdive.java_learn.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * This is the entity class that creates a FillBlankAnswer object. Each
 * FillBlankAnswer object is associated with one FillBlankQuestion entity.
 */
@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = FillBlankQuestion.class,
            parentColumns = "fb_question_id",
            childColumns = "fb_question_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class FillBlankAnswer {

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

  /**
   * Creates a FillBlankAnswer object.
   * @param fbAnswer Text of the answer.
   * @param isCorrect Determines if answer is correct. True if correct, false otherwise.
   * @param fbQuestionId Id of question this oject is associated with.
   */
  public FillBlankAnswer(@NonNull String fbAnswer, boolean isCorrect, long fbQuestionId) {
    this.fbQuestionId = fbQuestionId;
    this.fbAnswer = fbAnswer;
    this.isCorrect = isCorrect;
  }

  @Override
  public String toString() {
    return getFbAnswer();
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
