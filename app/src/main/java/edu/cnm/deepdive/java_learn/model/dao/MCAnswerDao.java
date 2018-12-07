package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.MultipleChoiceA;
import java.util.List;

/**
 * MCAnswerDao Interface class that allows the user to insert and query answers from the database.
 */
@Dao
public interface MCAnswerDao {
  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(MultipleChoiceA answer);

  @Query("SELECT * FROM MultipleChoiceA WHERE mc_question_id = :mcQuestionId")
  List<MultipleChoiceA> select(long mcQuestionId);
}
