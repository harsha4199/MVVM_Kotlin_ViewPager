package com.example.productshopapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.productshopapp.R
import com.example.productshopapp.models.Product
import me.relex.circleindicator.CircleIndicator3

class RecyclerViewAdapter(callBack: ProductDetails) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    val callBack: ProductDetails
    var productList = ArrayList<Product>()

    init {
        this.callBack = callBack
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUpdatedData(productList: ArrayList<Product>) {
        this.productList = productList
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var viewPager: ViewPager2 = view.findViewById(R.id.view_pager2)
        var indicator: CircleIndicator3 = view.findViewById(R.id.indicator)

        val prodTitle = view.findViewById<TextView>(R.id.prodTitle)
        val prodDesc = view.findViewById<TextView>(R.id.prodDesc)
        val prodRating = view.findViewById<TextView>(R.id.prodRating)


        fun bind(data: Product) {
            prodTitle.text = "[ " + data.brand + " ] " + data.title
            prodDesc.text = data.description
            prodRating.text = data.rating.toString()

            viewPager.adapter = ViewPagerAdapter(data.images)
            viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            indicator.setViewPager(viewPager)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycleview_item_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(productList[position])

    }


    override fun getItemCount(): Int {
        return productList.size
    }


    interface ProductDetails {
        fun getDetails(product: Product)
    }

}