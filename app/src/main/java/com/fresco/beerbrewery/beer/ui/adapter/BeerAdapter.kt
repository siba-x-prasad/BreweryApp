package com.fresco.beerbrewery.beer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fresco.beerbrewery.BR
import com.fresco.beerbrewery.beer.listeners.BeerClickListener
import com.fresco.beerbrewery.beer.model.BeerItem
import com.fresco.beerbrewery.databinding.ItemviewBeerBinding

class BeerAdapter(private val beerClickListener: BeerClickListener) :
    RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {

    private var beerList: List<BeerItem> = ArrayList()

    class BeerViewHolder(private val binding: ItemviewBeerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(obj: BeerItem, beerClickListener: BeerClickListener) {
            binding.setVariable(BR.beer, obj)
            binding.setVariable(BR.beerClickListener, beerClickListener)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val beerBinding: ItemviewBeerBinding =
            ItemviewBeerBinding.inflate(inflater, parent, false)
        return BeerViewHolder(beerBinding)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item, beerClickListener)
    }

    override fun getItemCount() = differ.currentList.size

    private val beerDiffCallback: DiffUtil.ItemCallback<BeerItem> =
        object : DiffUtil.ItemCallback<BeerItem>() {
            override fun areItemsTheSame(
                @NonNull oldBeer: BeerItem,
                @NonNull newBeer: BeerItem
            ): Boolean {
                return oldBeer.id == newBeer.id
            }

            override fun areContentsTheSame(
                @NonNull oldBeer: BeerItem,
                @NonNull newBeer: BeerItem
            ): Boolean {
                return oldBeer.id == newBeer.id
            }
        }

    private val differ: AsyncListDiffer<BeerItem> =
        AsyncListDiffer(this, beerDiffCallback)

    fun updateItems(beers: List<BeerItem>?) {
        if (beers?.isNotEmpty() == true) {
            beerList = beers
            differ.submitList(beers)
        }
    }
}