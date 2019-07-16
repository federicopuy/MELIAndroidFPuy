package com.example.federico.mlibrefedericopuy.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.federico.mlibrefedericopuy.R
import com.example.federico.mlibrefedericopuy.model.ProductInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_content.view.*

class ProductListAdapter(private val items: List<ProductInfo>, private val listener: (ProductInfo) -> Unit) :
        RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ProductViewHolder =
            ProductViewHolder(LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.item_list_content, viewGroup, false))

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(product: ProductInfo?, listener: (ProductInfo) -> Unit) = with(itemView) {

            product?.let {

                Picasso.get().load(it.thumbnail)
                        .placeholder(R.drawable.ic_photo_black_24dp)
                        .error(R.drawable.ic_error_outline_black_24dp).into(imageViewProductPhoto)

                tvProductTitle.text = it.title
                tvCondition.text = it.condition
                tvPrice.text = it.price

                it.soldQuantity?.let {
                    tvAmountSold.visibility = View.VISIBLE
                    tvAmountSold.text = it
                }
                setOnClickListener { listener(product) }
            }
        }

    }
}
