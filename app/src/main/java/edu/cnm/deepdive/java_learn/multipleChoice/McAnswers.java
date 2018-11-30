package edu.cnm.deepdive.java_learn.multipleChoice;

public class McAnswers {

  private String answer;
  private boolean isCorrect;

  public McAnswers(String answer, boolean isCorrect) {
    this.answer = answer;
    this.isCorrect = isCorrect;
  }

  public String getAnswerText() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public boolean isCorrect() {
    return isCorrect;
  }

  public void setCorrect(boolean correct) {
    isCorrect = correct;
  }
}
