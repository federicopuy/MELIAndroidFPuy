package com.example.federico.mlibrefedericopuy.detail

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager.widget.PagerAdapter
import com.example.federico.mlibrefedericopuy.R
import com.squareup.picasso.Picasso
import java.util.*

class ProductDetailImagesPagerAdapter(private val context: Context, private val picturesURLs: ArrayList<String>)
    : PagerAdapter() {

    private val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
            as LayoutInflater

    override fun getCount(): Int = picturesURLs.size

    override fun isViewFromObject(view: View, o: Any): Boolean = view === o as LinearLayout

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val itemView = layoutInflater.inflate(R.layout.pager_item, container, false)
        val imageView = itemView.findViewById<View>(R.id.imageViewPicturePager) as ImageView

        Picasso.get().load(picturesURLs[position])
                .placeholder(R.drawable.ic_photo_black_24dp)
                .error(R.drawable.ic_error_outline_black_24dp)
                .into(imageView)
        container.addView(itemView)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            imageView.foreground = ResourcesCompat.getDrawable(context.resources, R.drawable.scrim, null)
        }

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) =
            container.removeView(`object` as LinearLayout)
}
