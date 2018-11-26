package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.HLQuestions;
import java.util.List;

@Dao
public interface HLQuestionsDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(HLQuestions question);

  @Query("SELECT * FROM HLQuestions WHERE level_id = :levelId")
  List<HLQuestions> select(long levelId);
}
