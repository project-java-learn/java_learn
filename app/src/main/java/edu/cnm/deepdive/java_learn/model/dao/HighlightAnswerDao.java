package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.HighlightAnswer;
import java.util.List;

/**
 * HighlightAnswerDao interface accesses HighlightAnswer entities from the
 * database.
 */
@Dao
public interface HighlightAnswerDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(HighlightAnswer answer);

  @Query("SELECT * FROM HighlightAnswer WHERE hl_question_id = :hlQuestionId")
  List<HighlightAnswer> select(long hlQuestionId);
}
