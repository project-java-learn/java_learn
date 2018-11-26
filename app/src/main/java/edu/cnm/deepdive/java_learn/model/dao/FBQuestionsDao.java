package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.FBQuestions;
import java.util.List;

@Dao
public interface FBQuestionsDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(FBQuestions question);

  @Query("SELECT * FROM FBQuestions WHERE level_id = :levelId")
  List<FBQuestions> select(long levelId);
}
