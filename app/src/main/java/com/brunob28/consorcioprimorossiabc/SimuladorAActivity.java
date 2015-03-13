package com.brunob28.consorcioprimorossiabc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


public class SimuladorAActivity extends Activity {

    final Dados d = Dados.getInstance();
    private static final DecimalFormat DF = new DecimalFormat();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulador_a);

    }

    public void clicouGrupo(View v) {
// do something when the button is clicked

        //startActivity(new Intent(getApplicationContext(), GrupoActivity.class));
        Intent i = new Intent(this,GrupoActivity.class);
        startActivityForResult(i, 0);

    }


    public void sairOnClick(View v) {
// do something when the button is clicked

        d.grupo_escolhido=0;
        d.valor_credito_escolhido=0;
        d.percentual1=0;
        d.percentual2=0;

       finish();

    }

    public void clicouRecalcular(View v) {

        d.grupo_escolhido=0;
        TextView t = (TextView) findViewById(R.id.txt_grupo);
        t.setText("Escolha");

        t = (TextView) findViewById(R.id.txt_valorcredito);
        t.setText("");
        d.valor_credito_escolhido=0;

        d.percentual1=0;
        d.percentual2=0;

        t = (TextView) findViewById(R.id.txt_percentual1);
        t.setText("");
        t = (TextView) findViewById(R.id.txt_percentual2);
        t.setText("");

        t=(TextView) findViewById(R.id.txt_d14);
        t.setText("");
        t=(TextView) findViewById(R.id.txt_d15);
        t.setText("");
        t=(TextView) findViewById(R.id.txt_c16);
        t.setText("");
        t=(TextView) findViewById(R.id.txt_d16);
        t.setText("");
        t=(TextView) findViewById(R.id.txt_c21);
        t.setText("");
        t=(TextView) findViewById(R.id.txt_c22);
        t.setText("");


    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {

        if(resultCode==0) {
            //Retornou com grupo
            TextView t = (TextView) findViewById(R.id.txt_grupo);
            t.setText(d.getGrupo(d.grupo_escolhido));

            t = (TextView) findViewById(R.id.txt_valorcredito);
            t.setText("Escolha");
            d.valor_credito_escolhido=0;

            t=(TextView) findViewById(R.id.txt_d14);
            t.setText("");
            t=(TextView) findViewById(R.id.txt_d15);
            t.setText("");
            t=(TextView) findViewById(R.id.txt_c16);
            t.setText("");
            t=(TextView) findViewById(R.id.txt_d16);
            t.setText("");
            t=(TextView) findViewById(R.id.txt_c21);
            t.setText("");
            t=(TextView) findViewById(R.id.txt_c22);
            t.setText("");

        }
        else
            if(resultCode==1) {
                //Retornou com valor credito
                TextView t = (TextView) findViewById(R.id.txt_valorcredito);
                t.setText(d.getValores(d.valor_credito_escolhido));
                t = (TextView) findViewById(R.id.txt_percentual1);
                if(t.getText().toString().compareTo("")==0) {
                    t.setText("0%");
                    t = (TextView) findViewById(R.id.txt_percentual2);
                    t.setText("0%");
                }
                    else
                    calcula();
            }
        else
            if(resultCode==2) {
                //Retornou percentuais
                TextView t = (TextView) findViewById(R.id.txt_percentual1);
                t.setText(d.percentual1 + "%");
                t = (TextView) findViewById(R.id.txt_percentual2);
                t.setText(d.percentual2 + "%");
                calcula();
            }

    }

    public void clicouValorCredito(View v) {
// do something when the button is clicked

        TextView t=(TextView) findViewById(R.id.txt_valorcredito);
        if(t.getText().toString().compareTo("")!=0) {

            Intent i = new Intent(this,ValorCreditoActivity.class);
            startActivityForResult(i, 1);

        }

    }

    public void clicouPercentuais(View v) {
// do something when the button is clicked

        TextView t=(TextView) findViewById(R.id.txt_percentual1);
        if(t.getText().toString().compareTo("")!=0) {

            Intent i = new Intent(this,PercentuaisActivity.class);
            startActivityForResult(i, 2);

        }

    }

    public void calcula() {

        int c14=d.percentual1;
        int c15=d.percentual2;

        String c3_ = new String();
        c3_ = d.getValores(d.valor_credito_escolhido);
        c3_ = c3_.replace("R$ ","");
        c3_ = c3_.replace(".","");
        c3_ = c3_.replace(",",".");
        double c3=Double.parseDouble(c3_);

        double c4 = d.getPrazo(d.grupo_escolhido);
        double c5 = d.getTaxaadm(d.grupo_escolhido);
        double c6 = d.getFundoreserva(d.grupo_escolhido);
        double c9 = d.getSegurovida(d.grupo_escolhido);
        double c10 = d.getQuebragarantia(d.grupo_escolhido);

        double c7 = c3+(c3*(c5+c6)/100);

        double c8=c7/c4;
        double d9=c7*c9/100.0;
        double d10=c7*c10/100.0;
        double c11=c8+d9+d10;
        //float c12=c8+d10;
        double c13=c11*c4;
        double d14 = c3*c14/100.0;
        double d15 = c3*c15/100.0;
        int c16=c14+c15;
        double d16=d14+d15;
        double d17=1.0;
        double c17=c11*d17;
        //float d18=2;
        //float c18=c11*d18;
        double c19=c13-d14-d15-c17;
        //float c20=0;
        //if(c12!=0)
        //c20=(d14+d15)/c12;
        double c21=c3-d15;
        double c22=0;
        if(c4-d17!=0)
        c22=c19/(c4-d17);

        TextView t=(TextView) findViewById(R.id.txt_d14);
        t.setText(toCurrency(d14));
        t=(TextView) findViewById(R.id.txt_d15);
        t.setText(toCurrency(d15));
        t=(TextView) findViewById(R.id.txt_c16);
        t.setText(c16 + "%");
        t=(TextView) findViewById(R.id.txt_d16);
        t.setText(toCurrency(d16));
        t=(TextView) findViewById(R.id.txt_c21);
        t.setText(toCurrency(c21));
        t=(TextView) findViewById(R.id.txt_c22);
        t.setText(toCurrency(c22));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.simulador_a, menu);
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

    public static String toCurrency(Double d) {
        if (d == null || "".equals(d) || "NaN".equals(d)) {
            return " - ";
        }
        BigDecimal bd = new BigDecimal(d);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        DecimalFormatSymbols symbols = DF.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        String ret = DF.format(bd) + "";
        if (!ret.contains(".")) {
            ret += ".00";
        }
        if (ret.substring(ret.length()-2,ret.length()-1).equals(".")) {
            ret += "0";
        }
        ret=ret.replace(",",";");
        ret=ret.replace(".",",");
        ret="R$ " + ret.replace(";",".");
        return ret;

    }

}
