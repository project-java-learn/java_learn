package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.DefinitionsQuestion;
import java.util.List;

/**
 * DefinitionsQuestionDao interface class allows the user to insert questions to and from the database.
 */
@Dao
public interface DefinitionsQuestionDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(DefinitionsQuestion question);

  @Query("SELECT * FROM DefinitionsQuestion WHERE level_id =:levelId")
  List<DefinitionsQuestion> select(long levelId);

  @Query("SELECT * FROM DefinitionsQuestion WHERE def_question = :ddQuestion")
  DefinitionsQuestion select(String ddQuestion);
}
