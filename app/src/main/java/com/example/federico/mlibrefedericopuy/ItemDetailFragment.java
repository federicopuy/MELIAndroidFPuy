package com.example.federico.mlibrefedericopuy;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.federico.mlibrefedericopuy.model.Product;
import com.example.federico.mlibrefedericopuy.utils.Constants;
import com.example.federico.mlibrefedericopuy.utils.Utils;
import com.google.gson.Gson;

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

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvAvailableQuantity)
    TextView tvAvailableQuantity;
    @BindView(R.id.tvRating)
    TextView tvRating;
    @BindView(R.id.tvCondition)
    TextView tvSeller;
    @BindView(R.id.tvAmountSold)
    TextView tvAmountSold;

    @BindView(R.id.tvDescription)
    TextView tvDescription;
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
            String productJsonString = getArguments().getString(Constants.PRODUCT_FRAGMENT_JSON,"");
            Gson gson = new Gson();
            product = gson.fromJson(productJsonString, Product.class);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(product.getTitle());
            }

//            ImageView photoImageView = activity.findViewById(R.id.header);
//            String imagePath = product.getThumbnail();
//            Picasso.get().load(imagePath).into(photoImageView);


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
            tvAvailableQuantity.setText(String.valueOf(product.getAvailableQuantity()));
            tvRating.setText(String.valueOf(product.getReviews().getRatingAverage()));
            //tvSeller.setText();
            tvAmountSold.setText(String.valueOf(product.getSoldQuantity() + " vendidos"));
            description = getArguments().getString(Constants.DESCRIPTION_FRAGMENT_JSON);

            tvDescription.setText(description);

//
//            ArrayList<String> pictureURLS = new ArrayList<>();
//
//            for (Picture p:product.getPictureList()){
//
//                pictureURLS.add(p.getUrl());
//            }
//
//
//            CustomPagerAdapter customPagerAdapter = new CustomPagerAdapter(getContext(), pictureURLS);
//            ViewPager viewPager = rootView.findViewById(R.id.imageViewPager);
//            viewPager.setAdapter(customPagerAdapter);

        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
