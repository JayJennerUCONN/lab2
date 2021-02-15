package me.com.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.com.lab2.controller.NextQuestion;
import me.com.lab2.controller.Score;
import me.com.lab2.model.AllQuestions;
import me.com.lab2.model.Question;

public class SummaryActivity extends AppCompatActivity {

    private Button backButton = null;
    private TextView sumScoreView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        backButton = findViewById(R.id.back_button);
        sumScoreView = findViewById(R.id.sumScoreView);
        int summaryscore = getIntent().getIntExtra(MainActivity.score_key, -1);
        sumScoreView.setText(String.valueOf(summaryscore));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}