package com.brunob28.consorcioprimorossiabc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class MenuActivity extends Activity {

    private static final String BASE64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmWijlkcyzFOEts9OOzZEkQt794ML6ORXBx0N7PLy29P8eqUzUc4wrUXtjuPZaJPN37QaN50jDIWD+jd5GFXZCinBfhNGxWsL2Gj+dgBaCGESfvRPM9nI7jQglmC1vFkhQXnUleJngW66nqG1AhRtn1gsEy5ibIY4U28yLiHRk2Tbq5nT/pvPxMxFXF9lwZS0840oBo1+yZj96qR9Ze2mn1yB0lWFy7FkuruR35plBqn9jWp0z/oxJCvu4rQhrOnA/AXBscyN3a8Prw+lU0SJB1UH0wBJfPip7KgN7mPtHTJ3D1wcP6D2PVs5kLY3CikBO7MWZ26z32n6vCXVJE6TIwIDAQAB";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final Dados d = Dados.getInstance();

        final String TAG = MenuActivity.class.getSimpleName();

        //final View imagem_menu = findViewById(R.id.menuView);
        //imagem_menu.setAlpha((float)0.5);

        final Thread le_xml = new Thread() {

            @Override
            public void run() {

                try {
                    // Create a URL for the desired page
                    URL url = new URL("http://www.consorcioprimorossiabc.com.br/xml.asp");

                    // Read all the text returned by the server
                    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                    String str;
                    while ((str = in.readLine()) != null) {
                        // str is one line of text; readLine() strips the newline character(s)
                        if(str.indexOf("nome")>0) {
                            d.setGrupo(str.replaceFirst("<nome>", "").replaceFirst("</nome>", ""));
                            //Log.i(TAG, str);
                        }
                        else
                            if(str.indexOf("tipo")>0) {
                            d.setTipo(str.replaceFirst("<tipo>", "").replaceFirst("</tipo>", ""));
                            }
                            else
                                if(str.indexOf("prazo")>0) {
                                    d.setPrazo(str.replaceFirst("<prazo>", "").replaceFirst("</prazo>", ""));
                                }
                                else
                                    if(str.indexOf("taxaadm")>0) {
                                        d.setTaxaadm(str.replaceFirst("<taxaadm>", "").replaceFirst("</taxaadm>", ""));
                                    }
                                    else
                                        if(str.indexOf("fundoreserva")>0) {
                                            d.setFundoreserva(str.replaceFirst("<fundoreserva>", "").replaceFirst("</fundoreserva>", ""));
                                        }
                                        else
                                            if(str.indexOf("segurovida")>0) {
                                                d.setSegurovida(str.replaceFirst("<segurovida>", "").replaceFirst("</segurovida>", ""));
                                            }
                                            else
                                                if(str.indexOf("quebragarantia")>0) {
                                                     d.setQuebragarantia(str.replaceFirst("<quebragarantia>", "").replaceFirst("</quebragarantia>", ""));
                                                }

                    }
                    in.close();

                    this.interrupt();

                } catch (MalformedURLException e) {
                } catch (IOException e) {
                }


            }
        };

        le_xml.start();

        try {
            le_xml.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

// XML1

        final Thread le_xml1 = new Thread() {

            @Override
            public void run() {

                try {
                    // Create a URL for the desired page
                    URL url = new URL("http://www.consorcioprimorossiabc.com.br/xml1.asp");

                    // Read all the text returned by the server
                    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                    String str;
                    while ((str = in.readLine()) != null) {
                        // str is one line of text; readLine() strips the newline character(s)
                        if(str.indexOf("valor")>0 && str.indexOf("valores")<0) {
                            d.setValores(str.replaceFirst("<valor>", "").replaceFirst("</valor>", ""));
                        }

                    }
                    in.close();

                    this.interrupt();

                } catch (MalformedURLException e) {
                } catch (IOException e) {
                }


            }
        };

        le_xml1.start();

        try {
            le_xml1.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void buttonAOnClick(View v) {
// do something when the button is clicked

    startActivity(new Intent(getApplicationContext(), SimuladorAActivity.class));

    }

    public void buttonBOnClick(View v) {
// do something when the button is clicked

        startActivity(new Intent(getApplicationContext(), SimuladorBActivity.class));

    }

    public void buttonCOnClick(View v) {
// do something when the button is clicked

        startActivity(new Intent(getApplicationContext(), SimuladorCActivity.class));

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

    public InputStream getUrlData(String url) throws URISyntaxException, ClientProtocolException, IOException {

        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet method = new HttpGet(new URI(url));
        HttpResponse res = client.execute(method);
        return res.getEntity().getContent();
    }

}
