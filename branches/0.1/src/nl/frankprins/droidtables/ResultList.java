package nl.frankprins.droidtables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ResultList extends Activity {
  private QuestionFactory qf;
  private int correctQ = 0;
  private int score = 0;
  private String name = "";
  private HighScoreFactory hsf;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.resultlist);
    ListView lv = (ListView) findViewById(R.id.listview);

    qf = (QuestionFactory) getIntent().getParcelableExtra("RESULTS");
    this.name = qf.getName();

    // create the grid item mapping
    String[] from = new String[] { "rowid", "col_1", "col_2", "col_3", "col_4" };
    int[] to = new int[] { R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5 };

    // prepare the list of all records
    List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
    for (int i = 0; i < qf.getQuestions().length; i++) {
      int points = 0;
      HashMap<String, String> map = new HashMap<String, String>();
      map.put("rowid", String.valueOf(i + 1));
      Question curq = qf.getQuestions()[i];
      String q = curq.getMultiplier() + " x " + curq.getTable() + " = " + curq.getAnswer();
      map.put("col_1", q);
      String correctAnswer = String.valueOf(curq.getMultiplier() * curq.getTable());
      map.put("col_2", correctAnswer);
      String correct = "";
      if (String.valueOf(curq.getAnswer()) == correctAnswer) {
        correct = "goed!";
        points = qf.getPointsPerQuestion();
        int timeLeft = (int) ((curq.getMaxTime() - curq.getDuration()) / 1000);
        points += (timeLeft * 5);
        score += points;
        correctQ++;
      } else {
        correct = "fout!";
      }
      map.put("col_3", correct);
      map.put("col_4", String.valueOf(points));
      fillMaps.add(map);
    }

    // fill in the grid_item layout
    SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.resultlist_item, from, to);
    lv.setAdapter(adapter);

    // check the score
    hsf = new HighScoreFactory(this);
    // hsf.deleteScores();
    for (int i = 0; i < 10; i++) {
      Log.i("Tables", "position " + hsf.getScore(i) + ", score: " + hsf.getName(i));
    }

    Builder adb = new AlertDialog.Builder(ResultList.this);
    String text = "";
    if (hsf.inHighscore(score)) {
      adb.setTitle("High score!");
      text = correctQ + " van de " + qf.getQuestions().length + " vragen goed beantwoord! Score = "
          + score + " High score!";
      hsf.addScore(name, score);
    } else {
      adb.setTitle("Finished");
      text = correctQ + " van de " + qf.getQuestions().length + " vragen goed beantwoord! Score = "
          + score;
    }
    adb.setMessage(text);
    adb.setPositiveButton("close", null);
    adb.show();
  }

}
