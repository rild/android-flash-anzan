package rimp.rild.com.android.android_flash_anzan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView mTextResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        int answer = intent.getIntExtra(FlashActivity.KEY_ANSWER, 0);


        mTextResult = (TextView) findViewById(R.id.result_text);
        mTextResult.setText(String.valueOf(answer));
    }

    public void go2Main(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
