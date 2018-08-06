package com.example.federico.mlibrefedericopuy.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.federico.mlibrefedericopuy.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductDetailImagesPagerAdapter extends PagerAdapter {

    /*
     * ViewPager para mostrar imagenes en DetailFragment
     *
     * */

    private LayoutInflater mLayoutInflater;
    private ArrayList<String> picturesURLs;
    private Context context;


    public ProductDetailImagesPagerAdapter(Context context, ArrayList<String> picturesURLs) {
        this.context = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.picturesURLs = picturesURLs;
    }

    @Override
    public int getCount() {
        return picturesURLs.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == ((LinearLayout) o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewPicturePager);
        Picasso.get().
                load(picturesURLs.get(position))
                .placeholder(R.drawable.ic_photo_black_24dp)
                .error(R.drawable.ic_error_outline_black_24dp)
                .into(imageView);
        container.addView(itemView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageView.setForeground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.scrim, null));
        }

        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
