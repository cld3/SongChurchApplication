package com.example.cld.songchurchapplication.ui.churchSelect

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.cld.songchurchapplication.R
import com.example.cld.songchurchapplication.models.ChurchModel

class ChurchSelectAdapter(var chuches: List<ChurchModel>) : RecyclerView.Adapter<ChurchSelectAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.church,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return chuches.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTV?.text = chuches[position].name
        holder.cityTV?.text = chuches[position].city
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameTV:TextView? = null
        var cityTV:TextView? = null

        init {
            nameTV = view.findViewById(R.id.nameTextView)
            cityTV = view.findViewById(R.id.cityTextView)
        }
    }
}