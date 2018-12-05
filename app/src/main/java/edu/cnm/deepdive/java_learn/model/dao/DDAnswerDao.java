package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.DDAnswer;
import java.util.List;

/**
 * DDAnswerDao interface class allows the user to insert and query answers to/from the database.
 */
@Dao
public interface DDAnswerDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(DDAnswer answers);

  @Query("SELECT * FROM DDAnswer WHERE dd_question_id = :ddQuestionId")
  List<DDAnswer> select(long ddQuestionId);
}
