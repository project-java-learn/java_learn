package edu.cnm.deepdive.java_learn.multipleChoice;

import java.util.List;

public class McQuestion {

  private String question;
  private List<McAnswer> answers;

  public McQuestion(String question, List<McAnswer> answers) {
    this.question = question;
    this.answers = answers;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public List<McAnswer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<McAnswer> answers) {
    this.answers = answers;
  }
}
