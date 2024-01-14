package com.example.messegview

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StrAdapter: RecyclerView.Adapter<StrAdapter.StringViewHolder>(){

    private val list = mutableListOf<String>()

    inner class StringViewHolder(
        val textView: TextView
    ): RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val textView = TextView(parent.context)
        textView.textSize = 20F
        return StringViewHolder(textView)
    }

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.textView.text = list[position]
    }

    fun submitList(list: List<String>) {
        with(this.list){
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }
}



