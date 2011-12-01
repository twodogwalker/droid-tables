package nl.frankprins.droidtables;

import java.io.Serializable;

public class Question implements Serializable {
  private static final long serialVersionUID = 1L;
  private int position = 0;
  private Long startTime;
  private Long endTime;
  private Long duration;
  private int table;
  private int multiplier;
  private int answer;
  private long maxTime = 0;

  public int getPosition() {
    return position;
  }

  public Question(int pos, int t, int m, long max) {
    this.position = pos;
    this.table = t;
    this.multiplier = m;
    this.maxTime = max;
    this.setAnswer((int) t * m);
  }

  public Long getStartTime() {
    return startTime;
  }

  public void setStartTime(Long l) {
    this.startTime = l;
  }

  public Long getEndTime() {
    return endTime;
  }

  public long getDuration() {
    return duration;
  }

  public void setEndTime(Long l) {
    this.endTime = l;
    this.duration = this.endTime - this.startTime;
  }

  public int getTable() {
    return table;
  }

  public int getMultiplier() {
    return multiplier;
  }

  public int getAnswer() {
    return answer;
  }

  public long getMaxTime() {
    return this.maxTime;
  }

  public void setAnswer(int answer) {
    this.answer = answer;
  }

}