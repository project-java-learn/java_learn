package edu.cnm.deepdive.java_learn.multipleChoice;

import java.util.List;

public class McQuestions {

  private String question;
  private List<McAnswers> answers;

  public McQuestions(String question, List<McAnswers> answers) {
    this.question = question;
    this.answers = answers;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public List<McAnswers> getAnswers() {
    return answers;
  }

  public void setAnswers(List<McAnswers> answers) {
    this.answers = answers;
  }
}
