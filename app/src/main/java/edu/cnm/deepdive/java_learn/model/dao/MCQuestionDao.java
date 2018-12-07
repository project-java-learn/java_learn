package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.MCQuestion;
import edu.cnm.deepdive.java_learn.model.pojo.MCQuestionWithAnswers;
import java.util.List;


/**
 * MCQuestionDao Interface class that allows the user to to insert and query questions from the
 * database.
 *
 */
@Dao
public interface MCQuestionDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(MCQuestion question);

  @Query("SELECT * FROM MCQuestion WHERE level_id =:levelId")
  List<MCQuestion> select(long levelId);

  @Query("SELECT * FROM MCQuestion WHERE level_id =:levelId LIMIT :limit")
  List<MCQuestionWithAnswers> selectWithAnswers(long levelId, int limit);
}
