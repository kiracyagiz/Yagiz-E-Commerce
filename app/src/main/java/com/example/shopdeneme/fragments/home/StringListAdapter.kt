package com.example.shopdeneme.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shopdeneme.R

class StringListAdapter(private val stringList: List<String>, private val listener: ItemClickListener) :
    RecyclerView.Adapter<StringListAdapter.ViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    interface ItemClickListener {
        fun onItemClicked(category: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.konu_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = stringList[position]
        holder.bind(item, position == selectedPosition)

        holder.itemView.setOnClickListener {
            selectedPosition = holder.adapterPosition
            notifyDataSetChanged()
            listener.onItemClicked(item)
        }
    }

    override fun getItemCount(): Int = stringList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val myTextView: TextView = itemView.findViewById(R.id.myText)

        fun bind(item: String, isSelected: Boolean) {
            myTextView.text = item

            if (isSelected) {
                itemView.background = ContextCompat.getDrawable(itemView.context, R.drawable.circle_background)
            } else {
                itemView.background = ContextCompat.getDrawable(itemView.context,R.drawable.circular_background)
            }

        }
    }
}



