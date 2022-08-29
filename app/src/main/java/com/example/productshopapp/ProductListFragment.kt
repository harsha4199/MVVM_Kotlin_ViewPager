package com.example.productshopapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.productshopapp.adapter.RecyclerViewAdapter
import com.example.productshopapp.models.Product
import com.example.productshopapp.viewmodel.MainActivityViewModel

class ProductListFragment : Fragment(), RecyclerViewAdapter.ProductDetails {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)
        initViewModel(view)
        initViewModel()
        return view
    }

    private fun initViewModel(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        recyclerViewAdapter = RecyclerViewAdapter(this)
        recyclerView.adapter = recyclerViewAdapter
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.makeApiCall()
        viewModel.getProductListObserver().observe(this, Observer {

            it?.let {
                recyclerViewAdapter.setUpdatedData(it.products as ArrayList<Product>)
            } ?: kotlin.run {
                Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
            }

        })

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProductListFragment().apply {
            }
    }

    override fun getDetails(product: Product) {
        //Log.d("TAG", "getDetails: " + product.brand)
    }


}
