package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.HighlightQuestion;
import java.util.List;

/**
 * HighlightQuestionDao interface class that will allow the user to insert to the database
 * and query questions from the database.
 */
@Dao
public interface HighlightQuestionDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(HighlightQuestion question);

  @Query("SELECT * FROM HighlightQuestion WHERE level_id = :levelId")
  List<HighlightQuestion> select(long levelId);

  @Query("SELECT * FROM HighlightQuestion WHERE hl_question = :hlQuestion")
  HighlightQuestion select(String hlQuestion);
}
