package edu.cnm.deepdive.java_learn.multipleChoice;

/**
 * McAnswer class that holds the setters and getters for retrieving and setting the answers to
 * be populated in the radio groups.
 */
public class McAnswer {

  private String answer;
  private boolean isCorrect;

  /**
   * Objects passed through the McAnswer.class
   * @param answer - a random word generated from the database.
   * @param isCorrect - true or false value.
   */
  public McAnswer(String answer, boolean isCorrect) {
    this.answer = answer;
    this.isCorrect = isCorrect;
  }

  /**
   * Getter for retrieving the answer from the database.
   * @return answer - the retrieved answer from the database.
   */
  public String getAnswerText() {
    return answer;
  }

  /**
   * Sets the answer after retrieving it from the database.
   * @param answer- sets the answer that was received from the database.
   */
  public void setAnswer(String answer) {
    this.answer = answer;
  }

  /**
   * Returns a true or false value
   * @return isCorrect - returns the true or false value for the answer.
   */
  public boolean isCorrect() {
    return isCorrect;
  }

  /**
   * Setter for evaluating whether or not the answer is correct.
   * @param correct - true or false value.
   */
  public void setCorrect(boolean correct) {
    isCorrect = correct;
  }
}
