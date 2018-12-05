package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.FBQuestion;
import java.util.List;

/**
 * FBQuestionDao interface class allows the user to insert and query fill-in-the-blank questions.
 */
@Dao
public interface FBQuestionDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(FBQuestion question);

  @Query("SELECT * FROM FBQuestion WHERE level_id = :levelId")
  List<FBQuestion> select(long levelId);
}
