package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.FillBlankQuestion;
import java.util.List;

/**
 * FillBlankQuestionDao interface class allows the user to insert and query fill-in-the-blank questions.
 */
@Dao
public interface FillBlankQuestionDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(FillBlankQuestion question);

  @Query("SELECT * FROM FillBlankQuestion WHERE level_id = :levelId")
  List<FillBlankQuestion> select(long levelId);
}
