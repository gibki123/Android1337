package com.example.android.listofbooks;

public class Wydarzenie {

    private String zrodlo;

    private String tytul;

    private String opis;

    private String url;

    public Wydarzenie(String Tytul, String Opis, String Zrodlo, String Url)
    {
        tytul = Tytul;
        opis = Opis;
        zrodlo = Zrodlo;
        url = Url;

    }

    public String getTytul() { return tytul; }

    public String getUrl() { return url; }

    public String getZrodlo() { return zrodlo; }

    public String getOpis() { return opis; }
}

