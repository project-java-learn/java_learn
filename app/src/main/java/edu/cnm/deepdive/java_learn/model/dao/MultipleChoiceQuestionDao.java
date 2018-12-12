package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.MultipleChoiceQuestion;
import edu.cnm.deepdive.java_learn.model.pojo.MultipleChoiceQWithA;
import java.util.List;


/**
 * MultipleChoiceQuestionDao Interface class that allows the user to to insert and query questions from the
 * database.
 *
 */
@Dao
public interface MultipleChoiceQuestionDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(MultipleChoiceQuestion question);

  @Query("SELECT * FROM MultipleChoiceQuestion WHERE level_id =:levelId")
  List<MultipleChoiceQuestion> select(long levelId);

  @Query("SELECT * FROM MultipleChoiceQuestion WHERE level_id =:levelId LIMIT :limit")
  List<MultipleChoiceQWithA> selectWithAnswers(long levelId, int limit);
}
