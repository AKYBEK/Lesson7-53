package com.example.lesson7_52;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private static final String emptyString = "";
    private Button[] buttonsNumber, buttonsOperation;
    private Button buttonEqual;
    private TextView tvResult;

    private String firstNumber, secondNumber, number, result;
    private OperationStatus operationStatus = OperationStatus.DEFAULT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        funFindViewById();
        funNumber(buttonsNumber);
        funOperation(buttonsOperation);
        funEqual();
    }

    private void funFindViewById() {
        buttonsOperation = new Button[]{
                findViewById(R.id.button_plus), findViewById(R.id.button_minus),
                findViewById(R.id.button_multiple), findViewById(R.id.button_divided),
                findViewById(R.id.button_clear),
        };

        buttonsNumber = new Button[]{
                findViewById(R.id.button_zero), findViewById(R.id.button_one),
                findViewById(R.id.button_two), findViewById(R.id.button_three),
                findViewById(R.id.button_four), findViewById(R.id.button_five),
                findViewById(R.id.button_six), findViewById(R.id.button_seven),
                findViewById(R.id.button_eight), findViewById(R.id.button_nine)
        };

        buttonEqual = findViewById(R.id.button_equal);
        tvResult = findViewById(R.id.tv_result);
    }

    @SuppressLint("NonConstantResourceId")
    private void funOperation(Button[] buttonsOperation) {
        for (Button item : buttonsOperation) {
            item.setOnClickListener(v -> {
                switch (item.getId()) {
                    case R.id.button_plus:
                        operationStatus = OperationStatus.PLUS;
                        break;
                    case R.id.button_minus:
                        operationStatus = OperationStatus.MINUS;
                        break;
                    case R.id.button_multiple:
                        operationStatus = OperationStatus.MULTIPLE;
                        break;
                    case R.id.button_divided:
                        operationStatus = OperationStatus.DIVIDED;
                        break;
                    case R.id.button_clear:
                        operationStatus = OperationStatus.DEFAULT;
                        break;
                }
                firstNumber = number;
                number = emptyString;
                tvResult.setText(getResources().getString(R.string._0));
            });
        }
    }

    private void funEqual() {
        buttonEqual.setOnClickListener(v -> {
            secondNumber = number;
            number = emptyString;
            if (Objects.equals(firstNumber, emptyString))
                firstNumber = getResources().getString(R.string._0);
            if (Objects.equals(secondNumber, emptyString))
                secondNumber = getResources().getString(R.string._0);
            switch (operationStatus) {
                case DEFAULT:
                    break;
                case PLUS:
                    result = String.valueOf(Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber));
                    break;
                case MINUS:
                    result = String.valueOf(Double.parseDouble(firstNumber) - Double.parseDouble(secondNumber));
                    break;
                case MULTIPLE:
                    result = String.valueOf(Double.parseDouble(firstNumber) * Double.parseDouble(secondNumber));
                    break;
                case DIVIDED:
                    result = String.valueOf(Double.parseDouble(firstNumber) / Double.parseDouble(secondNumber));
                    break;
            }
            tvResult.setText(result);
            operationStatus = OperationStatus.DEFAULT;
            firstNumber = emptyString;
            secondNumber = emptyString;
        });
    }

    private void funNumber(Button[] buttonsNumber) {
        for (Button item : buttonsNumber) {
            item.setOnClickListener(v -> {
                if (Objects.equals(number, emptyString) || number == null)
                    number = String.valueOf(item.getText());
                else number = number + item.getText();
                tvResult.setText(number);
            });
        }
    }

    enum OperationStatus {
        DEFAULT, PLUS, MINUS, MULTIPLE, DIVIDED
    }
}