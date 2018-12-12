package edu.cnm.deepdive.java_learn.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * This is the entity class that creates a HighlightAnswer object. Each
 * HighlightAnswer object is associated with one HighlightQuestion entity.
 */
@Entity(
    foreignKeys = {
        @ForeignKey(
            entity =HighlightQuestion.class,
            parentColumns = "hl_question_id",
            childColumns = "hl_question_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class HighlightAnswer {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "hl_answer_id")
  private long hlAnswerId;

  @ColumnInfo(name = "hl_question_id")
  private long hlQuestionId;

  @NonNull
  @ColumnInfo(name = "hl_answer")
  private String hlAnswer;

  @ColumnInfo(name = "type")
  private String type;

  /**
   * Cerates a HighlightAnswer object.
   * @param hlAnswer Text of the answer.
   * @param type Type of the answer to be highlighted.
   * @param hlQuestionId Id of the question this object is associated with.
   */
  public HighlightAnswer( @NonNull String hlAnswer, String type, long hlQuestionId) {
    this.hlQuestionId = hlQuestionId;
    this.hlAnswer = hlAnswer;
    this.type = type;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
