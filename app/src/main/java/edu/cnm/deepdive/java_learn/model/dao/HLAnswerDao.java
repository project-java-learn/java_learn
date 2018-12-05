package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.HLAnswer;
import java.util.List;

/**
 * HLAnswerDao interface class allows the user to insert and query answers that can be highlighted.
 */
@Dao
public interface HLAnswerDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(HLAnswer answer);

  @Query("SELECT * FROM HLAnswer WHERE hl_question_id = :hlQuestionId")
  List<HLAnswer> select(long hlQuestionId);
}
