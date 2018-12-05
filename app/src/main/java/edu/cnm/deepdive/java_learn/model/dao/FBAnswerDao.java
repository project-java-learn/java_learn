package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.FBAnswer;
import java.util.List;

/**
 * FBAnswerDao interface class allows the user to insert answers into the database and query them.
 */
@Dao
public interface FBAnswerDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(FBAnswer answer);

  @Query("SELECT * FROM FBAnswer WHERE fb_question_id = :fbQuestionId")
  List<FBAnswer> select(long fbQuestionId);
}
