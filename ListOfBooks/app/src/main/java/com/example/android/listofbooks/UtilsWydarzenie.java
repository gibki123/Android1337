package com.example.android.listofbooks;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class UtilsWydarzenie {

    private static final String TAG = UtilsWydarzenie.class.getSimpleName();

    private UtilsWydarzenie(){

    }

    public static List<Wydarzenie> polaczDaneJSON (String UrlStrony){
        URL url = createUrl(UrlStrony);

        String jsonResponse = null;

        try{
            jsonResponse = makeHTTPrequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Wydarzenie> wydarzenia = extractFeatureFromJSON(jsonResponse);

        return wydarzenia;


    }
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(TAG, "Problem ze zbudowaniem URL ", e);
        }
        return url;
    }

    private static String makeHTTPrequest(URL url) throws IOException {
        String jsonResponse = "";

        if(url == null){
            Log.e(TAG,"URL jest pusty");
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputstream = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputstream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputstream);
            } else {
                Log.e(TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputstream != null) {

                inputstream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();

    }

    private static List<Wydarzenie> extractFeatureFromJSON(String wydarzenieJSON){
        if(TextUtils.isEmpty(wydarzenieJSON)){
        return null;
        }

        List<Wydarzenie> Wydarzenia = new ArrayList<>();

        try{
            JSONObject baseJSONresponse = new JSONObject(wydarzenieJSON);

            JSONArray wydarzenieArray = baseJSONresponse.getJSONArray("articles");
            for(int i = 0;i<wydarzenieArray.length();i++ ){

                JSONObject obecneWydarzenie = wydarzenieArray.getJSONObject(i);

                JSONObject source = obecneWydarzenie.getJSONObject("source");

                String zrodlo = source.getString("name");

                String tytul = obecneWydarzenie.getString("title");

                String opis = obecneWydarzenie.getString("description");

                String url = obecneWydarzenie.getString("url");

                Wydarzenie wydarzenie = new Wydarzenie(tytul,opis,zrodlo,url);

                Wydarzenia.add(wydarzenie);
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return Wydarzenia;
    }

}
