package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.DDAnswers;
import java.util.List;

@Dao
public interface DDAnswersDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(DDAnswers answers);

  @Query("SELECT * FROM DDAnswers WHERE dd_question_id = :ddQuestionId")
  List<DDAnswers> select(long ddQuestionId);
}
