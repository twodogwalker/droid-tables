package nl.frankprins.droidtables;

import android.util.Log;

public class QuestionFactory {
  private Question[] qList = null;
  private int current = 0;
  private int numberOfQuestions = 0;
  private int maxTime = 0;

  public QuestionFactory(String level, int[] tables) {
    setLevel(level);
    qList = new Question[numberOfQuestions];
    for (int i = 0; i < numberOfQuestions; i++) {
      int t = (int) (Math.random() * ((tables.length) + 1) - 1);
      int m = 1 + (int) (Math.random() * 10);
      qList[i] = new Question(i, tables[t], m);
    }
  }

  private void setLevel(String level) {
    if (level.equalsIgnoreCase("easy")) {
      this.numberOfQuestions = 5;
      this.maxTime = 10;
    } else if (level.equalsIgnoreCase("moderate")) {
      this.numberOfQuestions = 10;
      this.maxTime = 5;
    } else if (level.equalsIgnoreCase("difficult")) {
      this.numberOfQuestions = 15;
      this.maxTime = 3;
    }
  }

  public Question getFirstQ() {
    if (qList.length > current) {
      return this.qList[current];
    }
    return null;
  }

  public Question getNextQ() {
    if (qList.length > current + 1) {
      current = current + 1;
      return this.qList[current];
    }
    return null;
  }

  public void setCurrentQAnswer(int i) {
    this.qList[current].setAnswer(i);
  }

  public void stopCurrentQ() {
    this.qList[current].setEndTime(System.currentTimeMillis());
    Log.i("Tables", "stopping q " + current);
  }

  public void startCurrentQ() {
    this.qList[current].setStartTime(System.currentTimeMillis());
    Log.i("Tables", "starting q " + current);
  }

  public String getResult() {
    StringBuilder a = new StringBuilder();
    for (int i = 0; i < qList.length; i++) {
      a.append(String.valueOf(qList[i].getPosition() + 1) + ". "
          + String.valueOf(qList[i].getTable()) + " x " + String.valueOf(qList[i].getMultiplier())
          + " = " + String.valueOf(qList[i].getAnswer()) + " (time: " + qList[i].getDuration()
          + ")\n");
    }
    return a.toString();
  }

  public int getMaxTime() {
    return maxTime;
  }
  public int getNumberOfQuestions() {
    return numberOfQuestions;
  }
}
