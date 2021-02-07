package com.avinash.currencyconverter.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avinash.currencyconverter.R
import com.avinash.currencyconverter.commons.Constants.EMPTY_STRING
import com.avinash.currencyconverter.uistate.CurrencyDetails
import kotlinx.android.synthetic.main.currency_list_item.view.country
import kotlinx.android.synthetic.main.currency_list_item.view.currencyAmount

class CurrencyListAdapter(val itemList : List<CurrencyDetails>) : RecyclerView.Adapter<CurrencyItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.currency_list_item, parent, false)
        return CurrencyItemViewHolder(itemView)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: CurrencyItemViewHolder, position: Int) {
        holder.run {
            itemList.getOrNull(position)?.let {
                bind(info = it)
            }
        }
    }
}

class CurrencyItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(info: CurrencyDetails) {
        with(itemView) {
            info.let {
                country.text = it.country
                currencyAmount.text = it.currencyRate?:EMPTY_STRING
            }
        }
    }
}
