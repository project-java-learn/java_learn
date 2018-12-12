package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.MultipleChoiceAnswer;
import java.util.List;

/**
 * MultipleChoiceAnswerDao interface accesses MultipleChoiceAnswer entities from
 * the database.
 */
@Dao
public interface MultipleChoiceAnswerDao {
  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(MultipleChoiceAnswer answer);

  @Query("SELECT * FROM MultipleChoiceAnswer WHERE mc_question_id = :mcQuestionId")
  List<MultipleChoiceAnswer> select(long mcQuestionId);
}
