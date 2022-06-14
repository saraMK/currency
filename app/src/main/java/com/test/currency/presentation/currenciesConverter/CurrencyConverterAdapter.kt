package com.test.currency.presentation.currenciesConverter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.currency.R
import com.test.currency.common.Extentions.getImageLink
import com.test.currency.databinding.ConverterRowViewBinding
import com.test.currency.databinding.CurrencyRowViewBinding
import com.test.currency.domain.models.ConverterModel
import com.test.currency.domain.models.CurrencyModel
import com.test.currency.domain.models.ValueModel
import javax.inject.Inject

class CurrencyConverterAdapter @Inject constructor():RecyclerView.Adapter<CurrencyConverterAdapter.MyViewHolder>() {

    var list:List<ValueModel> = listOf()
    var amount =1
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val binding= ConverterRowViewBinding.
       inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model =list.get(position)
        holder.binding.dateTxt.text=model.date
        holder.binding.amount.text="${model.value*amount}"


    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(val binding :ConverterRowViewBinding):RecyclerView.ViewHolder(binding.root)


}