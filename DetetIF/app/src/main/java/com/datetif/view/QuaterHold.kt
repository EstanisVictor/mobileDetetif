package com.datetif.view

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.datetif.MatterActivity
import com.datetif.databinding.ItemRecyclerViewBinding
import com.datetif.model.QuaterEntity

class QuaterHold(var binding: ItemRecyclerViewBinding, val context: Context, var ra: String) : RecyclerView.ViewHolder(binding.root) {

        fun bind(quater: QuaterEntity){
                binding.firstTxt.text = quater.classe + " - " + quater.year
                binding.secondTxt.text = quater.name

                binding.acsessBtn.setOnClickListener {
                        val transitionMatterActivity:Intent = Intent(context, MatterActivity::class.java)
                        transitionMatterActivity.putExtra("class", quater.name)
                        transitionMatterActivity.putExtra("year", quater.year)
                        transitionMatterActivity.putExtra("ra", ra)
                        context.startActivity(transitionMatterActivity)
                }
        }
}