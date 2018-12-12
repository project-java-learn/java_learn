package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.DefinitionsAnswer;
import java.util.List;

/**
 * DefinitionsAnswerDao interface accesses DefinitionsAnswer entities from the
 * database.
 */
@Dao
public interface DefinitionsAnswerDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(DefinitionsAnswer answers);

  @Query("SELECT * FROM DefinitionsAnswer WHERE def_question_id = :ddQuestionId")
  List<DefinitionsAnswer> select(long ddQuestionId);
}
