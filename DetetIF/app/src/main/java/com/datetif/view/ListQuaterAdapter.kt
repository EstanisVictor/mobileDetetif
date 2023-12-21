package com.datetif.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.datetif.databinding.ItemRecyclerViewBinding
import com.datetif.model.QuaterEntity

class ListQuaterAdapter(val listQuaters:MutableList<QuaterEntity>?, var ra: String) : RecyclerView.Adapter<QuaterHold>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuaterHold {
        val item = ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val context = parent.context
        return QuaterHold(item, context, ra)
    }

    override fun getItemCount(): Int {
        return if(listQuaters != null){listQuaters.size }else{0}
    }

    override fun onBindViewHolder(holder: QuaterHold, position: Int) {
        holder.bind(listQuaters!![position])
    }
}