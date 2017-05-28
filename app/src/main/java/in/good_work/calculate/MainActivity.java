package in.good_work.calculate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    TextView tvDisplay;
    String numberFirst;
    String numberSecond;
    String operand;
    String operation;
    private Animation btnAnnimationClick;

    boolean firstDigitEnter = false;
    boolean startCalculate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAnnimationClick = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_click);
        initCalc();

        tvDisplay = (TextView) findViewById(R.id.tv_main_display);

        Button btnPlus = (Button) findViewById(R.id.btn_main_plus);
        Button btnMinus = (Button) findViewById(R.id.btn_main_minus);
        Button btnMultiple = (Button) findViewById(R.id.btn_main_multiple);
        Button btnDiv = (Button) findViewById(R.id.btn_main_div);
        Button btnClear = (Button) findViewById(R.id.btn_main_clear);

        Button btn0 = (Button) findViewById(R.id.btn_main_0);
        Button btn1 = (Button) findViewById(R.id.btn_main_1);
        Button btn2 = (Button) findViewById(R.id.btn_main_2);
        Button btn3 = (Button) findViewById(R.id.btn_main_3);
        Button btn4 = (Button) findViewById(R.id.btn_main_4);
        Button btn5 = (Button) findViewById(R.id.btn_main_5);
        Button btn6 = (Button) findViewById(R.id.btn_main_6);
        Button btn7 = (Button) findViewById(R.id.btn_main_7);
        Button btn8 = (Button) findViewById(R.id.btn_main_8);
        Button btn9 = (Button) findViewById(R.id.btn_main_9);

        Button btnCalc = (Button) findViewById(R.id.btn_main_calc);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        btnCalc.setOnClickListener(this);

        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiple.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        btn0.setOnTouchListener(this);
        btn1.setOnTouchListener(this);
        btn2.setOnTouchListener(this);
        btn3.setOnTouchListener(this);
        btn4.setOnTouchListener(this);
        btn5.setOnTouchListener(this);
        btn6.setOnTouchListener(this);
        btn7.setOnTouchListener(this);
        btn8.setOnTouchListener(this);
        btn9.setOnTouchListener(this);

        btnCalc.setOnTouchListener(this);

        btnPlus.setOnTouchListener(this);
        btnMinus.setOnTouchListener(this);
        btnMultiple.setOnTouchListener(this);
        btnDiv.setOnTouchListener(this);
        btnClear.setOnTouchListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_main_calc) {
            makeText(MainActivity.this, "Посчитаем!", Toast.LENGTH_LONG).show();
            startCalculate = true;
        } else if (v.getId() == R.id.btn_main_plus || v.getId() == R.id.btn_main_minus ||
                v.getId() == R.id.btn_main_multiple || v.getId() == R.id.btn_main_div) {
            Button op = (Button) findViewById(v.getId());
            operand = op.getText().toString();
            firstDigitEnter = true;
        } else if (v.getId() == R.id.btn_main_clear) {
            initCalc();
        } else {
            Button digitEnter = (Button) findViewById(v.getId());
            if (firstDigitEnter) {
                numberSecond += digitEnter.getText().toString();
                operation += " " + numberSecond;
            } else {
                numberFirst += digitEnter.getText().toString();
            }
        }
        showDisplay();
        if (startCalculate) {
            initCalc();
        }
    }

    private void showDisplay() {
        operation = getString(R.string.tv_main_start_calculate_text);

        if (!numberFirst.equals("")) {
            operation = numberFirst;
        }

        if (!operand.equals("")) {
            operation += " " + operand;
        }

        if (!numberSecond.equals("")) {
            operation += " " + numberSecond;
        }

        if (startCalculate) {
            if (!numberFirst.equals("") && !numberSecond.equals("")) {
                float sum = calc(numberFirst, operand, numberSecond);
                if ((int) sum == sum) {
                    operation += " = " + (int) sum;
                } else {
                    operation += " = " + sum;
                }
            } else {
                makeText(MainActivity.this, "Аргументы не верны!", Toast.LENGTH_LONG).show();
            }
        }

        tvDisplay.setText(operation);
    }

    private void initCalc() {

        firstDigitEnter = false;
        startCalculate = false;
        numberFirst = "";
        numberSecond = "";
        operation = "";
        operand = "";
    }

    private float calc(String firstValue, String operand, String secondValue) {
        float fNumber = Integer.valueOf(firstValue);
        float sNumber = Integer.valueOf(secondValue);
        float result = 0;

        switch (operand) {
            case "+":
                result = fNumber + sNumber;
                break;
            case "-":
                result = fNumber - sNumber;
                break;
            case "*":
                result = fNumber * sNumber;
                break;
            case "/":
                result = fNumber / sNumber;
                break;
            default:
                Toast.makeText(getApplicationContext(), "нету операции", Toast.LENGTH_LONG).show();
        }

        return result;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                v.startAnimation(btnAnnimationClick);
                break;
            case MotionEvent.ACTION_UP:
                v.clearAnimation();
                break;
        }
        return false;

    }
}
