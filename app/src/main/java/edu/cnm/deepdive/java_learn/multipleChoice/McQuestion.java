package edu.cnm.deepdive.java_learn.multipleChoice;

import java.util.List;

/**
 * McQuestion.java class lists the setters and getters for the MultipleChoice class to retrieve the
 * questions and answers from the JavaLearnDB.java database
 */
public class McQuestion {

  private String question;
  private List<McAnswer> answers;

  /**
   *
   * @param question -
   * @param answers -
   */
  public McQuestion(String question, List<McAnswer> answers) {
    this.question = question;
    this.answers = answers;
  }

  /**
   * Getter for retrieving a question from the database
   * @return - retrieves a question from database
   */
  public String getQuestion() {
    return question;
  }

  /**
   * Setter for the question that was retrieved from the database
   * @param question - sets the question retrieved from the database
   */
  public void setQuestion(String question) {
    this.question = question;
  }

  /**
   * Getter answers from database
   * @return answers - returns a list of answers
   */
  public List<McAnswer> getAnswers() {
    return answers;
  }

  /**
   * Setter for List of answers retrieved from the database
   * @param answers - sets list of answers
   */
  public void setAnswers(List<McAnswer> answers) {
    this.answers = answers;
  }
}
