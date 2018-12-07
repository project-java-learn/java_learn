package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.MultipleChoiceQ;
import edu.cnm.deepdive.java_learn.model.pojo.MultipleChoiceQWithA;
import java.util.List;


/**
 * MCQuestionDao Interface class that allows the user to to insert and query questions from the
 * database.
 *
 */
@Dao
public interface MCQuestionDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(MultipleChoiceQ question);

  @Query("SELECT * FROM MultipleChoiceQ WHERE level_id =:levelId")
  List<MultipleChoiceQ> select(long levelId);

  @Query("SELECT * FROM MultipleChoiceQ WHERE level_id =:levelId LIMIT :limit")
  List<MultipleChoiceQWithA> selectWithAnswers(long levelId, int limit);
}
