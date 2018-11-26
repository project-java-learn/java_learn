package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.HLAnswers;
import java.util.List;

@Dao
public interface HLAnswersDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(HLAnswers answer);

  @Query("SELECT * FROM HLAnswers WHERE hl_question_id = :hlQuestionId")
  List<HLAnswers> select(long hlQuestionId);
}
