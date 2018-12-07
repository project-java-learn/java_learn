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
      MCQuestionDao mcQDao = db.getMCQuestionDao();
      MCAnswerDao mcADao = db.getMCAnswerDao();
      HLQuestionDao hlQDao = db.getHLQuestionDao();
      HLAnswerDao hlADao = db.getHLAnswerDao();
      FBQuestionDao fbQDao = db.getFBQuestionDao();
      FBAnswerDao fbADao = db.getFBAnswerDao();

      long levId = lDao.insert(new Level("Definitions Test"));
      long levId2 = lDao.insert(new Level("Multiple Choice Test"));
      long levId3 = lDao.insert(new Level("Highlight Test"));
      long levId4 = lDao.insert(new Level("Fill Blank Test"));

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

      long que6Id = mcQDao.insert(new MCQuestion("What is the access modifier\nfor the main method?",
          levId2));
      mcADao.insert(new MCAnswer("public", true, que6Id));
      mcADao.insert(new MCAnswer("private", false, que6Id));
      mcADao.insert(new MCAnswer("protected", false, que6Id));
      mcADao.insert(new MCAnswer("package private", false, que6Id));

      long que7Id = mcQDao.insert(new MCQuestion("How many methods does\nthis class contain?", levId2));
      mcADao.insert(new MCAnswer("2", true, que7Id));
      mcADao.insert(new MCAnswer("5", false, que7Id));
      mcADao.insert(new MCAnswer("6", false, que7Id));
      mcADao.insert(new MCAnswer("3", false, que7Id));

      long que8Id = mcQDao.insert(new MCQuestion("What does toString return", levId2));
      mcADao.insert(new MCAnswer("A String", true, que8Id));
      mcADao.insert(new MCAnswer("StringBuilder", false, que8Id));
      mcADao.insert(new MCAnswer("StringBuffer", false, que8Id));
      mcADao.insert(new MCAnswer("StringWriter", false, que8Id));

      long que9Id = mcQDao.insert(new MCQuestion("What is the name\n of the class?", levId2));
      mcADao.insert(new MCAnswer("Decompose", true, que9Id));
      mcADao.insert(new MCAnswer("main", false, que9Id));
      mcADao.insert(new MCAnswer("toString", false, que9Id));
      mcADao.insert(new MCAnswer("QRDecomposition", false, que9Id));

      long que10Id = mcQDao.insert(new MCQuestion("How many imports is\n this class using?", levId2));
      mcADao.insert(new MCAnswer("4" , true, que10Id));
      mcADao.insert(new MCAnswer("2", false, que10Id));
      mcADao.insert(new MCAnswer("5", false, que10Id));
      mcADao.insert(new MCAnswer("6", false, que10Id));

      long que11Id =  mcQDao.insert(new MCQuestion("Which object is asking\nfor an array of arrays?", levId2));
      mcADao.insert(new MCAnswer(" Matrix", true, que11Id));
      mcADao.insert(new MCAnswer("toString", false, que11Id));
      mcADao.insert(new MCAnswer("QRDecomposition", false, que11Id));
      mcADao.insert(new MCAnswer("main", false, que11Id));

      long que12Id = hlQDao.insert(new HLQuestion("Field", levId3));
      hlADao.insert(new HLAnswer("double[] quotient", "1", que12Id));
      hlADao.insert(new HLAnswer("double[] remainder", "1", que12Id));

      long que13Id = hlQDao.insert(new HLQuestion("Access Modifier", levId3));
      hlADao.insert(new HLAnswer("private", "2", que13Id));

      long que14Id = hlQDao.insert(new HLQuestion("Class Name", levId3));
      hlADao.insert(new HLAnswer("Solution", "3", que14Id));

      long que15Id = fbQDao.insert(new FBQuestion(levId4));
      fbADao.insert(new FBAnswer("try", true, que15Id));
      fbADao.insert(new FBAnswer("do", false, que15Id));
      fbADao.insert(new FBAnswer("printf", false, que15Id));
      fbADao.insert(new FBAnswer("println", false, que15Id));
      fbADao.insert(new FBAnswer("Random", false, que15Id));
      fbADao.insert(new FBAnswer("IOException e", false, que15Id));

      long que16Id = fbQDao.insert(new FBQuestion(levId4));
      fbADao.insert(new FBAnswer("try", false, que16Id));
      fbADao.insert(new FBAnswer("do", false, que16Id));
      fbADao.insert(new FBAnswer("printf", false, que16Id));
      fbADao.insert(new FBAnswer("println", false, que16Id));
      fbADao.insert(new FBAnswer("Random", true, que16Id));
      fbADao.insert(new FBAnswer("IOException e", false, que16Id));

      long que17Id = fbQDao.insert(new FBQuestion(levId4));
      fbADao.insert(new FBAnswer("try", false, que17Id));
      fbADao.insert(new FBAnswer("do", false, que17Id));
      fbADao.insert(new FBAnswer("printf", false, que17Id));
      fbADao.insert(new FBAnswer("println", false, que17Id));
      fbADao.insert(new FBAnswer("Random", false, que17Id));
      fbADao.insert(new FBAnswer("IOException e", true, que17Id));

      long que18Id = fbQDao.insert(new FBQuestion(levId4));
      fbADao.insert(new FBAnswer("try", false, que18Id));
      fbADao.insert(new FBAnswer("do", false, que18Id));
      fbADao.insert(new FBAnswer("printf", false, que18Id));
      fbADao.insert(new FBAnswer("println", true, que18Id));
      fbADao.insert(new FBAnswer("Random", false, que18Id));
      fbADao.insert(new FBAnswer("IOException e", false, que18Id));



      forgetInstance(context);

      return null;
    }
  }
}
