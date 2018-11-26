package edu.cnm.deepdive.java_learn.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.java_learn.model.entity.Level;
import java.util.List;

@Dao
public interface LevelDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(Level level);

  @Query("SELECT * FROM Level ORDER BY level_id")
  List<Level> select();

  @Query("SELECT * FROM Level WHERE level =:level")
  Level select(String level);
}
