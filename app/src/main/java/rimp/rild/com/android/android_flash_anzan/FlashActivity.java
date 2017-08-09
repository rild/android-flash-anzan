package rimp.rild.com.android.android_flash_anzan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.util.Random;

public class FlashActivity extends AppCompatActivity {
    public static final String KEY_ANSWER = "answer";
    public final String TAG = "Flash";

    private final int QUEXTION_NUMBER = 5;
    private final int TIME_MILLIS_PER_Q = 800;
    private final int TIME_MILLIS_TOTAL = TIME_MILLIS_PER_Q * QUEXTION_NUMBER; // 4000

    private TextView mTextViewFlash;
    private Button mButtonNext;
    private Button mButtonStart;

    private CountDown mCountDown;
    private int[] mQuestions = new int[QUEXTION_NUMBER];
    private int mAnswer = 0;
    private int mCurrentNum = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        initViews();

        initQuestions();

        mCountDown = new CountDown(TIME_MILLIS_TOTAL, TIME_MILLIS_PER_Q);
        mCountDown.setOnTickListener(new CountDown.OnTickListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d(TAG, "" + millisUntilFinished);
                showQuestion();
                nextQuestion();
            }
        });

        mCountDown.setOnFinishListener(new CountDown.OnFinishListener() {
            @Override
            public void onFinish() {
                finishQuestions();
                initQuestions();
            }
        });


    }

    private void initViews() {
        mTextViewFlash = (TextView) findViewById(R.id.flash_main);
        mButtonNext = (Button) findViewById(R.id.next_button);
        mButtonStart = (Button) findViewById(R.id.next_button);

    }

    private void initQuestions() {
        Random random = new Random();
        for (int i = 0; i < QUEXTION_NUMBER; i++) {
            mQuestions[i] = random.nextInt(99) + 1; // 1 ~ 99
            mAnswer += mQuestions[i];
        }
    }

    private void nextQuestion() {
        mCurrentNum++;
    }

    private void showQuestion() {
        if (mCurrentNum < QUEXTION_NUMBER)
            mTextViewFlash.setText(String.valueOf(mQuestions[mCurrentNum]));
    }

    private void finishQuestions() {
        mButtonNext.setVisibility(View.VISIBLE);
        mTextViewFlash.setText("");
    }

    public void go2Result(View v) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(KEY_ANSWER, mAnswer);
        startActivity(intent);
    }

    public void start(View v) {
        mButtonStart.setVisibility(View.INVISIBLE);
        mCountDown.start();
    }
}
