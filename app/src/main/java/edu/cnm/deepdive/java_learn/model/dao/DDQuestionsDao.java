package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.DDQuestions;
import java.util.List;

@Dao
public interface DDQuestionsDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(DDQuestions question);

  @Query("SELECT * FROM DDQuestions WHERE level_id =:levelId")
  List<DDQuestions> select(long levelId);
}
