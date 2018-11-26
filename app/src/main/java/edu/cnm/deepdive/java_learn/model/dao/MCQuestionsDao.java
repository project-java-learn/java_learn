package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.MCQuestions;
import java.util.List;

@Dao
public interface MCQuestionsDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(MCQuestions question);

  @Query("SELECT * FROM MCQuestions WHERE level_id =:levelId")
  List<MCQuestions> select(long levelId);
}
