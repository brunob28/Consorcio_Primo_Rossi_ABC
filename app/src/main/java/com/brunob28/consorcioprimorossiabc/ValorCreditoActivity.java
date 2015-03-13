package com.brunob28.consorcioprimorossiabc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;

import java.util.ArrayList;


public class ValorCreditoActivity extends Activity {

    final Dados d = Dados.getInstance();
    int valor_credito_temp;
    int comeco=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valor_credito);

        String[] v = new String[d.valores.size()];
        v = d.valores.toArray(v);
        ArrayList<String> lista = new ArrayList<String>();

        int x;
        int fim=0;

        //Encontra o grupo na lista de valores
        for(x=0;x<v.length;x++)
        {

            if(comeco>0 && fim==0)
            {
                if(v[x].indexOf("R$")>=0)
                lista.add(v[x]);
                else
                fim=1;

            }

            if(v[x].equals(d.getGrupo(d.grupo_escolhido)) && comeco==0) {
                comeco=x+1;
            }

        }

        valor_credito_temp=comeco;

        String[] vc = new String[lista.size()];
        vc = lista.toArray(vc);

        final NumberPicker pickerValores = (NumberPicker) findViewById(R.id.lst_valor_credito);
        pickerValores.setMaxValue(vc.length-1);
        pickerValores.setMinValue(0);
        pickerValores.setDisplayedValues(vc);
        if(d.valor_credito_escolhido>0)
           pickerValores.setValue(d.valor_credito_escolhido-comeco);

        NumberPicker.OnValueChangeListener onValueChanged =new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker,int oldVal,int newVal) {
                valor_credito_temp=newVal + comeco;
            }
        };

        pickerValores.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        pickerValores.setOnValueChangedListener(onValueChanged);


    }

    public void clicouCancelar(View v) {

        Intent valor_creditoIntent = new Intent();
        setResult(-1, valor_creditoIntent);
        finish();

    }

    public void clicouOK(View v) {

        d.valor_credito_escolhido=valor_credito_temp;

        Intent valor_creditoIntent = new Intent();
        setResult(1, valor_creditoIntent);
        finish();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.valor_credito, menu);
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
