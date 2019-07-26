package com.example.federico.mlibrefedericopuy.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.federico.mlibrefedericopuy.R.layout.activity_product_list
import com.example.federico.mlibrefedericopuy.core.getViewModel
import com.example.federico.mlibrefedericopuy.databinding.ActivityProductListBinding
import com.example.federico.mlibrefedericopuy.detail.ProductDetailActivity
import com.example.federico.mlibrefedericopuy.model.ProductInfo
import com.example.federico.mlibrefedericopuy.utils.Constants
import kotlinx.android.synthetic.main.activity_product_list.*


class ProductListActivity : AppCompatActivity() {

    lateinit var viewModel: ProductListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_product_list)
        setSupportActionBar(toolbar)

        val binding : ActivityProductListBinding = DataBindingUtil.setContentView(this, activity_product_list)

        viewModel = getViewModel { ProductListViewModel(ProductListRepository()) }

        viewModel.results.observe(this, Observer {
            when (it) {
                is ProductListState.ShowItems -> renderItems(it.products)
                is ProductListState.Loading -> showLoading(it.isLoading)
                is ProductListState.ShowError -> showError()
            }
        })

        binding.viewModel = viewModel
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

    private fun showError() = Toast.makeText(this, com.example.federico.mlibrefedericopuy.R.string.error, Toast.LENGTH_LONG).show()
}