package com.example.android.cartoonquiz;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {
    int boxResult;
    private RadioGroup radioGroup;
    private EditText editText;
    TextView questionText;
    TextView questionNumberText;

    RadioButton option1;
    RadioButton option2;
    RadioButton option3;
    RadioButton option4;

    private int questionIndex = 1;
    boolean name1 = false;
    boolean name2 = false;
    boolean name3 = false;
    double correctQuestions;
    private static final String KEY_INDEX = "index";
    private static final String KEY_INDEX_2 = "index2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        if (savedInstanceState != null) {
            correctQuestions = savedInstanceState.getDouble
                    (KEY_INDEX, boxResult);
        }
        if (savedInstanceState != null) {
            questionIndex = savedInstanceState.getInt(KEY_INDEX_2, questionIndex);
        }
    }

    private CheckBox.OnCheckedChangeListener onCheckedChangeListener =
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    switch (buttonView.getId()) {
                        case R.id.checkBoxName1: {
                            name1 = isChecked;
                            break;
                        }
                        case R.id.checkBoxName2: {
                            name2 = isChecked;
                            break;
                        }
                        case R.id.checkBoxName3: {
                            name3 = isChecked;
                            break;
                        }
                    }
                }
            };

    public void setupView() {
        questionText = findViewById(R.id.questionText);
        questionNumberText = findViewById(R.id.question_number);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    public void submitButton(View view) {
        //Checks if we got to the end of the quiz, if that's true it exits the function.
        if (questionIndex == 7) return;
        //Declarations to all the required variables to change the app's appearance.
        switch (questionIndex) {
            case 1: {

                questionText.setText("What's the title of Cinderella's song in the movie " +
                        "with the same name?");
                option1.setText("A dream is wish your hearts makes;");
                option2.setText("I have a dream;");
                option3.setText("It's my dream you take;");
                option4.setText("I'm just a dreamer");
                checkAnswer();

                break;
            }
            case 2: {
                checkAnswer();
                questionText.setText("Who casts the voice of Rapunzel in \"Tangled\"");
                option1.setText("Hene Woods;");
                option2.setText("Donna Murpy;");
                option3.setText("Mandy Moor;");
                option4.setText("Miley Cyrus");

                break;
            }
            case 3: {
                checkAnswer();
                questionText.setText("In \"Moana\", Moana has a pet named");
                option1.setText("Hiphop;");
                option2.setText("Hanna;");
                option3.setText("Heihei;");
                option4.setText("Hammurabi");

                break;
            }
            //EditText question.
            case 4: {
                checkAnswer();
                questionText.setText("What's the name of Timon's song from \"The Lion King\"");
                radioGroup.removeAllViews();
                addEditText();

                break;
            }
            //Editable text field.
            case 5: {
                checkAnswer();
                questionText.setText("In \"Sleeping Beauty\" the names " +
                        "of Aurora's good fairies are:");
                radioGroup.removeAllViews();
                addCheckBoxGroup();

                break;
            }
            //Case 6 compute the points.
            case 6: {
                questionText.setText("Your result is: " + boxResult
                        + " points. out of 6.0 points");
                radioGroup.removeAllViews();

                break;
            }
            default:
                break;
        }
        //Recording the answer and move on to the next question.
        questionIndex++;

        if (questionIndex < 7) questionNumberText.setText("Question " + questionIndex + ":");
        else questionNumberText.setText("Congratulations! You completed the Quiz!");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    private void addCheckBoxGroup() {
        RadioGroup radioGroupLayout = findViewById(R.id.answerGroup);

        CheckBox checkBox = new CheckBox(MainActivity.this);
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBox.setText("Maleficient");
        checkBox.setTextColor(Color.WHITE);
        checkBox.setId(R.id.checkBoxName1);
        checkBox.setChecked(false);
        radioGroupLayout.addView(checkBox);

        checkBox = new CheckBox(MainActivity.this);
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBox.setText("Merryweather");
        checkBox.setTextColor(Color.WHITE);
        checkBox.setId(R.id.checkBoxName2);
        radioGroupLayout.addView(checkBox);

        checkBox = new CheckBox(MainActivity.this);
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBox.setText("Flora");
        checkBox.setId(R.id.checkBoxName3);
        checkBox.setTextColor(Color.WHITE);
        checkBox.setChecked(false);
        radioGroupLayout.addView(checkBox);
    }

    private void addEditText() {
        RadioGroup radioGroupLayout = findViewById(R.id.answerGroup);
        editText = new EditText(MainActivity.this);

        editText.setHint("Matata");
        editText.setTextColor(WHITE);
        editText.setWidth(radioGroupLayout.getWidth());
        editText.setId(R.id.answerEditText);
        radioGroupLayout.addView(editText);
    }

    public void checkAnswer() {
        radioGroup = findViewById(R.id.answerGroup);
        switch (questionIndex) {
            case 1: {
                if (radioGroup.getCheckedRadioButtonId() == R.id.option2) {
                    boxResult += 1;
                } else {
                    boxResult = 0;
                    break;
                }
            }
            case 2: {
                if (radioGroup.getCheckedRadioButtonId() == R.id.option1) {
                    boxResult += 1;
                } else {
                    boxResult = 0;
                    break;
                }
            }
            case 3: {
                if (radioGroup.getCheckedRadioButtonId() == R.id.option3) {
                    boxResult += 1;
                } else {
                    boxResult = 0;
                    break;
                }
            }
            case 4: {
                if (radioGroup.getCheckedRadioButtonId() == R.id.option3) {
                    boxResult += 1;
                } else {
                    boxResult = 0;
                    break;
                }
            }
            case 5: {
                editText = findViewById(R.id.answerEditText);
                if (editText.getText().toString().equals("Hakuna Matata") ||
                        editText.getText().toString().equals("hakuna matata")) {
                    boxResult = 1;
                } else {
                    boxResult = 0;
                }
            }
            case 6: {
                if (name1 && name2) {
                    boxResult = 1;
                } else {
                    boxResult = 0;
                }
                break;
            }
            default:
                break;
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putDouble(KEY_INDEX, boxResult);
        savedInstanceState.putInt(KEY_INDEX_2, questionIndex);
    }
}
