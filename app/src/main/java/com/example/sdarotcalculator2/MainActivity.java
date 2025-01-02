package com.example.sdarotcalculator2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Switch typeSwich;
    EditText etMult, etFirst;
    Button btnNext;

    double firstNum, d;
    String temp,strFn,strMn;
    boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        typeSwich = findViewById(R.id.seriesTypeSwitch);
        etMult = findViewById(R.id.etMult);
        etFirst = findViewById(R.id.etFirst);
        btnNext = findViewById(R.id.btnNext);
    }

    public void nxt(View view) {
        temp = etFirst.getText().toString();
        if (checkNum(temp))
        {
            firstNum = Double.parseDouble(temp);
            temp = etMult.getText().toString();

            if (checkNum(temp))
            {
                d = Double.parseDouble(temp);
                isChecked = typeSwich.isChecked();

                Intent si = new Intent(this, result.class);
                si.putExtra("first", firstNum);
                si.putExtra("d", d);
                si.putExtra("seriesChoice", isChecked);
                startActivity(si);
            }
            else Toast.makeText(this, "Invalid number!", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Invalid number!", Toast.LENGTH_SHORT).show();
    }

    public boolean checkNum(String x)
    {
        if (x.isEmpty()||x.equals(".")||x.equals(",")||x.equals("+")||x.equals("-")||x.equals("-.")) return false;
        return true;
    }
}