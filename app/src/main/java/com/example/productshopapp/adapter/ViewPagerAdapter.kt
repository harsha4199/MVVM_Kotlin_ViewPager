package com.example.productshopapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.productshopapp.R
import com.squareup.picasso.Picasso

class ViewPagerAdapter(
    private var productList: List<String> = ArrayList(),
) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val prodImage = view.findViewById<ImageView>(R.id.prodImg)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder =
        ViewPagerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.viewpager_view, parent, false)
        )


    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        Picasso.get().load(productList[position]).into(holder.prodImage);
    }

    override fun getItemCount(): Int = productList.size


}