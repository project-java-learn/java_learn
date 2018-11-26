package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.FBAnswers;
import java.util.List;

@Dao
public interface FBAnswersDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(FBAnswers answer);

  @Query("SELECT * FROM FBAnswers WHERE fb_question_id = :fbQuestionId")
  List<FBAnswers> select(long fbQuestionId);
}
