package com.test.currency.presentation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.test.currency.common.ActivityState
import com.test.currency.databinding.FragmentCurrenciesBinding
import com.test.currency.domain.models.CurrencyModel
import com.test.currency.presentation.productsList.CurrenciesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CurrenciesFragment : BottomSheetDialogFragment() {
    val viewModel by activityViewModels<CurrenciesViewModel>()
    lateinit var binding:FragmentCurrenciesBinding
    var isFromCurrency=true
    @Inject
    lateinit var adapter_:CurrencyAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         (!::binding.isInitialized)
        binding = FragmentCurrenciesBinding.inflate(inflater,container, false)

        isFromCurrency=arguments?.getBoolean("isFromCurrency")?:true
        viewModel.state.observe(this) {
            when (it) {
                is ActivityState.Success<List<CurrencyModel>?> -> {
                    // binding.loader.bar.visibility = GONE
                    setListOfCurrencies(it.data)
                }
                is ActivityState.Error -> {
                    //  binding.loader.bar.visibility = GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
                is ActivityState.Loading ->{
                    Toast.makeText(activity, "loading .....", Toast.LENGTH_LONG).show()

                }
             }
        }
        return binding.root
    }

    private fun setListOfCurrencies(data: List<CurrencyModel>?) {
        adapter_.list=(data ?: mutableListOf())

        binding.list.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = adapter_
        }
        adapter_.action = object : CurrencyAdapter.CurrencyAction {
            override fun onClick(currency: String) {
                if (isFromCurrency)
                    viewModel.fromCurrency=currency
                else
                    viewModel.toCurrency=currency

                dismiss()
            }
        }
    }
}