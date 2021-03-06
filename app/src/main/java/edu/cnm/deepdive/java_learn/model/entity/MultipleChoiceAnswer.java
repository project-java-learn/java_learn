package edu.cnm.deepdive.java_learn.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * This is the entity class that creates a MultipleChoiceAnswer object. Each
 * MultipleChoiceAnswer object is associated with one MultipleChoiceQuestion
 * entity.
 */
@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = MultipleChoiceQuestion.class,
            parentColumns = "mc_question_id",
            childColumns = "mc_question_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class MultipleChoiceAnswer {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "mc_answer_id")
  private long mcAnswerId;

  @ColumnInfo(name = "mc_question_id")
  private long mcQuestionId;

  @NonNull
  @ColumnInfo(name = "mc_answer")
  private String mcAnswer;

  @ColumnInfo(name = "is_correct")
  private boolean isCorrect;

  /**
   * Creates a MultipleChoiceAnswer object.
   * @param mcAnswer Text of the answer.
   * @param isCorrect Determines if answer is correct. True if correct, false otherwise.
   * @param mcQuestionId Id of the question this object is associated with.
   */
  public MultipleChoiceAnswer(@NonNull String mcAnswer, boolean isCorrect, long mcQuestionId) {
    this.mcQuestionId = mcQuestionId;
    this.mcAnswer = mcAnswer;
    this.isCorrect = isCorrect;
  }

  public long getMcAnswerId() {
    return mcAnswerId;
  }

  public void setMcAnswerId(long mcAnswerId) {
    this.mcAnswerId = mcAnswerId;
  }

  public long getMcQuestionId() {
    return mcQuestionId;
  }

  public void setMcQuestionId(long mcQuestionId) {
    this.mcQuestionId = mcQuestionId;
  }

  @NonNull
  public String getMcAnswer() {
    return mcAnswer;
  }

  public void setMcAnswer(@NonNull String mcAnswer) {
    this.mcAnswer = mcAnswer;
  }

  public boolean isCorrect() {
    return isCorrect;
  }

  public void setCorrect(boolean correct) {
    isCorrect = correct;
  }
}
