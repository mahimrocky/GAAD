package com.aad.core.gaad.application_components.loader;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.aad.core.gaad.R;

import java.util.ArrayList;

public class LoaderActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<ArrayList<FriendsModel>> {

    private FriendsListAdapter adapter;
    private ArrayList<FriendsModel> friends;
    private RecyclerView friendList;
    private View loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        //initialize the view
        friendList = (RecyclerView) findViewById(R.id.recycler_view_list);
        loadingBar = (ProgressBar) findViewById(R.id.loading);

        // adapter set
        initAdapter();

        // initial loader

        getSupportLoaderManager().initLoader(0, null, this);

    }

    private void initAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        friendList.setLayoutManager(layoutManager);
        friendList.setHasFixedSize(true);

        friends = new ArrayList<>();

        adapter = new FriendsListAdapter(this, friends);

        friendList.setAdapter(adapter);

    }

    @Override
    public Loader<ArrayList<FriendsModel>> onCreateLoader(int id, Bundle args) {

        CustomAsynTaskLoader customAsynTaskLoader = new CustomAsynTaskLoader(this, friends);
        customAsynTaskLoader.forceLoad();

        return customAsynTaskLoader;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<FriendsModel>> loader, ArrayList<FriendsModel> data) {

        adapter.notifyDataSetChanged();

        loadingBar.setVisibility(View.GONE);
        friendList.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<FriendsModel>> loader) {
        friendList.setAdapter(null);
    }
}
