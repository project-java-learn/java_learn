package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.DDQuestion;
import java.util.List;

@Dao
public interface DDQuestionDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(DDQuestion question);

  @Query("SELECT * FROM DDQuestion WHERE level_id =:levelId")
  List<DDQuestion> select(long levelId);
}
