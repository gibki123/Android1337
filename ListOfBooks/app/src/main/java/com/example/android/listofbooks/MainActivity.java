package com.example.android.listofbooks;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<Wydarzenie>> {

    private static String TAG = MainActivity.class.getName();

    private static final String USGS_REQUEST_URL ="https://newsapi.org/v2/top-headlines?country=pl&" +
            "apiKey=d8a3f809da8048f89245bd588b94d837";

    private static final int NEWS_LOADER_ID = 1;

    private WydarzenieAdapter mAdapter;

    private TextView pustalista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView newsListView = (ListView) findViewById(R.id.list);

        pustalista = (TextView) findViewById(R.id.empty_view);
        newsListView.setEmptyView(pustalista);

        mAdapter = new WydarzenieAdapter(this,new ArrayList<Wydarzenie>());

        newsListView.setAdapter(mAdapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Wydarzenie obecneWydarzenie = mAdapter.getItem(position);

                Uri Newsuri = Uri.parse(obecneWydarzenie.getUrl());

                Intent loadWebsite = new Intent(Intent.ACTION_VIEW,Newsuri);

                startActivity(loadWebsite);

            }
        });

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected());{
            LoaderManager loaderManager = getLoaderManager().forceload();
        }

    }

    @NonNull
    @Override
    public Loader<List<Wydarzenie>> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Wydarzenie>> loader, List<Wydarzenie> data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Wydarzenie>> loader) {
        mAdapter.clear();
    }
}
