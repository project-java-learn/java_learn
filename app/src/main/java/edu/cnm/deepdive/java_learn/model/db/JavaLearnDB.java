package edu.cnm.deepdive.java_learn.model.db;

import android.arch.persistence.room.Database;
import edu.cnm.deepdive.java_learn.model.entity.User;

@Database(
    entities = {User.class},
    version = 1,
    exportSchema = true
)

public abstract class JavaLearnDB {

}
