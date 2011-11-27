package nl.frankprins.droidtables;


public class Question {
  private int position = 0;
  private Long startTime;
  private Long endTime;
  private Long duration;
  private int table;
  private int multiplier;
  private int answer;

  public int getPosition() {
    return position;
  }

  public Question(int pos, int t, int m) {
    this.position = pos;
    this.table = t;
    this.multiplier = m;
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

  public String getDuration() {
    return String.valueOf(duration);
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

  public void setAnswer(int answer) {
    this.answer = answer;
  }
  

}