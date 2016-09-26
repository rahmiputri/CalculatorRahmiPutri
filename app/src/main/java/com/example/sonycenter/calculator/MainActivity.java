package com.example.sonycenter.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private static final String TAG = "MainActivity";
    private StringBuilder stringInput;
    private int numVal;
    private EditText input;
    char operation = ' ';
    String tmp, n = "0";
    double hasil = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout
        setContentView(R.layout.activity_main);
        //set superscript on button X2
        Button buttonX2 = (Button) findViewById(R.id.buttonX2);
        buttonX2.setText(Html.fromHtml("x<sup>2</sup>"));
        //at initial start reset input text to 0
        stringInput=new StringBuilder("0");
        input = (EditText) findViewById(R.id.input);
        input.setText(stringInput);
        //set listener on all buttons
        ViewGroup rootView = (ViewGroup) findViewById(R.id.activity_main);
        setAllListenerOnButton(rootView);
        //init variables
        numVal=0;
    }

    public void setAllListenerOnButton(ViewGroup parentView){
        int numChildViews=parentView.getChildCount();
        for (int i = 0; i < numChildViews; i++) {
            View childView = parentView.getChildAt(i);
            if(childView instanceof Button){
                Button genericButton = (Button) childView;
                genericButton.setOnClickListener(this);
            } else if(childView instanceof ImageButton){
                ImageButton genericImageButton = (ImageButton) childView;
                genericImageButton.setOnClickListener(this);
            }
            else if(childView instanceof ViewGroup){
                setAllListenerOnButton((ViewGroup) childView);
            }
        }
    }

    public void displayResult(View v){
        Log.v(TAG, "Displayed");
        input.setText("Result");
    }

    @Override
    public void onClick(View view) {
        numVal = Integer.parseInt(stringInput.toString());
        //before concatenating always read first from numeric values
        if(numVal==0){
            stringInput = new StringBuilder("");
        }
        switch (view.getId())
        {
            case R.id.buttonCC:
                stringInput=new StringBuilder("0");
                input.setText("0");
                Log.v(TAG, "clear text");
                break;
            case R.id.imageButtonBack:
                stringInput.deleteCharAt(stringInput.length()-1);
                Log.v(TAG, "current string: " + stringInput);
                input.setText(stringInput);
                break;
            case R.id.buttonResult:
                input.setText("result");
                break;

            case R.id.buttonX2:
                operation = '^';
                tmp = Double.parseDouble(EditText.getText().toString());
                hasil = Math.pow(tmp, 2);
                EditText.setText(String.valueOf(hasil));
                n = String.valueOf(hasil);
                break;

            case R.id.buttonPlus:
                operation = '+';
                hitung();
                break;

            case R.id.buttonMinus:
                operation = '-';
                hitung();
                break;

            case R.id.buttonX:
                operation = '*';
                hitung();
                break;

            case R.id.imageButtonDiv:
                operation = ':';
                hitung();
                break;

            case R.id.buttonPercent:
                operation = '%';
                hitung();
                break;

            case R.id.buttonSqrt:
                tmp = Double.parseDouble(EditText.getText().toString());
                hasil = Math.sqrt(tmp, 2);
                EditText.setText(String.valueOf(hasil));
                n = String.valueOf(hasil);
                break;

            default:
                //handle multiple view click events
                Button numButton = (Button)view;
                String text = numButton.getText().toString();
                stringInput.append(text);
                //Log.v(TAG, "text: " + text);
                input.setText(stringInput);
        }
    }

    public void buttonResultClicked(View v){
        hitung();
    }

    private void hitung(){
        double number = Double.parseDouble(n);
        n = "0";
        if (operation == ' ') {
            hasil = number;
        }
        else if(operation == '+') {
            hasil += number;
        }
        else if(operation == '-') {
            hasil -= number;
        }
        else if(operation == '*') {
            hasil *= number;
        }
        else if(operation == ':') {
            hasil /= number;
        }
        else if(operation == '%') {
            hasil = number/100;
        }
        else if(operation == '=') {
        }
        EditText.setText(String.valueOf(hasil));
        }

}
