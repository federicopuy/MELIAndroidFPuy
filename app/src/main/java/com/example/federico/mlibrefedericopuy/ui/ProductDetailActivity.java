package com.example.federico.mlibrefedericopuy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.example.federico.mlibrefedericopuy.R;
import com.example.federico.mlibrefedericopuy.utils.Constants;

/**
 * Actividad usada unicamente para celulares o dispositivos con pantallas chicas
 */
public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {

            String stepJson = getIntent().getStringExtra(Constants.PRODUCT_INTENT_JSON);
            Bundle arguments = new Bundle();
            arguments.putString(Constants.PRODUCT_FRAGMENT_JSON, stepJson);
            ProductDetailFragment fragment = new ProductDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, ProductListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
