package com.example.justinkhull.timer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MainCountDownTimer countDownTimer;
    private long timeElapsed;
    private boolean timerHasStarted = false;
    private Button startB;
    private TextView text;
    private TextView timeElapsedView;
    private final long startTime = 50 * 1000;
    private final long interval = 1 * 1000;

    public class MainCountDownTimer extends CountDownTimer {
        public MainCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            text.setText(getResources().getString(R.string.time_remain) + millisUntilFinished);
            timeElapsed = startTime - millisUntilFinished;
            timeElapsedView.setText(getResources().getString(R.string.time_elapsed) + String.valueOf(timeElapsed));
        }

        @Override
        public void onFinish() {
            text.setText(getResources().getString(R.string.time_up));
            timeElapsedView.setText(getResources().getString(R.string.time_elapsed) + String.valueOf(startTime));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startB = (Button) this.findViewById(R.id.button);
        startB.setOnClickListener(this);
        text = (TextView) this.findViewById(R.id.timer);
        timeElapsedView = (TextView) this.findViewById(R.id.timeElapsed);
        countDownTimer = new MainCountDownTimer(startTime, interval);
        text.setText(text.getText() + String.valueOf(startTime));
    }

    @Override
    public void onClick(View view) {
        if(!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;
            startB.setText(getResources().getString(R.string.start_button));
        } else {
            countDownTimer.cancel();
            timerHasStarted = false;
            startB.setText(getResources().getString(R.string.resume_button));
        }
    }

}


