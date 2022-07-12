package com.fadil.antihoaxsearch;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fadil.antihoaxsearch.AsyncDelegate;
import com.fadil.antihoaxsearch.GObjectAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.List;

public class ActivityCariFacebook extends AppCompatActivity implements AsyncDelegate {

    private final int PERMISSIONS_REQUEST_INTERNET = 12;
    private EditText eText;
    private GObjectAdapter mGObjectAdapter;
    private boolean loading;
    private int pastVisibleItems, visibleItemCount, totalItemCount;
    private LinearLayoutManager mLayoutManager;
    private boolean currentType = false;
    private RecyclerView recyclerView;
    private GridLayoutManager mGridLayoutManager;
    private AdView adBanner;
    private  AdRequest adRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_gambar);
        resolvePermissionAndInitView();
        MobileAds.initialize(this,"ca-app-pub-2767077821626186~7875044803");
        adBanner=(AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adBanner.loadAd(adRequest);
    }

    private void resolvePermissionAndInitView() {
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PERMISSIONS_REQUEST_INTERNET);
        } else {
            initView();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_INTERNET: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initView();
                } else {
                    this.finish();
                }
            }
        }
    }

    private void runAsyncTask(String query, int firstID) {
        mGObjectAdapter.getData().add(null);
        mGObjectAdapter.notifyItemInserted(mGObjectAdapter.getData().size() - 1);
        SearchTaskFB searcherfb = new SearchTaskFB(this);
        searcherfb.setFirstItemID(firstID);
        searcherfb.setType(currentType);
        searcherfb.execute(query);
    }

    private void initView() {
        eText = (EditText) findViewById(R.id.editText2);
        Button button = (Button) findViewById(R.id.searchButton2);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchRecyclerLayout();
                mGObjectAdapter.getData().clear();
                mGObjectAdapter.notifyDataSetChanged();
                runAsyncTask(eText.getText().toString(), 1);
            }
        });
        mGObjectAdapter = new GObjectAdapter(getApplicationContext());
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mGridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        assert recyclerView != null;
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mGObjectAdapter);
        loading = true;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    if (!currentType) {
                        visibleItemCount = mLayoutManager.getChildCount();
                        totalItemCount = mLayoutManager.getItemCount();
                        pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();
                    } else {
                        visibleItemCount = mGridLayoutManager.getChildCount();
                        totalItemCount = mGridLayoutManager.getItemCount();
                        pastVisibleItems = mGridLayoutManager.findFirstVisibleItemPosition();
                    }

                    if (loading) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            loading = false;
                            runAsyncTask(eText.getText().toString(), mGObjectAdapter.getItemCount() + 1);
                        }
                    }
                }
            }
        });
    }

    private void switchRecyclerLayout() {
        if (!currentType) {
            mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mGObjectAdapter);
        } else {
            mGridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
            recyclerView.setLayoutManager(mGridLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mGObjectAdapter);
            SpacesItemDecoration decoration = new SpacesItemDecoration(16);
            recyclerView.addItemDecoration(decoration);
        }

    }


    @Override
    public void asyncComplete(List<GObject> data) {
        loading = true;
        mGObjectAdapter.getData().remove(mGObjectAdapter.getData().size() - 1);
        mGObjectAdapter.notifyItemRemoved(mGObjectAdapter.getData().size());
        if (data != null) {
            mGObjectAdapter.getData().addAll(data);
            mGObjectAdapter.notifyDataSetChanged();
            if (mGObjectAdapter.getData().size() < 15) {
                runAsyncTask(eText.getText().toString(), mGObjectAdapter.getItemCount() + 1);
            }
        } else {
            Context context = getApplicationContext();
            CharSequence text = "End";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
