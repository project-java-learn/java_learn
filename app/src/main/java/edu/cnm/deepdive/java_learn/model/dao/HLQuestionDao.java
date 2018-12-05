package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.HLQuestion;
import java.util.List;

/**
 * HLQuestionDao interface class that will allow the user to insert to the database
 * and query questions from the database.
 */
@Dao
public interface HLQuestionDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(HLQuestion question);

  @Query("SELECT * FROM HLQuestion WHERE level_id = :levelId")
  List<HLQuestion> select(long levelId);
}
