package com.test.currency.presentation.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.currency.R
import com.test.currency.common.Extentions.getImageLink
import com.test.currency.databinding.CurrencyRowViewBinding
import com.test.currency.domain.models.CurrencyModel
import javax.inject.Inject

class CurrencyAdapter @Inject constructor():RecyclerView.Adapter<CurrencyAdapter.MyViewHolder>() {

    var list:List<CurrencyModel> = listOf()
    var action:CurrencyAction?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val binding= CurrencyRowViewBinding.
       inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model =list.get(position)
        holder.binding.txt.text=model.currencyName
        val img =model.countryId.getImageLink()
        if (!img.isNullOrEmpty()) {
            holder.binding.image.load(img){
                placeholder(R.drawable.ic_flag)
                error(R.drawable.ic_flag)
                build()
            }
        }
        holder.itemView.setOnClickListener {
            action?.onClick(model.currencyId)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(val binding :CurrencyRowViewBinding):RecyclerView.ViewHolder(binding.root)

    interface CurrencyAction{
        fun onClick(currency:String)
    }
}