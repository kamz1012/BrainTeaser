package com.sami.brainteaser;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class PictureActivity extends AppCompatActivity {
    Button goBtn;
    int imageArray[], questionCount = 0, scoreCount = 1;
    String questionArray[], colourOptionArray[], answerArray[], cartoonOptionArray[], animalOptionArray[], ballonOptionArray[], foodOptionArray[];
    String userAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_pic);
        goBtn = (Button) findViewById(R.id.goBtn);
        final ImageView image = (ImageView) findViewById(R.id.quiz_img);
        imageArray = new int[]{R.drawable.a_colour_txt_img, R.drawable.b_cartoon, R.drawable.c_animal, R.drawable.d_ballon, R.drawable.e_food};
        questionArray = getResources().getStringArray(R.array.question_array);
        colourOptionArray = getResources().getStringArray(R.array.colour_txt_option_array);
        cartoonOptionArray = getResources().getStringArray(R.array.cartoon_odd_option_array);
        animalOptionArray = getResources().getStringArray(R.array.animal_odd_option_array);
        ballonOptionArray = getResources().getStringArray(R.array.ballon_count_option_array);
        foodOptionArray = getResources().getStringArray(R.array.food_odd_option_array);
        answerArray = getResources().getStringArray(R.array.answer_array);
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater layoutInflater = getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.dialog, null);
                TextView questionTxt = (TextView) view.findViewById(R.id.question_txt);
                final RadioButton option1 = (RadioButton) view.findViewById(R.id.option_1);
                final RadioButton option2 = (RadioButton) view.findViewById(R.id.option_2);
                final RadioButton option3 = (RadioButton) view.findViewById(R.id.option_3);
                if (questionCount < 5) {
                    switch (questionCount) {
                        case 0:
                            questionTxt.setText(questionArray[questionCount]);
                            option1.setText(colourOptionArray[0]);
                            option2.setText(colourOptionArray[1]);
                            option3.setText(colourOptionArray[2]);
                            break;
                        case 1:
                            questionTxt.setText(questionArray[questionCount]);
                            option1.setText(cartoonOptionArray[0]);
                            option2.setText(cartoonOptionArray[1]);
                            option3.setText(cartoonOptionArray[2]);
                            break;
                        case 2:
                            questionTxt.setText(questionArray[questionCount]);
                            option1.setText(animalOptionArray[0]);
                            option2.setText(animalOptionArray[1]);
                            option3.setText(animalOptionArray[2]);
                            break;
                        case 3:
                            questionTxt.setText(questionArray[questionCount]);
                            option1.setText(ballonOptionArray[0]);
                            option2.setText(ballonOptionArray[1]);
                            option3.setText(ballonOptionArray[2]);
                            break;
                        case 4:
                            questionTxt.setText(questionArray[questionCount]);
                            option1.setText(foodOptionArray[0]);
                            option2.setText(foodOptionArray[1]);
                            option3.setText(foodOptionArray[2]);
                            break;
                    }
                    builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (option1.isChecked()) {
                                userAnswer = option1.getText().toString();
                            } else if (option2.isChecked()) {
                                userAnswer = option2.getText().toString();
                            } else if (option3.isChecked()) {
                                userAnswer = option3.getText().toString();
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter answer", Toast.LENGTH_SHORT).show();
                            }
                            if (userAnswer.equalsIgnoreCase(answerArray[questionCount].trim())) {
                                scoreCount++;
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                            }
                            questionCount++;
                            if (questionCount <= 4) {
                                image.setImageResource(imageArray[questionCount]);
                            } else {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("score", scoreCount + "");
                                finish();
                                startActivity(intent);
                            }

                        }

                    });
                    builder.setView(view);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }
            }
        });
    }

}
