package com.datetif.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.datetif.databinding.ItemRecyclerViewBinding
import com.datetif.model.Matter
import java.util.Locale

class ListMatterAdapter(var listMatter: MutableList<Matter>?, var year: String, var ra: String) : RecyclerView.Adapter<MatterHold>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatterHold {
        val item = ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val context = parent.context
        return MatterHold(item, context, year, ra)
    }

    override fun onBindViewHolder(holder: MatterHold, position: Int) {
        holder.bind(listMatter!![position])
    }

    override fun getItemCount(): Int {
        return listMatter?.size ?: 0
    }

    fun setFilteredList(matters: MutableList<Matter>) {
        this.listMatter = matters
        notifyDataSetChanged()
    }
}

