package edu.cnm.deepdive.java_learn.model.pojo;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;
import edu.cnm.deepdive.java_learn.model.entity.MCAnswer;
import edu.cnm.deepdive.java_learn.model.entity.MCQuestion;
import java.util.List;

public class MCQuestionWithAnswers {

  @Embedded
  private MCQuestion question;

  @Relation(parentColumn = "mc_question_id", entityColumn = "mc_question_id")
  private List<MCAnswer> answers;

  public MCQuestion getQuestion() {
    return question;
  }

  public void setQuestion(MCQuestion question) {
    this.question = question;
  }

  public List<MCAnswer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<MCAnswer> answers) {
    this.answers = answers;
  }
}
