package com.example.federico.mlibrefedericopuy.list

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.federico.mlibrefedericopuy.R
import com.example.federico.mlibrefedericopuy.core.getViewModel
import com.example.federico.mlibrefedericopuy.detail.ProductDetailActivity
import com.example.federico.mlibrefedericopuy.model.ProductInfo
import com.example.federico.mlibrefedericopuy.utils.Constants
import kotlinx.android.synthetic.main.activity_product_list.*

class ProductListActivity : AppCompatActivity() {

    lateinit var viewModel: ProductListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        setSupportActionBar(toolbar)

        viewModel = getViewModel { ProductListViewModel(ProductListRepository()) }

        viewModel.results.observe(this, Observer {
            when (it) {
                is ProductListState.ShowItems -> renderItems(it.products)
                is ProductListState.Loading -> showLoading(it.isLoading)
                is ProductListState.ShowError -> showError()
            }
        })
    }

    private fun renderItems(products: List<ProductInfo>) {
        rvItemList.adapter = ProductListAdapter(products) {
            //OnClickListener for the adapter
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra(Constants.PRODUCT_INTENT_JSON, it)
            startActivity(intent)
        }
    }

    private fun showLoading(isLoading: Boolean) =
            if (isLoading) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.INVISIBLE

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(s: String): Boolean = false

            override fun onQueryTextChange(input: String): Boolean {
                viewModel.searchProducts(input)
                return false
            }
        })
        return true
    }

    private fun showError() = Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show()
}