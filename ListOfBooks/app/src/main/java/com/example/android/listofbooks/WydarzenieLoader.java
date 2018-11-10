package com.example.android.listofbooks;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

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

    @Nullable
    @Override
    public List<Wydarzenie> loadInBackground() {
        if(PolskieNewsyUrl == null){
        return null;
        }
        List<Wydarzenie> wydarzenia = UtilsWydarzenie.polaczDaneJSON(PolskieNewsyUrl);

        return wydarzenia;
    }
}
