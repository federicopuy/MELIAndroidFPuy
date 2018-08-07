package com.example.federico.mlibrefedericopuy.ui;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.example.federico.mlibrefedericopuy.R;
import com.example.federico.mlibrefedericopuy.adapters.ProductListAdapter;
import com.example.federico.mlibrefedericopuy.network.AppController;
import com.example.federico.mlibrefedericopuy.network.NetworkState;
import com.example.federico.mlibrefedericopuy.viewmodel.SearchResultsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.federico.mlibrefedericopuy.utils.Constants.LIST_STATE_KEY;

/**
 * Actividad principal donde se muestra una lista de productos segun un parametro de busqueda
 * ingresado por el usuario. En tablets soporta master-detail view.
 */
public class ProductListActivity extends AppCompatActivity {
    private static final String TAG = "ProductListActivity";

    @BindView(R.id.item_list)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.coordinatorMaster)
    View coordinatorMaster;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    SearchResultsViewModel searchResultsViewModel;
    private Parcelable mListState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        boolean isTablet = false;
        if (findViewById(R.id.item_detail_container) != null) {
            isTablet = true;
        }

        //recyclerview set up
        ProductListAdapter adapter = new ProductListAdapter(this, isTablet);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(ProductListActivity.this,
                DividerItemDecoration.VERTICAL));

        //observer set up
        SearchResultsViewModel.Factory factory = new SearchResultsViewModel.Factory(AppController.create(this));
        searchResultsViewModel = ViewModelProviders.of(this, factory).get(SearchResultsViewModel.class);

        // Observer para actualizar lista de productos mostrados
        searchResultsViewModel.getProductLiveData().observe(this, pagedList -> {
            adapter.submitList(pagedList);
            recyclerView.setAdapter(adapter);
        });

        // Observer para actualizar network state
        searchResultsViewModel.getNetworkState().observe(this, new Observer<NetworkState>() {
            @Override
            public void onChanged(@Nullable NetworkState networkState) {
                if (networkState.getStatus() == NetworkState.Status.RUNNING) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    if (networkState != null && networkState.getStatus() == NetworkState.Status.FAILED) {
                        Log.e(TAG, networkState.getMsg());
                        displayErrorMessage();
                    }
                }
            }
        });
    }

    /*-------------------------------------- Menu --------------------------------------------***/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                if (!(s.equals(""))) {
                    //efectuar llamada con nueva query ingresada por el usuario
                    searchResultsViewModel.setQuery(s);
                    searchResultsViewModel.invalidateDataSource();
                }
                return false;
            }
        });
        return true;
    }

    /*-------------------------------------- Display Errors --------------------------------------------***/

    private void displayErrorMessage() {
        Snackbar snackbar;
        if (!(NetworkState.isNetworkConnected(this))) {
            snackbar = Snackbar
                    .make(coordinatorMaster, R.string.error_internet, Snackbar.LENGTH_LONG);
        } else {
            snackbar = Snackbar
                    .make(coordinatorMaster, R.string.error, Snackbar.LENGTH_LONG);
        }
        snackbar.show();
    }
    
}
