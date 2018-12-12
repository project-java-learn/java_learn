package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.FillBlankAnswer;
import java.util.List;

/**
 * FillBlankAnswerDao interface class allows the user to insert answers into the database and query them.
 */
@Dao
public interface FillBlankAnswerDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(FillBlankAnswer answer);

  @Query("SELECT * FROM FillBlankAnswer WHERE fb_question_id = :fbQuestionId")
  List<FillBlankAnswer> select(long fbQuestionId);
}
