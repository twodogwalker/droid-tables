package nl.frankprins.droidtables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class HighScoreList extends Activity {

  private HighScoreFactory hsf;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.highscores);
    ListView lv = (ListView) findViewById(R.id.scorelistview);

    // create the grid item mapping
    String[] from = new String[] { "rowid", "col_1", "col_2" };
    int[] to = new int[] { R.id.position, R.id.name, R.id.score};

    // get the scores
    hsf = new HighScoreFactory(this);
    List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
    for (int i = 0; i < 10; i++) {
      HashMap<String, String> map = new HashMap<String, String>();
      map.put("rowid", String.valueOf(i + 1));
      map.put("col_1", hsf.getName(i));
      map.put("col_2", String.valueOf(hsf.getScore(i)));
      fillMaps.add(map);
    }

    // fill in the grid_item layout
    SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.highscore_item, from, to);
    lv.setAdapter(adapter);

  }

}
