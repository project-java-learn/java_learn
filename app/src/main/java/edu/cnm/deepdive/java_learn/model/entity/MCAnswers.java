package edu.cnm.deepdive.java_learn.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = MCQuestions.class,
            parentColumns = "level_id",
            childColumns = "level_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class MCAnswers {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "mc_answer_id")
  private long mcAnswerId;

  @ColumnInfo(name = "mc_question_id")
  private long mcQuestionId;

  @NonNull
  @ColumnInfo(name = "mc_answwer")
  private String mcAnswer;

  @ColumnInfo(name = "is_correct")
  private boolean isCorrect;

  public MCAnswers() {

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
