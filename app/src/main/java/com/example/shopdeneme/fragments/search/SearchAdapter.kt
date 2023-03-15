package com.example.shopdeneme.fragments.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.shopdeneme.data.Products
import com.example.shopdeneme.databinding.SearchItemsBinding
import com.example.shopdeneme.util.setPicture

class SearchAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<SearchAdapter.MyHolder>(), Filterable {

    private var productList = ArrayList<Products>()
    private var productListFiltered = ArrayList<Products>()

    class MyHolder(val itemBinding: SearchItemsBinding) : RecyclerView.ViewHolder(itemBinding.root){
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
        return MyHolder(SearchItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val productList = productListFiltered[position]
        holder.bind(productList)
        holder.itemView.setOnClickListener{
            listener.onProductClick(productList)
        }
    }

    override fun getItemCount(): Int = productListFiltered.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val query = charSequence.toString()
                productListFiltered = productList.filter { it.title.contains(query) } as ArrayList<Products>
                val filterResults = FilterResults()
                filterResults.values = productListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                productListFiltered = filterResults.values as ArrayList<Products>
                notifyDataSetChanged()
            }
        }
    }


    fun setData(list: List<Products>) {
        productList.clear()
        productList.addAll(list)
        productListFiltered.clear()
        productListFiltered.addAll(list)
        notifyDataSetChanged()
    }

    interface OnItemClickListener{
        fun onProductClick(products: Products)
    }
}

