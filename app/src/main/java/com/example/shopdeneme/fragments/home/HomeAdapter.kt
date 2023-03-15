package com.example.shopdeneme.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopdeneme.data.Products
import com.example.shopdeneme.databinding.PostItemBinding
import com.example.shopdeneme.util.setPicture

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyHolder>() {

    private var top10productList = ArrayList<Products>()


    class MyHolder(val itemBinding: PostItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(products: Products) = with(itemBinding){



            val title = products.title
            itemTitle.text =
                if (title.length > 10) title.substring(0, 10) + "..."
                else title
            itemBody.text = products.category
            myImageView.setPicture(products.image)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = top10productList.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val productList = top10productList[position]
        holder.bind(productList)
    }

    fun setdata(list: List<Products>){
        top10productList.clear()
        top10productList.addAll(list)
        notifyDataSetChanged()

    }

}