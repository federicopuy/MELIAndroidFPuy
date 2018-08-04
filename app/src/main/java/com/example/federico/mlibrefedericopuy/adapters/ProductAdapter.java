package com.example.federico.mlibrefedericopuy.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.federico.mlibrefedericopuy.ItemDetailActivity;
import com.example.federico.mlibrefedericopuy.ItemDetailFragment;
import com.example.federico.mlibrefedericopuy.ItemListActivity;
import com.example.federico.mlibrefedericopuy.R;
import com.example.federico.mlibrefedericopuy.model.Product;
import com.example.federico.mlibrefedericopuy.utils.Constants;
import com.example.federico.mlibrefedericopuy.utils.Utils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private final ItemListActivity mParentActivity;
    private final List<Product> productsList;
    private final boolean mTwoPane;
    private final ProductClickListener mOnClickListener;


    public ProductAdapter(ItemListActivity parent, List<Product> productsList, boolean twoPane, ProductClickListener listener) {
        this.productsList = productsList;
        mParentActivity = parent;
        mTwoPane = twoPane;
        mOnClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view, mOnClickListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Product product = productsList.get(position);

        String imagePath = product.getThumbnail();
        Picasso.get().load(imagePath).into(holder.mImageViewProductPhoto);

        holder.tvProductTitle.setText(product.getTitle());
        holder.tvSeller.setText("en " + product.getSellerAddress().getCity().getName());
        holder.tvPrice.setText(Utils.getFormattedPrice(product.getPrice()));
        if (product.getSoldQuantity()>0){
            holder.tvAmountSold.setVisibility(View.VISIBLE);
            holder.tvAmountSold.setText(String.valueOf(product.getSoldQuantity()) + " vendidos");
        }
        holder.tvRating.setText(String.valueOf(product.getReviews().getRatingAverage()));

        holder.itemView.setTag(productsList.get(position));

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imageViewProductPhoto)
        ImageView  mImageViewProductPhoto;
        @BindView(R.id.tvProductTitle)
        TextView tvProductTitle;
        @BindView(R.id.tvSeller)
        TextView tvSeller;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.tvAmountSold)
        TextView tvAmountSold;
        @BindView(R.id.tvRating)
        TextView tvRating;
        private ProductClickListener mListener;

        ViewHolder(View view, ProductClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            mListener = listener;
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            mListener.onProductClicked(view);
        }
    }

    public interface ProductClickListener {
        void onProductClicked(View view);
    }
}