package com.example.sdarotcalculator2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class result extends AppCompatActivity {
    TextView tvFirstItem;
    ListView lv;
    boolean type;
    double firstNum, d, num, sum = 0;
    String[] arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvFirstItem = findViewById(R.id.tvFirstItem);
        lv = findViewById(R.id.lv);

        Intent get = getIntent();
        firstNum = get.getDoubleExtra("first", 1);
        d = get.getDoubleExtra("d", 1);
        type = get.getBooleanExtra("seriesChoice", false);

        arr = new String[20];
        if(type)
        {
            if(firstNum>100000){
                arr[0] = (beautifulNum(firstNum) + "");
            }
            else
            {
                arr[0] = (firstNum + "");
            }
            for (int i = 1; i < 20; i++)
            {
                num = firstNum * (Math.pow(d, i));
                if(num>100000)
                {
                    arr[i] = (beautifulNum(num) + "");
                }
                else
                {
                    arr[i] = (num + "");
                }
            }
        }
        else
        {
            if(firstNum > 100000) {
                arr[0] = beautifulNum(firstNum) + "";
            }
            else
            {
                arr[0] = firstNum + "";
            }

            for (int i = 1; i < 20; i++)
            {
                num = firstNum + d * i;
                if(num > 100000) {
                    arr[i] = beautifulNum(num) + "";
                }
                else
                {
                    arr[i] = num + "";
                }
            }
        }

        ArrayAdapter<String> adp = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arr);
        lv.setAdapter(adp);

        lv.setOnItemLongClickListener((parent, view, position, id) ->
        {
            PopupMenu popupMenu = new PopupMenu(this, view);
            popupMenu.getMenu().add("Place ");
            popupMenu.getMenu().add("Sum ");

            popupMenu.setOnMenuItemClickListener(item ->
            {
                if (item.getTitle().equals("Place "))
                {
                    tvFirstItem.setText("Place " + (position + 1));
                }
                else if (item.getTitle().equals("Sum "))
                {
                    if (type)
                    {
                        for (int i = 0; i <= position; i++)
                        {
                            sum += firstNum * Math.pow(d, i);
                        }
                    }
                    else
                    {
                        for (int i = 0; i <= position; i++)
                        {
                            sum += firstNum + d * i;
                        }
                    }
                    if(sum>100000)
                    {
                        tvFirstItem.setText("Sum: " + beautifulNum(sum));
                    }
                    else
                    {
                        tvFirstItem.setText("Sum: " + sum);
                    }
                }
                return true;
            });

            popupMenu.show();
            return true;
        });
        tvFirstItem.setText("first num until u press: " + firstNum);
    }
    public String beautifulNum(double value) {
        String scientificNotation = String.format("%.4e", value);
        String[] parts = scientificNotation.split("e");
        double base = Double.parseDouble(parts[0]) / 10.0;
        int exponent = Integer.parseInt(parts[1]) + 1;
        return String.format("%.4f * 10^%d", base, exponent);
    }
}
