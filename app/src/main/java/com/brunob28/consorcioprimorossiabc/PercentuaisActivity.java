package com.brunob28.consorcioprimorossiabc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;

import java.util.ArrayList;


public class PercentuaisActivity extends Activity {

    final Dados d = Dados.getInstance();
    int percentual1_temp=d.percentual1;
    int percentual2_temp=d.percentual2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percentuais);

        String[] p = new String[101];
        for (int i=0;i<=100;i++)
        p[i]=String.valueOf(i);

        final NumberPicker pickerPercentual1 = (NumberPicker) findViewById(R.id.lst_percentual1);
        pickerPercentual1.setMaxValue(100);
        pickerPercentual1.setMinValue(0);
        pickerPercentual1.setDisplayedValues(p);
        pickerPercentual1.setValue(d.percentual1);

        final NumberPicker pickerPercentual2 = (NumberPicker) findViewById(R.id.lst_percentual2);
        pickerPercentual2.setMaxValue(100);
        pickerPercentual2.setMinValue(0);
        pickerPercentual2.setDisplayedValues(p);
        pickerPercentual2.setValue(d.percentual2);

        NumberPicker.OnValueChangeListener onValueChanged =new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker,int oldVal,int newVal) {

                if(picker==pickerPercentual1)
                percentual1_temp=newVal;
                else
                percentual2_temp=newVal;

            }
        };

        pickerPercentual1.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        pickerPercentual1.setOnValueChangedListener(onValueChanged);

        pickerPercentual2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        pickerPercentual2.setOnValueChangedListener(onValueChanged);

    }

    public void clicouCancelar(View v) {

        Intent PercentuaisIntent = new Intent();
        setResult(-1, PercentuaisIntent);
        finish();

    }

    public void clicouOK(View v) {

        d.percentual1=percentual1_temp;
        d.percentual2=percentual2_temp;

        Intent PercentuaisIntent = new Intent();
        setResult(2, PercentuaisIntent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.porcentuais, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
