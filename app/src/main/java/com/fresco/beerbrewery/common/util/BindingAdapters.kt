package com.fresco.beerbrewery.common.util


import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fresco.beerbrewery.R
import com.fresco.beerbrewery.beer.model.BeerItem
import com.fresco.beerbrewery.beer.model.Method
import com.fresco.beerbrewery.beer.ui.adapter.BeerAdapter
import com.fresco.beerbrewery.beer.ui.adapter.MaltHopAdapter
import com.squareup.picasso.Picasso


object BindingAdapters {


    @JvmStatic
    @BindingAdapter("malthopdapter")
    fun setMaltHopAdapter(view: RecyclerView, adapter: MaltHopAdapter) {
        view.adapter = adapter
    }


    @JvmStatic
    @BindingAdapter("adapter")
    fun setAdapter(view: RecyclerView, adapter: BeerAdapter) {
        view.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("methodText")
    fun setMethodText(apptextView: AppCompatTextView, method: Method) {
        val fermentation =
            "Fermentation ${method.fermentation?.temp?.value} ${method.fermentation?.temp?.unit} \n"
        var tempMethods = ""
        method.mash_temp?.forEach {
            tempMethods += "${it.temp?.value} ${it.temp?.unit} duration : ${it.duration} \n"
        }
        apptextView.text = "$fermentation Mash Temperature : $tempMethods"
    }

    @JvmStatic
    @BindingAdapter(value = ["updateBeers"])
    fun RecyclerView.updateRecyclerViewAdapter(
        beerList: MutableList<BeerItem>?
    ) {
        val adapter = this.adapter as BeerAdapter
        if (beerList != null) {
            adapter.beerList = beerList
        }
    }

    @JvmStatic
    @BindingAdapter("beerImage")
    fun loadImage(imageview: AppCompatImageView, imageUrl: String?) {
        Picasso.with(imageview.context)
            .load(imageUrl)
            .error(R.mipmap.ic_launcher_round)
            .into(imageview)
    }

    @JvmStatic
    @BindingAdapter("show")
    fun showOrHideProgress(view: View, showHideStatus: Boolean) {
        view.visibility = if (showHideStatus) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("errorVisibleGone")
    fun showOrHideError(view: View, error: String) {
        view.visibility = if (error.isEmpty()) View.GONE else View.VISIBLE
    }

    @JvmStatic
    @BindingAdapter("errorMessage")
    fun showErrorMessage(view: AppCompatTextView, message: String) {
        message.let {
            view.text = message
        }
    }

    @set:BindingAdapter("invisible")
    var View.invisible
        get() = visibility == View.INVISIBLE
        set(value) {
            visibility = if (value) View.INVISIBLE else View.VISIBLE
        }

    @set:BindingAdapter("gone")
    var View.gone
        get() = visibility == View.GONE
        set(value) {
            visibility = if (value) View.GONE else View.VISIBLE
        }

}