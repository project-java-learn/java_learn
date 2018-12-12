package edu.cnm.deepdive.java_learn.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * This is the entity class that creates a DefinitionsAnswer object. Each
 * DefinitionsAnswer object is associated with one DefinitionsQuestion entity.
 */
@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = DefinitionsQuestion.class,
            parentColumns = "def_question_id",
            childColumns = "def_question_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class DefinitionsAnswer {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "def_answer_id")
  private long defAnswerId;

  @ColumnInfo(name = "def_question_id")
  private long defQuestionId;

  @NonNull
  @ColumnInfo(name = "def_answer")
  private String defAnswer;

  @ColumnInfo(name = "is_correct")
  private boolean isCorrect;

  /**
   * Creates a DefinitionsAnswer object.
   * @param defAnswer Text of the answer.
   * @param isCorrect Determines if answer is correct. True if correct, false otherwise.
   * @param defQuestionId Id of question this oject is associated with.
   */
  public DefinitionsAnswer( @NonNull String defAnswer, boolean isCorrect, long defQuestionId) {
    this.defQuestionId = defQuestionId;
    this.defAnswer = defAnswer;
    this.isCorrect = isCorrect;
  }

  @NonNull
  @Override
  public String toString() {
    return getDefAnswer();
  }

  public long getDefAnswerId() {
    return defAnswerId;
  }

  public void setDefAnswerId(long defAnswerId) {
    this.defAnswerId = defAnswerId;
  }

  public long getDefQuestionId() {
    return defQuestionId;
  }

  public void setDefQuestionId(long defQuestionId) {
    this.defQuestionId = defQuestionId;
  }

  @NonNull
  public String getDefAnswer() {
    return defAnswer;
  }

  public void setDefAnswer(@NonNull String defAnswer) {
    this.defAnswer = defAnswer;
  }

  public boolean isCorrect() {
    return isCorrect;
  }

  public void setCorrect(boolean correct) {
    isCorrect = correct;
  }
}
