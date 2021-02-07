package com.avinash.currencyconverter.presentation.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.avinash.currencyconverter.databinding.ActivityMainBinding
import com.avinash.currencyconverter.presentation.CurrencyListAdapter
import com.avinash.currencyconverter.presentation.vm.CurrencyConverterViewModel
import com.avinash.currencyconverter.uistate.CurrencyUIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CurrencyConverterActivity :AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    private val viewModel: CurrencyConverterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConvert.setOnClickListener {
            viewModel.convert(
                binding.etFrom.text.toString(),
                binding.spFromCurrency.selectedItem.toString()
            )
        }

        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { event ->
                when(event) {
                    is CurrencyUIState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.currencyRecyclerView.visibility = View.GONE
                        binding.errorTv.visibility = View.GONE
                    }
                    is CurrencyUIState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.errorTv.visibility = View.GONE
                        setAdapter(event)
                    } is CurrencyUIState.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorTv.visibility = View.VISIBLE
                    binding.errorTv.text = event.errorMessage
                    binding.currencyRecyclerView.visibility = View.GONE
                }
                    else -> Unit
                }
            }
        }
    }

    private fun setAdapter(event: CurrencyUIState.Success) {
        binding.currencyRecyclerView.visibility = View.VISIBLE
        binding.currencyRecyclerView.adapter =
            CurrencyListAdapter(itemList = event.response)
    }
}