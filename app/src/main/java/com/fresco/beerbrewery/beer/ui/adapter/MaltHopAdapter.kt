package com.fresco.beerbrewery.beer.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fresco.beerbrewery.BR
import com.fresco.beerbrewery.beer.listeners.IngredientClickListener
import com.fresco.beerbrewery.beer.model.Hop
import com.fresco.beerbrewery.databinding.ItemviewMaltHopsBinding

class MaltHopAdapter(private val ingredientClickListener: IngredientClickListener) :
    RecyclerView.Adapter<MaltHopAdapter.MaltHopsViewHolder>() {

    var maltOrHopsList: List<Hop> = ArrayList()

    class MaltHopsViewHolder(private val binding: ItemviewMaltHopsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(obj: Hop, ingredientClickListener: IngredientClickListener) {
            binding.setVariable(BR.data, obj)
            binding.setVariable(BR.ingredientClickListener, ingredientClickListener)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaltHopsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val maltHopsBinding: ItemviewMaltHopsBinding =
            ItemviewMaltHopsBinding.inflate(inflater, parent, false)
        return MaltHopsViewHolder(maltHopsBinding)
    }

    override fun onBindViewHolder(holder: MaltHopsViewHolder, position: Int) {
        val item = differ.currentList[position]
        item.id = position
        holder.bind(item, ingredientClickListener)
    }

    override fun getItemCount() = differ.currentList.size

    private val maltOrHopsDiffCallback: DiffUtil.ItemCallback<Hop> =
        object : DiffUtil.ItemCallback<Hop>() {
            override fun areItemsTheSame(
                @NonNull oldData: Hop,
                @NonNull newData: Hop
            ): Boolean {
                return oldData.name == newData.name
            }

            override fun areContentsTheSame(
                @NonNull oldData: Hop,
                @NonNull newData: Hop
            ): Boolean {
                return oldData.name == newData.name
            }
        }

    private val differ: AsyncListDiffer<Hop> =
        AsyncListDiffer(this, maltOrHopsDiffCallback)


    fun updateMaltOrHops(maltOrHops: List<Hop>?) {
        if (maltOrHops?.isNotEmpty() == true) {
            maltOrHopsList = maltOrHops
            differ.submitList(maltOrHops)
        }
    }
}