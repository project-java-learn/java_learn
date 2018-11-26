package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.MCAnswers;
import java.util.List;

@Dao
public interface MCAnswersDao {
  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(MCAnswers answer);

  @Query("SELECT * FROM MCAnswers WHERE mc_question_id = :mcQuestionId")
  List<MCAnswers> select(long mcQuestionId);
}
