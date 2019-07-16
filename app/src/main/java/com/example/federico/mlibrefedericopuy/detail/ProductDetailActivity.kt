package com.example.federico.mlibrefedericopuy.detail


import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.federico.mlibrefedericopuy.R
import com.example.federico.mlibrefedericopuy.core.getViewModel
import com.example.federico.mlibrefedericopuy.list.ProductListActivity
import com.example.federico.mlibrefedericopuy.model.Description
import com.example.federico.mlibrefedericopuy.model.ProductInfo
import com.example.federico.mlibrefedericopuy.utils.Constants
import kotlinx.android.synthetic.main.activity_product_detail.*
import java.util.*

class ProductDetailActivity : AppCompatActivity() {

    lateinit var viewModel: ProductDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        setSupportActionBar(detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val product = intent.getParcelableExtra<ProductInfo>(Constants.PRODUCT_INTENT_JSON)
        renderProductInfo(product)

        viewModel = getViewModel { ProductDetailViewModel(ProductDetailRepository(), product.id) }

        viewModel.productDescription.observe(this, Observer(this::renderProductDescription))
        viewModel.productImages.observe(this, Observer(this::loadProductImages))
        viewModel.loadingStatus.observe(this, Observer(this::showLoading))

    }

    private fun renderProductInfo(product: ProductInfo) {
        tvTitle.text = product.title
        tvPrice.text = product.price
        tvAvailableQuantity.text = product.availableQuantity
        tvAmountSold.text = product.soldQuantity
        tvCondition.text = product.condition
        if (product.acceptsMp) {
            logoMpago.visibility = View.VISIBLE
        }
    }

    private fun renderProductDescription(description: Description?) {
        if (!description?.plainText.isNullOrBlank()) {
            descriptionTitle.visibility = View.VISIBLE
            tvDescription.visibility = View.VISIBLE
            tvDescription.text = description?.plainText
        }
    }

    private fun loadProductImages(pictures: List<String>?) {
        val productDetailImagesPagerAdapter = ProductDetailImagesPagerAdapter(this, pictures as ArrayList<String>)
        imageViewPager.adapter = productDetailImagesPagerAdapter
    }

    private fun showLoading(isLoading: Boolean?) {
        isLoading?.let {
            if (it) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.INVISIBLE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            navigateUpTo(Intent(this, ProductListActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}
