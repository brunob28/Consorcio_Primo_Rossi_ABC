package com.brunob28.consorcioprimorossiabc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;


public class ParcelasActivity extends Activity {

    final Dados d = Dados.getInstance();
    int parcelas_temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelas);

        String[] p = new String[181];
        for (int i=0;i<=180;i++)
            p[i]=String.valueOf(i);

        final NumberPicker pickerParcelas = (NumberPicker) findViewById(R.id.lst_parcelas);
        pickerParcelas.setMaxValue(180);
        pickerParcelas.setMinValue(0);
        pickerParcelas.setDisplayedValues(p);
        pickerParcelas.setValue(d.parcelas);

        NumberPicker.OnValueChangeListener onValueChanged =new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker,int oldVal,int newVal) {

              parcelas_temp=newVal;

            }
        };

        pickerParcelas.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        pickerParcelas.setOnValueChangedListener(onValueChanged);

    }

    public void clicouCancelar(View v) {

        Intent ParcelasIntent = new Intent();
        setResult(-1, ParcelasIntent);
        finish();

    }

    public void clicouOK(View v) {

        d.parcelas=parcelas_temp;

        Intent ParcelasIntent = new Intent();
        setResult(3, ParcelasIntent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.parcelas, menu);
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
