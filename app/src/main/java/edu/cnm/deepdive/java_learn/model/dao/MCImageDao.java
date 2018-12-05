//package edu.cnm.deepdive.java_learn.model.dao;
//
//import android.arch.persistence.room.Dao;
//import android.arch.persistence.room.Insert;
//import android.arch.persistence.room.OnConflictStrategy;
//import android.arch.persistence.room.Query;
//import edu.cnm.deepdive.java_learn.model.entity.MCImage;
//import java.util.List;
//
//@Dao
//public interface MCImageDao {
//
//  @Insert(onConflict = OnConflictStrategy.FAIL)
//  long insert(MCImage image);
//
//  @Query("SELECT * FROM MCImage WHERE level_id = :levelId")
//  List<MCImage> select(long levelId);
//}
