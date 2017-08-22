package com.sami.brainteaser;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class TeaserActivity extends AppCompatActivity implements View.OnClickListener {
    Button goBtn;
    boolean isGoBtn = true;
    int imageArray[], questionCount = 0, scoreCount = 0;
    String questionArray[], colourOptionArray[], answerArray[], cartoonOptionArray[], animalOptionArray[], ballonOptionArray[], foodOptionArray[];
    String userAnswer;
    ImageView quizImage;
    TextView queryTxt;
    RadioGroup optionRadioGroup;
    RadioButton optionRadioBtn1, optionRadioBtn2, optionRadioBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_pic);
        goBtn = (Button) findViewById(R.id.goBtn);
        quizImage = (ImageView) findViewById(R.id.quiz_img);
        queryTxt = (TextView) findViewById(R.id.pay_attention_txt);
        optionRadioGroup = (RadioGroup) findViewById(R.id.option_radio_group);
        optionRadioBtn1 = (RadioButton) findViewById(R.id.option_1);
        optionRadioBtn2 = (RadioButton) findViewById(R.id.option_2);
        optionRadioBtn3 = (RadioButton) findViewById(R.id.option_3);
        imageArray = new int[]{R.drawable.a_colour_txt_img, R.drawable.b_cartoon, R.drawable.c_animal, R.drawable.d_ballon, R.drawable.e_food};
        questionArray = getResources().getStringArray(R.array.question_array);
        colourOptionArray = getResources().getStringArray(R.array.colour_txt_option_array);
        cartoonOptionArray = getResources().getStringArray(R.array.cartoon_odd_option_array);
        animalOptionArray = getResources().getStringArray(R.array.animal_odd_option_array);
        ballonOptionArray = getResources().getStringArray(R.array.ballon_count_option_array);
        foodOptionArray = getResources().getStringArray(R.array.food_odd_option_array);
        answerArray = getResources().getStringArray(R.array.answer_array);
        goBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (isGoBtn) {
            goBtn.setText("SUBMIT");
            quizImage.setVisibility(View.INVISIBLE);
            optionRadioGroup.setVisibility(View.VISIBLE);
            optionRadioGroup.clearCheck();
            isGoBtn = false;
            switch (questionCount) {
                case 0:
                    queryTxt.setText(questionArray[questionCount]);
                    optionRadioBtn1.setText(colourOptionArray[0]);
                    optionRadioBtn2.setText(colourOptionArray[1]);
                    optionRadioBtn3.setText(colourOptionArray[2]);
                    break;
                case 1:
                    queryTxt.setText(questionArray[questionCount]);
                    optionRadioBtn1.setText(cartoonOptionArray[0]);
                    optionRadioBtn2.setText(cartoonOptionArray[1]);
                    optionRadioBtn3.setText(cartoonOptionArray[2]);
                    break;
                case 2:
                    queryTxt.setText(questionArray[questionCount]);
                    optionRadioBtn1.setText(animalOptionArray[0]);
                    optionRadioBtn2.setText(animalOptionArray[1]);
                    optionRadioBtn3.setText(animalOptionArray[2]);
                    break;
                case 3:
                    queryTxt.setText(questionArray[questionCount]);
                    optionRadioBtn1.setText(ballonOptionArray[0]);
                    optionRadioBtn2.setText(ballonOptionArray[1]);
                    optionRadioBtn3.setText(ballonOptionArray[2]);
                    break;
                case 4:
                    queryTxt.setText(questionArray[questionCount]);
                    optionRadioBtn1.setText(foodOptionArray[0]);
                    optionRadioBtn2.setText(foodOptionArray[1]);
                    optionRadioBtn3.setText(foodOptionArray[2]);
                    break;
            }
        } else {
            goBtn.setText("GO");
            queryTxt.setText("Pay Attention ! Click Go ");
            isGoBtn = true;
            quizImage.setVisibility(View.VISIBLE);
            optionRadioGroup.setVisibility(View.INVISIBLE);
            if (!isOptionRadioButtonSelected()) {
                Toast.makeText(getApplicationContext(), "Please select answer", Toast.LENGTH_SHORT).show();
            } else {
                if (userAnswer.equalsIgnoreCase(answerArray[questionCount].trim())) {
                    scoreCount++;
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                }
                questionCount++;
                if (questionCount < 5) {
                    quizImage.setImageResource(imageArray[questionCount]);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Your Score : "+String.valueOf(scoreCount)+"/5");
                    final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.success);
                    mediaPlayer.start();
                    builder.setPositiveButton("Finish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mediaPlayer.stop();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            finish();
                            startActivity(intent);
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        }
    }

    public boolean isOptionRadioButtonSelected() {
        boolean isChecked = false;
        if (optionRadioBtn1.isChecked()) {
            userAnswer = optionRadioBtn1.getText().toString();
            isChecked = true;
        } else if (optionRadioBtn2.isChecked()) {
            userAnswer = optionRadioBtn2.getText().toString();
            isChecked = true;
        } else if (optionRadioBtn3.isChecked()) {
            userAnswer = optionRadioBtn3.getText().toString();
            isChecked = true;
        } else {
            isChecked = false;
        }
        return isChecked;
    }
}
