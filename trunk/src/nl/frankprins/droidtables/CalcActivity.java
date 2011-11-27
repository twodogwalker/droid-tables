package nl.frankprins.droidtables;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class CalcActivity extends Activity implements OnClickListener {
  private QuestionFactory qFact = null;
  private String level;
  private ProgressBar progressBar;
  private MyTimer timer;
  private TextView questionTitleView;
  private TextView resultBox;
  private TextView multiplierLabel;
  private TextView tableLabel;
  private Button Btn1, Btn2, Btn3, Btn4, Btn5, Btn6, Btn7, Btn8, Btn9, Btn0, BtnB, BtnE;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.input);
    initButtons();
    questionTitleView = (TextView) findViewById(R.id.QuestionTitle);
    resultBox = (TextView) findViewById(R.id.ResultBox);
    progressBar = (ProgressBar) findViewById(R.id.ProgressBar);
    multiplierLabel = (TextView) findViewById(R.id.TextViewS1);
    multiplierLabel.setText("9");
    tableLabel = (TextView) findViewById(R.id.TextViewS3);
    level = getIntent().getStringExtra("LEVEL");
    qFact = new QuestionFactory(level, getIntent().getIntArrayExtra("TABLES"));
    timer = new MyTimer(qFact.getMaxTime()*1000, 100);
    startQuestion(qFact.getFirstQ());
    
  }

  @Override
  public void onClick(View v) {
    String answer = (String) resultBox.getText();
    if (v == BtnE) {
      if (answer != "") {
        validateInput(answer);
      }
      return;
    }
    // Get the button
    Button button = (Button) v;
    String strToAppend = button.getText().toString();
    if (strToAppend.equalsIgnoreCase("«")) {
      if (answer.length() == 1) {
        resultBox.setText("");
      } else if (answer.length() > 1) {
        resultBox.setText(answer.substring(0, answer.length() - 1));
      }
    } else {
      if (answer.length() < 3) {
        resultBox.setText(resultBox.getText() + strToAppend);
      }
    }
  }

  private void initButtons() {
    Btn1 = (Button) findViewById(R.id.b1);
    Btn1.setOnClickListener(this);
    Btn2 = (Button) findViewById(R.id.b2);
    Btn2.setOnClickListener(this);
    Btn3 = (Button) findViewById(R.id.b3);
    Btn3.setOnClickListener(this);
    Btn4 = (Button) findViewById(R.id.b4);
    Btn4.setOnClickListener(this);
    Btn5 = (Button) findViewById(R.id.b5);
    Btn5.setOnClickListener(this);
    Btn6 = (Button) findViewById(R.id.b6);
    Btn6.setOnClickListener(this);
    Btn7 = (Button) findViewById(R.id.b7);
    Btn7.setOnClickListener(this);
    Btn8 = (Button) findViewById(R.id.b8);
    Btn8.setOnClickListener(this);
    Btn9 = (Button) findViewById(R.id.b9);
    Btn9.setOnClickListener(this);
    Btn0 = (Button) findViewById(R.id.b0);
    Btn0.setOnClickListener(this);
    BtnB = (Button) findViewById(R.id.bB);
    BtnB.setOnClickListener(this);
    BtnE = (Button) findViewById(R.id.bE);
    BtnE.setOnClickListener(this);
  }

  private void validateInput(String resultString) {
    int result = Integer.parseInt(resultString);
    qFact.setCurrentQAnswer(result);
    qFact.stopCurrentQ();
    timer.cancel();
    Question nextQ = qFact.getNextQ();
    if (nextQ != null) {
      startQuestion(nextQ);
    } else {
      Intent intent = new Intent(getApplicationContext(), ResultList.class);
      intent.putExtra("RESULTS", qFact);
      startActivity(intent);
    }
  }

  private void startQuestion(Question q) {
    String s = String.valueOf(q.getPosition() + 1) + " / " + qFact.getNumberOfQuestions();
    questionTitleView.setText("Vraag " + s + ":");
    multiplierLabel.setText(String.valueOf(q.getMultiplier()));
    tableLabel.setText(String.valueOf(q.getTable()));
    resultBox.setText("");
    setDigitButtonsEnabled(true);
    qFact.startCurrentQ();
    progressBar.setProgress(100);
    timer.start();
  }

  public class MyTimer extends CountDownTimer {
    public MyTimer(int t, int i) {
      super(t, i);
    }

    @Override
    public void onFinish() {
      Toast.makeText(getApplicationContext(), "Sorry, time is up!", 1000).show();
      setDigitButtonsEnabled(false);
    }

    @Override
    public void onTick(long millisUntilFinished) {
      progressBar.setProgress((int) (millisUntilFinished / 100));
    }
  }

  public void setDigitButtonsEnabled(boolean b) {
    Btn1.setEnabled(b);
    Btn2.setEnabled(b);
    Btn3.setEnabled(b);
    Btn4.setEnabled(b);
    Btn5.setEnabled(b);
    Btn6.setEnabled(b);
    Btn7.setEnabled(b);
    Btn8.setEnabled(b);
    Btn9.setEnabled(b);
    Btn0.setEnabled(b);
  }

}
