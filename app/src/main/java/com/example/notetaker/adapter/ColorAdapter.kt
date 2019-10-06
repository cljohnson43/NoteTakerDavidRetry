package com.example.notetaker.adapter

import android.content.Context
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.notetaker.R
import com.example.notetaker.util.Constants

class  ColorAdapter(private val colors: List<String>, private val delegator: colorAdapterDelegator) :
        RecyclerView.Adapter<ColorAdapter.CustomViewHolder>() {
    lateinit var appContext: Context

    interface  colorAdapterDelegator{
        fun colorPicked(colorResource: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.color_item_view_layout, parent, false)
        appContext = parent.context.applicationContext
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        holder.colorText.text = colors[position]
        when(colors[position])
        {
            Constants.COLOR_BLUE -> {
                holder.colorText.setTextColor(ContextCompat.getColor(appContext, R.color.appBlue))
                holder.itemView.setOnClickListener {delegator.colorPicked(Constants.COLOR_BLUE)
                }
            }

            Constants.COLOR_YELLOW -> {
                holder.colorText.setTextColor(ContextCompat.getColor(appContext, R.color.appYellow))
                holder.itemView.setOnClickListener {delegator.colorPicked(Constants.COLOR_YELLOW)
                }
            }

            Constants.COLOR_WHITE -> {
                holder.colorText.setTextColor(ContextCompat.getColor(appContext, R.color.appWhite))
                holder.colorText.setOnClickListener {delegator.colorPicked(Constants.COLOR_WHITE)
                }
            }
        }
    }


    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val colorText: TextView = itemView.findViewById(R.id.color_textview)
    }
}