package edu.cnm.deepdive.java_learn.model.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import edu.cnm.deepdive.java_learn.model.dao.DDAnswerDao;
import edu.cnm.deepdive.java_learn.model.dao.DDQuestionDao;
import edu.cnm.deepdive.java_learn.model.dao.FBAnswerDao;
import edu.cnm.deepdive.java_learn.model.dao.FBQuestionDao;
import edu.cnm.deepdive.java_learn.model.dao.HLAnswerDao;
import edu.cnm.deepdive.java_learn.model.dao.HLQuestionDao;
import edu.cnm.deepdive.java_learn.model.dao.LevelDao;
import edu.cnm.deepdive.java_learn.model.dao.MCAnswerDao;
import edu.cnm.deepdive.java_learn.model.dao.MCQuestionDao;
import edu.cnm.deepdive.java_learn.model.entity.DDAnswer;
import edu.cnm.deepdive.java_learn.model.entity.DDQuestion;
import edu.cnm.deepdive.java_learn.model.entity.FBAnswer;
import edu.cnm.deepdive.java_learn.model.entity.FBQuestion;
import edu.cnm.deepdive.java_learn.model.entity.HLAnswer;
import edu.cnm.deepdive.java_learn.model.entity.HLQuestion;
import edu.cnm.deepdive.java_learn.model.entity.Level;
import edu.cnm.deepdive.java_learn.model.entity.MCAnswer;
import edu.cnm.deepdive.java_learn.model.entity.MCQuestion;

/**
 * JavaLearnDB class allows the user to create a database for holding user levels, game progress
 * and holds the questions and answers for Drag and Drop, HighLight, Fill-in-the-Blank, and Multiple
 * Choice.
 */
@Database(
    entities = {Level.class, DDAnswer.class, DDQuestion.class, FBAnswer.class, FBQuestion.class,
        HLAnswer.class, HLQuestion.class, MCAnswer.class, MCQuestion.class},
    version = 1,
    exportSchema = true
)
public abstract class JavaLearnDB extends RoomDatabase {

  private static final String DATABASE_NAME = "java_learn_db";

  private static JavaLearnDB instance = null;

  public static synchronized JavaLearnDB getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context.getApplicationContext(),
          JavaLearnDB.class,
          DATABASE_NAME)
          .addCallback(new Callback(context.getApplicationContext()))
          .build();
    }
    return instance;
  }

  private static synchronized void forgetInstance(Context context) {
    instance = null;
  }


  public abstract LevelDao getLevelDao();


  public abstract DDQuestionDao getDDQuestionDao();


  public abstract DDAnswerDao getDDAnswerDao();


  public abstract FBQuestionDao getFBQuestionDao();


  public abstract FBAnswerDao getFBAnswerDao();


  public abstract HLQuestionDao getHLQuestionDao();


  public abstract HLAnswerDao getHLAnswerDao();

  public abstract MCQuestionDao getMCQuestionDao();

  public abstract MCAnswerDao getMCAnswerDao();

//  public abstract MCImageDao getMCImageDao();


  private static class Callback extends RoomDatabase.Callback {

    private Context context;

    Callback(Context context) {
      this.context = context;
    }

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      new PrepopulateTask(context).execute();
    }

    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
      super.onOpen(db);
    }
  }

  private static class PrepopulateTask extends AsyncTask<Void, Void, Void> {

    private Context context;

    PrepopulateTask(Context context) {
      this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
      JavaLearnDB db = getInstance(context);
      LevelDao lDao = db.getLevelDao();
      DDQuestionDao qDao = db.getDDQuestionDao();
      DDAnswerDao aDao = db.getDDAnswerDao();

      long levId = lDao.insert(new Level("Definitions Test"));

      long que1Id = qDao.insert(new DDQuestion("A function defined in a class.", levId));
      aDao.insert(new DDAnswer("Method", true, que1Id));
      aDao.insert(new DDAnswer("Class", false, que1Id));
      aDao.insert(new DDAnswer("Object", false, que1Id));
      aDao.insert(new DDAnswer("Field", false, que1Id));
      aDao.insert(new DDAnswer("Abstract", false, que1Id));

      long que2Id = qDao.insert(new DDQuestion("A type that defines the implementation of a "
          + "particular kind of object.", levId));

      aDao.insert(new DDAnswer("Method", false, que2Id));
      aDao.insert(new DDAnswer("Class", true, que2Id));
      aDao.insert(new DDAnswer("Object", false, que2Id));
      aDao.insert(new DDAnswer("Field", false, que2Id));
      aDao.insert(new DDAnswer("Abstract", false, que2Id));

      long que3Id = qDao.insert(new DDQuestion("The principal building blocks of object-oriented "
          + "programs", levId));
      aDao.insert(new DDAnswer("Method", false, que3Id));
      aDao.insert(new DDAnswer("Class", false, que3Id));
      aDao.insert(new DDAnswer("Object", true, que3Id));
      aDao.insert(new DDAnswer("Field", false, que3Id));
      aDao.insert(new DDAnswer("Abstract", false, que3Id));

      long que4Id = qDao.insert(new DDQuestion("A data member of a class.", levId));
      aDao.insert(new DDAnswer("Method", false, que4Id));
      aDao.insert(new DDAnswer("Class", false, que4Id));
      aDao.insert(new DDAnswer("Object", false, que4Id));
      aDao.insert(new DDAnswer("Field", true, que4Id));
      aDao.insert(new DDAnswer("Abstract", false, que4Id));

      long que5Id = qDao.insert(new DDQuestion("A Java programming language keyword used in a class"
          + " definition to specify that a class is not to be instantiated, but rather inherited by "
          + "other classes.", levId));

      aDao.insert(new DDAnswer("Method", false, que5Id));
      aDao.insert(new DDAnswer("Class", false, que5Id));
      aDao.insert(new DDAnswer("Object", false, que5Id));
      aDao.insert(new DDAnswer("Field", false, que5Id));
      aDao.insert(new DDAnswer("Abstract", true, que5Id));

      forgetInstance(context);

      return null;
    }
  }
}
