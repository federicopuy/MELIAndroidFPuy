package com.example.federico.mlibrefedericopuy.ui;

import android.arch.lifecycle.ViewModelProviders;
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

import com.example.federico.mlibrefedericopuy.R;
import com.example.federico.mlibrefedericopuy.adapters.ProductDetailImagesPagerAdapter;
import com.example.federico.mlibrefedericopuy.model.Product;
import com.example.federico.mlibrefedericopuy.network.AppController;
import com.example.federico.mlibrefedericopuy.network.NetworkState;
import com.example.federico.mlibrefedericopuy.utils.Constants;
import com.example.federico.mlibrefedericopuy.utils.Utils;
import com.example.federico.mlibrefedericopuy.viewmodel.AdditionalProductInfoViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment para representar un producto seleccionado
 */
public class ProductDetailFragment extends Fragment {

    private Product product;
    private static final String TAG = "ProductDetailFragment";

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

    public ProductDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(Constants.PRODUCT_FRAGMENT_JSON)) {
            String productJsonString = getArguments().getString(Constants.PRODUCT_FRAGMENT_JSON, "");
            Gson gson = new Gson();
            product = gson.fromJson(productJsonString, Product.class);
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

            if (product.getReviews().getRatingAverage() != null) {
                tvRating.setVisibility(View.VISIBLE);
                starImageView.setVisibility(View.VISIBLE);
                tvRating.setText(Utils.getFormattedRatingAverage(product.getReviews().getRatingAverage()));
            }

            if (product.getAcceptsMercadopago()) {
                logoMpago.setVisibility(View.VISIBLE);
            }
            tvAvailableQuantity.setText(Utils.getAvailableQuantity(product.getAvailableQuantity()));
            tvAmountSold.setText(Utils.getSoldAmount(product.getSoldQuantity()));
            tvCondition.setText(Utils.getCondition(product.getCondition()));

            //SetUp Observer
            AdditionalProductInfoViewModel.Factory factory = new AdditionalProductInfoViewModel.Factory(AppController.create(getContext()),
                    product.getId());
            AdditionalProductInfoViewModel additionalProductInfoViewModel = ViewModelProviders.of(this, factory).get(AdditionalProductInfoViewModel.class);

           //Observer para cargar imagenes
            additionalProductInfoViewModel.getPicturesUrls().observe(this, strings -> {
                ProductDetailImagesPagerAdapter productDetailImagesPagerAdapter = new ProductDetailImagesPagerAdapter(getContext(), (ArrayList<String>) strings);
                ViewPager viewPager = rootView.findViewById(R.id.imageViewPager);
                viewPager.setAdapter(productDetailImagesPagerAdapter);
            });

            //Observer para cargar Descripcion
            additionalProductInfoViewModel.getProductDescription().observe(this, description -> {
                if (!(description.getPlainText().equals(""))) {
                    descriptionTitleTv.setVisibility(View.VISIBLE);
                    tvDescription.setVisibility(View.VISIBLE);
                    tvDescription.setText(description.getPlainText());
                }
            });

            //Observer para network state
            additionalProductInfoViewModel.getNetworkState().observe(this, networkState -> {
                if (networkState != null && networkState.getStatus() == NetworkState.Status.FAILED) {
                    Log.e(TAG, networkState.getMsg());
                    displayErrorMessage(rootView);
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

    private void displayErrorMessage(View rootView) {
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
