package edu.cnm.deepdive.java_learn.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * This is the entity class that creates a DefinitionsQuestion object. Each
 * DefinitionsQuestion object is associated with one Level entity.
 */
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
public class DefinitionsQuestion {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "def_question_id")
  private long defQuestionId;

  @ColumnInfo(name = "level_id")
  private long levelId;

  @NonNull
  @ColumnInfo(name = "def_question")
  private String defQuestion;

  /**
   * Creates a DefinitionsQuestion object.
   * @param defQuestion Text of the question.
   * @param levelId Id of level the object is associated with.
   */
  public DefinitionsQuestion(@NonNull String defQuestion, long levelId) {
    this.defQuestion = defQuestion;
    this.levelId = levelId;
  }

  public long getDefQuestionId() {
    return defQuestionId;
  }

  public void setDefQuestionId(long defQuestionId) {
    this.defQuestionId = defQuestionId;
  }

  public long getLevelId() {
    return levelId;
  }

  public void setLevelId(long levelId) {
    this.levelId = levelId;
  }

  @NonNull
  public String getDefQuestion() {
    return defQuestion;
  }

  public void setDefQuestion(@NonNull String DDQuestion) {
    this.defQuestion = DDQuestion;
  }
}
