package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.DialerKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare member variables here:

    Button trueButton;
    Button falseButton;
    TextView questionView;
    int index;
    int question;
    int score;
    TextView scoreText;
    ProgressBar mProgressBar;
    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13, true)
    };


    // TODO: Declare constants here

    final int progress_Bar = (int) Math.ceil(100.0 / mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!= null){
            score = savedInstanceState.getInt("Score");
            index= savedInstanceState.getInt("Index");
        }
        else{
            score= 0;
            index= 0;
        }

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        questionView = findViewById(R.id.question_text_view);
        scoreText = findViewById(R.id.score);
        mProgressBar = findViewById(R.id.progress_bar);

        question = mQuestionBank[index].getQueID();
        scoreText.setText("Your Score " + score + " !");

        questionView.setText(question);



        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAns(true);
                updateQue();
//                Toast.makeText(getApplicationContext(), "True Button Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(false);
                updateQue();
//                Toast.makeText(getApplicationContext(), "False Button Pressed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateQue() {
        index = (index + 1) % mQuestionBank.length;
        if(index==0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("Your Score " + score + " !");
            alert.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
        question = mQuestionBank[index].getQueID();
        questionView.setText(question);
        mProgressBar.incrementProgressBy(progress_Bar);
        scoreText.setText("Score " + score + "/" + mQuestionBank.length);
    }

    private void checkAns(boolean userSelection) {
        boolean CorrectAns = mQuestionBank[index].isqAns();

        if (userSelection == CorrectAns) {
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
            score = score + 1;

        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("Score",score);
        outState.putInt("Index",index);
    }

}


