package com.example.federico.mlibrefedericopuy;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.federico.mlibrefedericopuy.adapters.ProductAdapter;
import com.example.federico.mlibrefedericopuy.model.Description;
import com.example.federico.mlibrefedericopuy.model.Item;
import com.example.federico.mlibrefedericopuy.model.Product;
import com.example.federico.mlibrefedericopuy.model.SearchResults;
import com.example.federico.mlibrefedericopuy.network.ApiInterface;
import com.example.federico.mlibrefedericopuy.network.RetrofitClient;
import com.example.federico.mlibrefedericopuy.utils.Constants;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity implements ProductAdapter.ProductClickListener{

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }

        //todo paginado


        String searchParameter = "Heladera";
        callToGetProducts(searchParameter);

    }

    private void callToGetProducts(String searchParameter) {
        ApiInterface mService = RetrofitClient.getClient(getApplicationContext()).create(ApiInterface.class);
        final Call<SearchResults> callGetResults = mService.getSearchResults(searchParameter);
        callGetResults.enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                if (response.isSuccessful()) {
                    System.out.println("SUCCESSFULL");

                    try {
                        SearchResults searchResults = response.body();
                        List<Product> productsList = searchResults.getProducts();
                        setupRecyclerView(productsList);

                    } catch (Exception e){
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("NOT SUCCESSFULL");

                }
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {


            }
        });
    }


    private void setupRecyclerView(List<Product> productsList) {
        RecyclerView recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;

        recyclerView.setAdapter(new ProductAdapter(this,productsList, mTwoPane, this));
        recyclerView.addItemDecoration(new DividerItemDecoration(ItemListActivity.this,
                DividerItemDecoration.VERTICAL));
//        if(!mTwoPane){
//            StaggeredGridLayoutManager sglm =
//                    new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//            recyclerView.setLayoutManager(sgl);
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Associate searchable configuration with the SearchView
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

                callToGetProducts(s);

                return false;
            }
        });

        return true;
    }

    @Override
    public void onProductClicked(final View view) {

        ApiInterface mService = RetrofitClient.getClient(getApplicationContext()).create(ApiInterface.class);
        final Product product = (Product) view.getTag();

        Call<Item> callGetProduct = mService.getItem(product.getId());
        callGetProduct.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {

                Item item = response.body();

                searchDescription(view, product, item);

            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });









    }

    void searchDescription(final View view, final Product product, final Item item){


        ApiInterface mService = RetrofitClient.getClient(getApplicationContext()).create(ApiInterface.class);
        Call<Description> callGetDescription = mService.getItemDescription(product.getId());
        callGetDescription.enqueue(new Callback<Description>() {
            @Override
            public void onResponse(Call<Description> call, Response<Description> response) {
                if (response.isSuccessful()){

                    Description description = response.body();
                    System.out.println(description.getText());

                    goToNextActivity(view, product,description, item);

                } else{

                }
            }

            @Override
            public void onFailure(Call<Description> call, Throwable t) {

            }
        });





    }

    void goToNextActivity(View view, Product product, Description description, Item item){

        product.setPictureList(item.getPictures());

        Gson productGson = new Gson();
        String productJson = productGson.toJson(product);

        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(Constants.PRODUCT_FRAGMENT_JSON, productJson);
            arguments.putString(Constants.DESCRIPTION_FRAGMENT_JSON, description.getPlainText());

            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            this.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
        } else {
            Context context = view.getContext();
            Intent intent = new Intent(context, ItemDetailActivity.class);
            intent.putExtra(Constants.PRODUCT_INTENT_JSON, productJson);
            intent.putExtra(Constants.DESCRIPTION_FRAGMENT_JSON, description.getPlainText());


            context.startActivity(intent);
        }



    }

}
