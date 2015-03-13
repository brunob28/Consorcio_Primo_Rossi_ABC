package com.brunob28.consorcioprimorossiabc;

import java.util.ArrayList;

/**
 * Created by brunob28 on 10/3/14.
 */
public class Dados {

    private static Dados instance;
    public static ArrayList<String> grupo = new ArrayList<String>();
    public static ArrayList<String> tipo = new ArrayList<String>();
    public static ArrayList<String> prazo = new ArrayList<String>();
    public static ArrayList<String> taxaadm = new ArrayList<String>();
    public static ArrayList<String> fundoreserva = new ArrayList<String>();
    public static ArrayList<String> segurovida = new ArrayList<String>();
    public static ArrayList<String> quebragarantia = new ArrayList<String>();
    public static ArrayList<String> valores = new ArrayList<String>();
    public static int percentual1=0;
    public static int percentual2=0;
    public static int parcelas=0;

    public int grupo_escolhido;
    public int valor_credito_escolhido;

    public void setGrupo (String g) {
        Dados.grupo.add(g);
    }
    public String getGrupo (int i) {
        return grupo.get(i);
    }

    public void setTipo (String g) {
        Dados.tipo.add(g);
    }
    public String getTipo (int i) { return tipo.get(i); }

    public void setPrazo (String g) { Dados.prazo.add(g); }
    public int getPrazo (int i) { return Integer.parseInt(prazo.get(i)); }

    public void setTaxaadm (String g) {
        Dados.taxaadm.add(g);
    }
    public float getTaxaadm (int i) { return Float.parseFloat(taxaadm.get(i).replace(",",".")); }

    public void setFundoreserva (String g) { Dados.fundoreserva.add(g);  }
    public float getFundoreserva (int i) { return Float.parseFloat(fundoreserva.get(i).replace(",",".")); }

    public void setSegurovida (String g) { Dados.segurovida.add(g);  }
    public float getSegurovida (int i) { return Float.parseFloat(segurovida.get(i).replace(",",".")); }

    public void setQuebragarantia (String g) { Dados.quebragarantia.add(g);  }
    public float getQuebragarantia (int i) { return Float.parseFloat(quebragarantia.get(i).replace(",",".")); }

    public void setValores (String g) { Dados.valores.add(g);  }
    public String getValores (int i) { return valores.get(i); }

    public static synchronized Dados getInstance() {
        if(instance==null) {
            instance = new Dados();
        }
        return instance;
    }

}
