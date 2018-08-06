package com.example.federico.mlibrefedericopuy.adapters;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.federico.mlibrefedericopuy.R;
import com.example.federico.mlibrefedericopuy.model.Product;
import com.example.federico.mlibrefedericopuy.ui.ProductDetailActivity;
import com.example.federico.mlibrefedericopuy.ui.ProductDetailFragment;
import com.example.federico.mlibrefedericopuy.ui.ProductListActivity;
import com.example.federico.mlibrefedericopuy.utils.Constants;
import com.example.federico.mlibrefedericopuy.utils.Utils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListAdapter extends PagedListAdapter<Product, RecyclerView.ViewHolder> {

    /*
     * PagedList Adapter para mostrar lista de productos
     *
     * */

    private final ProductListActivity mParentActivity;
    private final boolean isTablet;
    private final View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final Product product = (Product) view.getTag();
            Gson productGson = new Gson();
            String productJson = productGson.toJson(product);
            if (isTablet) {
                Bundle arguments = new Bundle();
                arguments.putString(Constants.PRODUCT_FRAGMENT_JSON, productJson);
                ProductDetailFragment fragment = new ProductDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra(Constants.PRODUCT_INTENT_JSON, productJson);
                context.startActivity(intent);
            }


        }
    };


    public ProductListAdapter(ProductListActivity parent, boolean isTablet) {
        super(Product.DIFF_CALLBACK);
        mParentActivity = parent;
        this.isTablet = isTablet;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list_content, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof ProductViewHolder) {

            ProductViewHolder holder = (ProductViewHolder) viewHolder;
            Product product = getItem(i);
            if (product!=null) {
                try {
                    String imagePath = product.getThumbnail();
                    Picasso.get().load(imagePath)
                            .placeholder(R.drawable.ic_photo_black_24dp)
                            .error(R.drawable.ic_error_outline_black_24dp).
                            into(holder.mImageViewProductPhoto);
                } catch (NullPointerException n) {
                    n.printStackTrace();
                }
                holder.tvProductTitle.setText(product.getTitle());
                holder.tvCondition.setText(Utils.getCondition(product.getCondition()));
                holder.tvPrice.setText(Utils.getFormattedPrice(product.getPrice()));

                if (product.getSoldQuantity() > 0) {
                    holder.tvAmountSold.setVisibility(View.VISIBLE);
                    holder.tvAmountSold.setText(Utils.getSoldAmount(product.getSoldQuantity()));
                }
                if (product.getReviews().getRatingAverage() != null) {
                    holder.tvRating.setVisibility(View.VISIBLE);
                    holder.imageStar.setVisibility(View.VISIBLE);
                    holder.tvRating.setText(String.valueOf(Utils.getFormattedRatingAverage(product.getReviews().getRatingAverage())));
                }
                holder.itemView.setOnClickListener(mClickListener);
                holder.itemView.setTag(Objects.requireNonNull(getCurrentList()).get(i));
            }
        }
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageViewProductPhoto)
        ImageView mImageViewProductPhoto;
        @BindView(R.id.tvProductTitle)
        TextView tvProductTitle;
        @BindView(R.id.tvCondition)
        TextView tvCondition;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.tvAmountSold)
        TextView tvAmountSold;
        @BindView(R.id.tvRating)
        TextView tvRating;
        @BindView(R.id.star)
        ImageView imageStar;

        ProductViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
