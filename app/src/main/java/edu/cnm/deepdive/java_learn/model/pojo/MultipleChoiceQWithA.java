package edu.cnm.deepdive.java_learn.model.pojo;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;
import edu.cnm.deepdive.java_learn.model.entity.MultipleChoiceA;
import edu.cnm.deepdive.java_learn.model.entity.MultipleChoiceQ;
import java.util.List;

public class MultipleChoiceQWithA {

  @Embedded
  private MultipleChoiceQ question;

  @Relation(parentColumn = "mc_question_id", entityColumn = "mc_question_id")
  private List<MultipleChoiceA> answers;

  public MultipleChoiceQ getQuestion() {
    return question;
  }

  public void setQuestion(MultipleChoiceQ question) {
    this.question = question;
  }

  public List<MultipleChoiceA> getAnswers() {
    return answers;
  }

  public void setAnswers(List<MultipleChoiceA> answers) {
    this.answers = answers;
  }
}
