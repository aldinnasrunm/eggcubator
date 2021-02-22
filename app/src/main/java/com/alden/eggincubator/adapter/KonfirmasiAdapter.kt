package com.alden.eggincubator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alden.eggincubator.R

class KonfirmasiAdapter(data : ArrayList<String>) : RecyclerView.Adapter<KonfirmasiAdapter.viewHolder>() {
    var data = data
    inner class viewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(s: String) {
            itemView.findViewById<TextView>(R.id.tvKonfirmasiText).text = s
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): viewHolder = viewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_list_konfirmasi, parent, false))


    override fun onBindViewHolder(holder: KonfirmasiAdapter.viewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}