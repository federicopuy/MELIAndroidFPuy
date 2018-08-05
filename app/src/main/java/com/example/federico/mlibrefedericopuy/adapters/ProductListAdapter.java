package com.example.federico.mlibrefedericopuy.adapters;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.federico.mlibrefedericopuy.R;
import com.example.federico.mlibrefedericopuy.model.Product;
import com.example.federico.mlibrefedericopuy.utils.Utils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListAdapter extends PagedListAdapter<Product, RecyclerView.ViewHolder> {

    private final ProductClickListener mOnClickListener;

    public ProductListAdapter(ProductClickListener listener){
        super(Product.DIFF_CALLBACK);
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list_content, viewGroup, false);
        return new ProductViewHolder(view, mOnClickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof ProductViewHolder){

            ProductViewHolder holder = (ProductViewHolder) viewHolder;
            Product product =  getItem(i);

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
            holder.itemView.setTag(getCurrentList().get(i));
        }
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imageViewProductPhoto)
        ImageView mImageViewProductPhoto;
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

        ProductViewHolder(View view, ProductClickListener listener) {
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
