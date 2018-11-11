package com.example.android.listofbooks;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class WydarzenieLoader extends AsyncTaskLoader<List<Wydarzenie>> {


    private static final String TAG = WydarzenieLoader.class.getName();

    private  String PolskieNewsyUrl;

    public WydarzenieLoader(Context context, String url)
    {
        super(context);
        PolskieNewsyUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Wydarzenie> loadInBackground() {
        if(PolskieNewsyUrl == null){
        return null;
        }
        List<Wydarzenie> wydarzenia = UtilsWydarzenie.polaczDaneJSON(PolskieNewsyUrl);

        return wydarzenia;
    }
}
