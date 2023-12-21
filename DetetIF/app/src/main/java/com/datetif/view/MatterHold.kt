package com.datetif.view

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.datetif.AssessmentActivity
import com.datetif.databinding.ItemRecyclerViewBinding
import com.datetif.model.Matter

class MatterHold(var binding: ItemRecyclerViewBinding, val context: Context, var year: String, var ra: String) : RecyclerView.ViewHolder(binding.root){
    fun bind(matter: Matter){
        binding.firstTxt.text = matter.disc_code + " - " + year
        binding.secondTxt.text = matter.name

        binding.acsessBtn.setOnClickListener {
            val transitionMatterActivity:Intent = Intent(context, AssessmentActivity::class.java)
            transitionMatterActivity.putExtra("disc", matter.name)
            transitionMatterActivity.putExtra("ra", ra)
            context.startActivity(transitionMatterActivity)
        }
    }
}