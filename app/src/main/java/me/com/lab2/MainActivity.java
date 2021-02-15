package me.com.lab2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import me.com.lab2.controller.NextQuestion;
import me.com.lab2.controller.Score;
import me.com.lab2.model.AllQuestions;
import me.com.lab2.model.Question;


public class MainActivity extends AppCompatActivity {

    private TextView questionView = null;
    public TextView scoreView = null;
    public TextView streakView = null;
    private Button trueButton = null;
    private Button falseButton = null;
    private Button nextButton = null;
    private Button doneButton = null;
    private Button summaryButton = null;
    int scr;

    public static final String score_key = "score_key";

    private static final String TAG_INDEX = "Game_Main_Activity";

    AllQuestions allQuestions = new AllQuestions();
    NextQuestion nextQuestion = new NextQuestion();
    Score score = new Score();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        questionView = findViewById(R.id.questionView);
        scoreView = findViewById(R.id.scoreView);
        streakView = findViewById(R.id.streakView);

        questionView.setText(R.string.q_start);
        scoreView.setText(R.string.initial_score);
        streakView.setText(R.string.initial_streak);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        doneButton = findViewById(R.id.done_button);
        summaryButton = findViewById(R.id.summary_button);

        summaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scr = score.getScore();
                launchSummary();
            }
        });



        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = nextQuestion.getCurrentQuestion();
                Question question = null;
                try {
                    question = allQuestions.getQuestion(index);
                } catch (Exception e) {
                    Log.d(TAG_INDEX, "index out of bounds");
                }

                if (question.isQuestionTrue()) {
                    score.correctAnswer();
                    scoreView.setText(String.valueOf(score.getScore()));
                    streakView.setText(String.valueOf(score.getStreak()));
                }
                else{
                    score.wrongAnswer();
                    streakView.setText(String.valueOf(score.getStreak()));
                }

                index = nextQuestion.getNextQuestionIndex();
                questionView.setText(allQuestions.getQuestion(index).getQuestionID());
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = nextQuestion.getCurrentQuestion();
                Question question = null;
                try {
                    question = allQuestions.getQuestion(index);
                } catch (Exception e) {
                    Log.d(TAG_INDEX, "index out of bounds");
                }

                if (!question.isQuestionTrue()) {
                    score.correctAnswer();
                    scoreView.setText(String.valueOf(score.getScore()));
                    streakView.setText(String.valueOf(score.getStreak()));
                }
                else{
                    score.wrongAnswer();
                    streakView.setText(String.valueOf(score.getStreak()));
                }

                index = nextQuestion.getNextQuestionIndex();
                questionView.setText(allQuestions.getQuestion(index).getQuestionID());
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = nextQuestion.getCurrentQuestion();
                Question question = null;
                try {
                    question = allQuestions.getQuestion(index);
                } catch (Exception e) {
                    Log.d(TAG_INDEX, "index out of bounds");
                }

                score.skipQuestion();
                score.wrongAnswer();
                index = nextQuestion.getNextQuestionIndex();
                scoreView.setText(String.valueOf(score.getScore()));
                streakView.setText(String.valueOf(score.getStreak()));
                questionView.setText(allQuestions.getQuestion(index).getQuestionID());
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });
    }

    private void launchSummary(){
        Intent intent = new Intent(this, SummaryActivity.class);
        intent.putExtra(score_key, scr);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}