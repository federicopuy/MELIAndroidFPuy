package com.example.federico.mlibrefedericopuy;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.federico.mlibrefedericopuy.activities.ItemDetailActivity;
import com.example.federico.mlibrefedericopuy.adapters.ProductDetailImagesPagerAdapter;
import com.example.federico.mlibrefedericopuy.model.Description;
import com.example.federico.mlibrefedericopuy.model.Product;
import com.example.federico.mlibrefedericopuy.network.AppController;
import com.example.federico.mlibrefedericopuy.network.NetworkState;
import com.example.federico.mlibrefedericopuy.utils.Constants;
import com.example.federico.mlibrefedericopuy.utils.Utils;
import com.example.federico.mlibrefedericopuy.viewmodel.ProductInfoViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {

    private Product product;
    private static final String TAG = "ItemDetailFragment";

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvAvailableQuantity)
    TextView tvAvailableQuantity;
    @BindView(R.id.tvRating)
    TextView tvRating;
    @BindView(R.id.tvCondition)
    TextView tvCondition;
    @BindView(R.id.tvAmountSold)
    TextView tvAmountSold;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.descriptionTitle)
    TextView descriptionTitleTv;
    @BindView(R.id.starImage)
    ImageView starImageView;
    @BindView(R.id.logoMpago)
    ImageView logoMpago;
    private Unbinder mUnbinder;
    String description;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(Constants.PRODUCT_FRAGMENT_JSON)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            String productJsonString = getArguments().getString(Constants.PRODUCT_FRAGMENT_JSON, "");
            Gson gson = new Gson();
            product = gson.fromJson(productJsonString, Product.class);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(product.getTitle());
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.item_detail, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        if (product != null) {

            tvTitle.setText(product.getTitle());
            tvPrice.setText(Utils.getFormattedPrice(product.getPrice()));

            if (product.getReviews().getRatingAverage()!=null){
                //todo set en invisible
                tvRating.setVisibility(View.VISIBLE);
                starImageView.setVisibility(View.VISIBLE);
                tvRating.setText(Utils.getFormattedRatingAverage(product.getReviews().getRatingAverage()));
            }

            if (product.getAcceptsMercadopago()){
                logoMpago.setVisibility(View.VISIBLE);
            }
            tvAvailableQuantity.setText(Utils.getAvailableQuantity(product.getAvailableQuantity()));


            tvAmountSold.setText(String.valueOf(product.getSoldQuantity() + " vendidos"));
            tvCondition.setText(Utils.getCondition(product.getCondition()));


            ProductInfoViewModel.Factory factory = new ProductInfoViewModel.Factory(AppController.create(getContext()),
                    product.getId());
            ProductInfoViewModel productInfoViewModel = ViewModelProviders.of(this, factory).get(ProductInfoViewModel.class);
            productInfoViewModel.getPicturesUrls().observe(this, new Observer<List<String>>() {
                @Override
                public void onChanged(@Nullable List<String> strings) {

                    ProductDetailImagesPagerAdapter productDetailImagesPagerAdapter = new ProductDetailImagesPagerAdapter(getContext(), (ArrayList<String>) strings);
                    ViewPager viewPager = rootView.findViewById(R.id.imageViewPager);
                    viewPager.setAdapter(productDetailImagesPagerAdapter);
                }
            });

            productInfoViewModel.getProductDescription().observe(this, new Observer<Description>() {
                @Override
                public void onChanged(@Nullable Description description) {
                    if (!(description.getPlainText().equals(""))) {
                        descriptionTitleTv.setVisibility(View.VISIBLE);
                        tvDescription.setText(description.getPlainText());
                    }
                }
            });

            productInfoViewModel.getNetworkState().observe(this, new Observer<NetworkState>() {
                @Override
                public void onChanged(@Nullable NetworkState networkState) {
                    if (networkState != null && networkState.getStatus() == NetworkState.Status.FAILED) {
                        Log.e(TAG, networkState.getMsg());
                        displayErrorMessage(rootView);
                    }
                }
            });

        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    void displayErrorMessage(View rootView) {

        Snackbar snackbar;
        if (!(NetworkState.isNetworkConnected(getContext()))) {
            snackbar = Snackbar
                    .make(rootView, R.string.error_internet, Snackbar.LENGTH_LONG);
        } else {
            snackbar = Snackbar
                    .make(rootView, R.string.error, Snackbar.LENGTH_LONG);
        }
        snackbar.show();

    }
}
