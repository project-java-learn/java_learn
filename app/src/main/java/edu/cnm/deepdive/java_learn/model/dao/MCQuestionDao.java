package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.MCQuestion;
import java.util.List;

@Dao
public interface MCQuestionDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(MCQuestion question);

  @Query("SELECT * FROM MCQuestion WHERE level_id =:levelId")
  List<MCQuestion> select(long levelId);
}
