package nl.frankprins.droidtables;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends Activity {

  private TextView nameField;
  private Spinner levelchoice;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.qinit);

    nameField = (TextView) findViewById(R.id.NameField);
    Button startBtn = (Button) findViewById(R.id.choiceStartButton);
    startBtn.setOnClickListener(new View.OnClickListener() {

      public void onClick(View v) {
        List<Integer> tList = new ArrayList<Integer>();
        CheckBox c1 = (CheckBox) findViewById(R.id.cb1);
        CheckBox c2 = (CheckBox) findViewById(R.id.cb2);
        CheckBox c3 = (CheckBox) findViewById(R.id.cb3);
        CheckBox c4 = (CheckBox) findViewById(R.id.cb4);
        CheckBox c5 = (CheckBox) findViewById(R.id.cb5);
        CheckBox c6 = (CheckBox) findViewById(R.id.cb6);
        CheckBox c7 = (CheckBox) findViewById(R.id.cb7);
        CheckBox c8 = (CheckBox) findViewById(R.id.cb8);
        CheckBox c9 = (CheckBox) findViewById(R.id.cb9);
        CheckBox c10 = (CheckBox) findViewById(R.id.cb10);
        CheckBox c11 = (CheckBox) findViewById(R.id.cb11);
        CheckBox c12 = (CheckBox) findViewById(R.id.cb12);
        if (c1.isChecked()) {
          tList.add(1);
        }
        if (c2.isChecked()) {
          tList.add(2);
        }
        if (c3.isChecked()) {
          tList.add(3);
        }
        if (c4.isChecked()) {
          tList.add(4);
        }
        if (c5.isChecked()) {
          tList.add(5);
        }
        if (c6.isChecked()) {
          tList.add(6);
        }
        if (c7.isChecked()) {
          tList.add(7);
        }
        if (c8.isChecked()) {
          tList.add(8);
        }
        if (c9.isChecked()) {
          tList.add(9);
        }
        if (c10.isChecked()) {
          tList.add(10);
        }
        if (c11.isChecked()) {
          tList.add(11);
        }
        if (c12.isChecked()) {
          tList.add(12);
        }

        int[] tables = new int[tList.size()];
        for (int i = 0; i < tables.length; i++) {
          tables[i] = tList.get(i);
        }
        // get level choice
        String level = levelchoice.getItemAtPosition(levelchoice.getSelectedItemPosition())
            .toString();
        if (tables.length < 1) {
          Toast.makeText(getApplicationContext(), "You should better choose some tables!", 1000)
              .show();
        } else {
          Intent intent = new Intent(getApplicationContext(), CalcActivity.class);
          String s = nameField.getText().toString();
          intent.putExtra("NAME", s);
          intent.putExtra("LEVEL", level);
          intent.putExtra("TABLES", tables);
          startActivity(intent);
        }
      }
    });

    levelchoice = (Spinner) findViewById(R.id.levelchoice);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.level_choice_array, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    levelchoice.setAdapter(adapter);
  }

}