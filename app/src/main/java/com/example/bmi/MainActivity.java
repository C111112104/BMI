package com.example.bmi;



import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText height_editText;
    EditText weight_editText;
    SeekBar size_seekBar;
    Button calculate_button;
    TextView result_textView;
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        height_editText = (EditText) findViewById(R.id.height_editText);
        weight_editText = (EditText) findViewById(R.id.weight_editText);
        size_seekBar = (SeekBar) findViewById(R.id.size_seekBar);
        calculate_button = (Button) findViewById(R.id.calculate_button);
        result_textView = (TextView) findViewById(R.id.result_textView);

        ButtonHandler handler = new ButtonHandler();
        calculate_button.setOnClickListener(handler);

        OnSeekBarChangeHandler seekBarHandler = new OnSeekBarChangeHandler();
        size_seekBar.setOnSeekBarChangeListener(seekBarHandler);

        TextWatcherHandler editTextHandler = new TextWatcherHandler();
        height_editText.addTextChangedListener(editTextHandler);
        weight_editText.addTextChangedListener(editTextHandler);

        builder = new AlertDialog.Builder(MainActivity.this);

    }
    public class ButtonHandler implements View.OnClickListener{
        public void onClick(View view){
            try{
                double height = Double.parseDouble(height_editText.getText().toString()) / 100;
                double weight = Double.parseDouble(weight_editText.getText().toString());
                double bmi = weight / (height * height);
                if(bmi > 24){
                    result_textView.setText("您的BMI: " + String.format("%.2f" , bmi) + "\n過胖");
                    builder.setTitle("您的BMI: " + String.format("%.2f" , bmi));
                    builder.setMessage("過胖");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if(bmi < 18.5){
                    result_textView.setText("您的BMI: " + String.format("%.2f" , bmi) + "\n過輕");
                    builder.setTitle("您的BMI: " + String.format("%.2f" , bmi));
                    builder.setMessage("過輕");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    result_textView.setText("您的BMI: " + String.format("%.2f" , bmi) + "\n適中");
                    builder.setTitle("您的BMI: " + String.format("%.2f" , bmi));
                    builder.setMessage("適中");
                    AlertDialog dialog = builder.create();
                    dialog.show();
            }
            }catch (NumberFormatException e){
                result_textView.setText("身高和體重不能空白");
                Toast toast = Toast.makeText(MainActivity.this ,    "身高和體重不能空白", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
    public class OnSeekBarChangeHandler implements SeekBar.OnSeekBarChangeListener {
        public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
            double size =  progress * 0.4;
            result_textView.setTextSize((float) size);

        }// End of onProgressChanged

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    }// End of OnSeekBarChangeHandler class

    public class TextWatcherHandler implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, 	int count,int after) {}

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            result_textView.setText("請輸入身高體重");
        }// End of onTextChanged

        public void afterTextChanged(Editable s) {}
    }// End of class TextWatchHandler
}
