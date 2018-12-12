package edu.cnm.deepdive.java_learn.model.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import edu.cnm.deepdive.java_learn.model.dao.DefinitionsAnswerDao;
import edu.cnm.deepdive.java_learn.model.dao.DefinitionsQuestionDao;
import edu.cnm.deepdive.java_learn.model.dao.FillBlankAnswerDao;
import edu.cnm.deepdive.java_learn.model.dao.FillBlankQuestionDao;
import edu.cnm.deepdive.java_learn.model.dao.HighlightAnswerDao;
import edu.cnm.deepdive.java_learn.model.dao.HighlightQuestionDao;
import edu.cnm.deepdive.java_learn.model.dao.LevelDao;
import edu.cnm.deepdive.java_learn.model.dao.MultipleChoiceAnswerDao;
import edu.cnm.deepdive.java_learn.model.dao.MultipleChoiceQuestionDao;
import edu.cnm.deepdive.java_learn.model.entity.DefinitionsAnswer;
import edu.cnm.deepdive.java_learn.model.entity.DefinitionsQuestion;
import edu.cnm.deepdive.java_learn.model.entity.FillBlankAnswer;
import edu.cnm.deepdive.java_learn.model.entity.FillBlankQuestion;
import edu.cnm.deepdive.java_learn.model.entity.HighlightAnswer;
import edu.cnm.deepdive.java_learn.model.entity.HighlightQuestion;
import edu.cnm.deepdive.java_learn.model.entity.Level;
import edu.cnm.deepdive.java_learn.model.entity.MultipleChoiceAnswer;
import edu.cnm.deepdive.java_learn.model.entity.MultipleChoiceQuestion;

/**
 * JavaLearnDB class allows the user to create a database for holding user levels,
 * and holds the questions and answers for Drag and Drop, HighLight,
 * Fill-in-the-Blank, and Multiple Choice.
 */
@Database(
    entities = {Level.class, DefinitionsAnswer.class, DefinitionsQuestion.class, FillBlankAnswer.class, FillBlankQuestion.class,
        HighlightAnswer.class, HighlightQuestion.class, MultipleChoiceAnswer.class, MultipleChoiceQuestion.class},
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


  public abstract DefinitionsQuestionDao getDDQuestionDao();


  public abstract DefinitionsAnswerDao getDDAnswerDao();


  public abstract FillBlankQuestionDao getFBQuestionDao();


  public abstract FillBlankAnswerDao getFBAnswerDao();


  public abstract HighlightQuestionDao getHLQuestionDao();


  public abstract HighlightAnswerDao getHLAnswerDao();

  public abstract MultipleChoiceQuestionDao getMCQuestionDao();

  public abstract MultipleChoiceAnswerDao getMCAnswerDao();

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
      DefinitionsQuestionDao qDao = db.getDDQuestionDao();
      DefinitionsAnswerDao aDao = db.getDDAnswerDao();
      MultipleChoiceQuestionDao mcQDao = db.getMCQuestionDao();
      MultipleChoiceAnswerDao mcADao = db.getMCAnswerDao();
      HighlightQuestionDao hlQDao = db.getHLQuestionDao();
      HighlightAnswerDao hlADao = db.getHLAnswerDao();
      FillBlankQuestionDao fbQDao = db.getFBQuestionDao();
      FillBlankAnswerDao fbADao = db.getFBAnswerDao();

      long levId = lDao.insert(new Level("Definitions Test"));
      long levId2 = lDao.insert(new Level("Multiple Choice Test"));
      long levId3 = lDao.insert(new Level("Highlight Test"));
      long levId4 = lDao.insert(new Level("Fill Blank Test"));

      long que1Id = qDao.insert(new DefinitionsQuestion("A function defined in a class.", levId));
      aDao.insert(new DefinitionsAnswer("Method", true, que1Id));
      aDao.insert(new DefinitionsAnswer("Class", false, que1Id));
      aDao.insert(new DefinitionsAnswer("Object", false, que1Id));
      aDao.insert(new DefinitionsAnswer("Field", false, que1Id));
      aDao.insert(new DefinitionsAnswer("Abstract", false, que1Id));

      long que2Id = qDao.insert(new DefinitionsQuestion("A type that defines the implementation of a "
          + "particular kind of object.", levId));

      aDao.insert(new DefinitionsAnswer("Method", false, que2Id));
      aDao.insert(new DefinitionsAnswer("Class", true, que2Id));
      aDao.insert(new DefinitionsAnswer("Object", false, que2Id));
      aDao.insert(new DefinitionsAnswer("Field", false, que2Id));
      aDao.insert(new DefinitionsAnswer("Abstract", false, que2Id));

      long que3Id = qDao.insert(new DefinitionsQuestion("The principal building blocks of object-oriented "
          + "programs", levId));
      aDao.insert(new DefinitionsAnswer("Method", false, que3Id));
      aDao.insert(new DefinitionsAnswer("Class", false, que3Id));
      aDao.insert(new DefinitionsAnswer("Object", true, que3Id));
      aDao.insert(new DefinitionsAnswer("Field", false, que3Id));
      aDao.insert(new DefinitionsAnswer("Abstract", false, que3Id));

      long que4Id = qDao.insert(new DefinitionsQuestion("A data member of a class.", levId));
      aDao.insert(new DefinitionsAnswer("Method", false, que4Id));
      aDao.insert(new DefinitionsAnswer("Class", false, que4Id));
      aDao.insert(new DefinitionsAnswer("Object", false, que4Id));
      aDao.insert(new DefinitionsAnswer("Field", true, que4Id));
      aDao.insert(new DefinitionsAnswer("Abstract", false, que4Id));

      long que5Id = qDao.insert(new DefinitionsQuestion("A Java keyword used to specify that a "
          + "class is to be inherited by "
          + "other classes.", levId));

      aDao.insert(new DefinitionsAnswer("Method", false, que5Id));
      aDao.insert(new DefinitionsAnswer("Class", false, que5Id));
      aDao.insert(new DefinitionsAnswer("Object", false, que5Id));
      aDao.insert(new DefinitionsAnswer("Field", false, que5Id));
      aDao.insert(new DefinitionsAnswer("Abstract", true, que5Id));

      long que6Id = mcQDao.insert(new MultipleChoiceQuestion("What is the access modifier\nfor the main method?",
          levId2));
      mcADao.insert(new MultipleChoiceAnswer("public", true, que6Id));
      mcADao.insert(new MultipleChoiceAnswer("private", false, que6Id));
      mcADao.insert(new MultipleChoiceAnswer("protected", false, que6Id));
      mcADao.insert(new MultipleChoiceAnswer("package private", false, que6Id));

      long que7Id = mcQDao.insert(new MultipleChoiceQuestion("How many methods does\nthis class contain?", levId2));
      mcADao.insert(new MultipleChoiceAnswer("2", true, que7Id));
      mcADao.insert(new MultipleChoiceAnswer("5", false, que7Id));
      mcADao.insert(new MultipleChoiceAnswer("6", false, que7Id));
      mcADao.insert(new MultipleChoiceAnswer("3", false, que7Id));

      long que8Id = mcQDao.insert(new MultipleChoiceQuestion("What does toString return?", levId2));
      mcADao.insert(new MultipleChoiceAnswer("A String", true, que8Id));
      mcADao.insert(new MultipleChoiceAnswer("StringBuilder", false, que8Id));
      mcADao.insert(new MultipleChoiceAnswer("StringBuffer", false, que8Id));
      mcADao.insert(new MultipleChoiceAnswer("StringWriter", false, que8Id));

      long que9Id = mcQDao.insert(new MultipleChoiceQuestion("What is the name\n of the class?", levId2));
      mcADao.insert(new MultipleChoiceAnswer("Decompose", true, que9Id));
      mcADao.insert(new MultipleChoiceAnswer("main", false, que9Id));
      mcADao.insert(new MultipleChoiceAnswer("toString", false, que9Id));
      mcADao.insert(new MultipleChoiceAnswer("QRDecomposition", false, que9Id));

      long que10Id = mcQDao.insert(new MultipleChoiceQuestion("How many imports is\n this class using?", levId2));
      mcADao.insert(new MultipleChoiceAnswer("4" , true, que10Id));
      mcADao.insert(new MultipleChoiceAnswer("2", false, que10Id));
      mcADao.insert(new MultipleChoiceAnswer("5", false, que10Id));
      mcADao.insert(new MultipleChoiceAnswer("6", false, que10Id));

      long que11Id =  mcQDao.insert(new MultipleChoiceQuestion("Which object is asking\nfor an array of arrays?", levId2));
      mcADao.insert(new MultipleChoiceAnswer(" Matrix", true, que11Id));
      mcADao.insert(new MultipleChoiceAnswer("toString", false, que11Id));
      mcADao.insert(new MultipleChoiceAnswer("QRDecomposition", false, que11Id));
      mcADao.insert(new MultipleChoiceAnswer("main", false, que11Id));

      long que12Id = hlQDao.insert(new HighlightQuestion("Field", levId3));
      hlADao.insert(new HighlightAnswer("double[] quotient", "1", que12Id));
      hlADao.insert(new HighlightAnswer("double[] remainder", "1", que12Id));

      long que13Id = hlQDao.insert(new HighlightQuestion("Access Modifier", levId3));
      hlADao.insert(new HighlightAnswer("public", "2", que13Id));
      hlADao.insert(new HighlightAnswer("private", "2", que13Id));

      long que14Id = hlQDao.insert(new HighlightQuestion("Class Name", levId3));
      hlADao.insert(new HighlightAnswer("Solution", "3", que14Id));

      long que15Id = fbQDao.insert(new FillBlankQuestion(levId4));
      fbADao.insert(new FillBlankAnswer("Select an answer...", false, que15Id));
      fbADao.insert(new FillBlankAnswer("try", true, que15Id));
      fbADao.insert(new FillBlankAnswer("do", false, que15Id));
      fbADao.insert(new FillBlankAnswer("printf", false, que15Id));
      fbADao.insert(new FillBlankAnswer("println", false, que15Id));
      fbADao.insert(new FillBlankAnswer("Random", false, que15Id));
      fbADao.insert(new FillBlankAnswer("IOException e", false, que15Id));

      long que16Id = fbQDao.insert(new FillBlankQuestion(levId4));
      fbADao.insert(new FillBlankAnswer("Select an answer...", false, que16Id));
      fbADao.insert(new FillBlankAnswer("try", false, que16Id));
      fbADao.insert(new FillBlankAnswer("do", false, que16Id));
      fbADao.insert(new FillBlankAnswer("printf", false, que16Id));
      fbADao.insert(new FillBlankAnswer("println", false, que16Id));
      fbADao.insert(new FillBlankAnswer("Random", true, que16Id));
      fbADao.insert(new FillBlankAnswer("IOException e", false, que16Id));

      long que17Id = fbQDao.insert(new FillBlankQuestion(levId4));
      fbADao.insert(new FillBlankAnswer("Select an answer...", false, que17Id));
      fbADao.insert(new FillBlankAnswer("try", false, que17Id));
      fbADao.insert(new FillBlankAnswer("do", false, que17Id));
      fbADao.insert(new FillBlankAnswer("printf", false, que17Id));
      fbADao.insert(new FillBlankAnswer("println", false, que17Id));
      fbADao.insert(new FillBlankAnswer("Random", false, que17Id));
      fbADao.insert(new FillBlankAnswer("IOException e", true, que17Id));

      long que18Id = fbQDao.insert(new FillBlankQuestion(levId4));
      fbADao.insert(new FillBlankAnswer("Select an answer...", false, que18Id));
      fbADao.insert(new FillBlankAnswer("try", false, que18Id));
      fbADao.insert(new FillBlankAnswer("do", false, que18Id));
      fbADao.insert(new FillBlankAnswer("printf", false, que18Id));
      fbADao.insert(new FillBlankAnswer("println", true, que18Id));
      fbADao.insert(new FillBlankAnswer("Random", false, que18Id));
      fbADao.insert(new FillBlankAnswer("IOException e", false, que18Id));



      forgetInstance(context);

      return null;
    }
  }
}
