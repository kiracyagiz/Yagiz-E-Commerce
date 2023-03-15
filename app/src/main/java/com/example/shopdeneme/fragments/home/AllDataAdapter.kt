package com.example.shopdeneme.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopdeneme.data.Products
import com.example.shopdeneme.databinding.PostItemAllBinding
import com.example.shopdeneme.util.setPicture

class AllDataAdapter : RecyclerView.Adapter<AllDataAdapter.MyInnerHolder>() {

    private var productList = ArrayList<Products>()


    class MyInnerHolder(val itemBinding: PostItemAllBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(products: Products) = with(itemBinding){

            val title = products.title
            itemTitle.text =
                if (title.length > 10) title.substring(0, 10) + "..."
                else title
            itemBody.text = products.category
            myImageView.setPicture(products.image)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyInnerHolder {
        return MyInnerHolder(PostItemAllBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: MyInnerHolder, position: Int) {
        val productList = productList[position]
        holder.bind(productList)
    }


    fun setdata(list: List<Products>){
        productList.clear()
        productList.addAll(list)
        notifyDataSetChanged()

    }

}