package com.brunob28.consorcioprimorossiabc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;


public class GrupoActivity extends Activity {

    final Dados d = Dados.getInstance();
    int grupo_temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);

        String[] g = new String[d.grupo.size()];
        g = d.grupo.toArray(g);

        //final String TAG = MenuActivity.class.getSimpleName();

        final NumberPicker pickerGrupo = (NumberPicker) findViewById(R.id.lst_grupo);
        pickerGrupo.setMaxValue(g.length - 1);
        pickerGrupo.setMinValue(0);
        pickerGrupo.setDisplayedValues(g);
        if(d.grupo_escolhido>0)
        pickerGrupo.setValue(d.grupo_escolhido);

        NumberPicker.OnValueChangeListener onValueChanged =new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker,int oldVal,int newVal) {
                grupo_temp=newVal;
                //Log.i(TAG, newVal + "");
            }
        };

        pickerGrupo.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        pickerGrupo.setOnValueChangedListener(onValueChanged);


    }

    public void clicouCancelar(View v) {

        Intent grupoIntent = new Intent();
        setResult(-1, grupoIntent);
        finish();

    }

    public void clicouOK(View v) {

        d.grupo_escolhido=grupo_temp;

        Intent grupoIntent = new Intent();
        setResult(0, grupoIntent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.grupo, menu);
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
