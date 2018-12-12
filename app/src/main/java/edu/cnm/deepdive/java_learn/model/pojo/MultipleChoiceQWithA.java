package edu.cnm.deepdive.java_learn.model.pojo;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;
import edu.cnm.deepdive.java_learn.model.entity.MultipleChoiceAnswer;
import edu.cnm.deepdive.java_learn.model.entity.MultipleChoiceQuestion;
import java.util.List;

public class MultipleChoiceQWithA {

  @Embedded
  private MultipleChoiceQuestion question;

  @Relation(parentColumn = "mc_question_id", entityColumn = "mc_question_id")
  private List<MultipleChoiceAnswer> answers;

  public MultipleChoiceQuestion getQuestion() {
    return question;
  }

  public void setQuestion(MultipleChoiceQuestion question) {
    this.question = question;
  }

  public List<MultipleChoiceAnswer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<MultipleChoiceAnswer> answers) {
    this.answers = answers;
  }
}
