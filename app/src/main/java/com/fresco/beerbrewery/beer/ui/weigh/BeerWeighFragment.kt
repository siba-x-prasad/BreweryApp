package com.fresco.beerbrewery.beer.ui.weigh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fresco.beerbrewery.R
import com.fresco.beerbrewery.beer.model.Hop
import com.fresco.beerbrewery.beer.ui.BeerActivity
import com.fresco.beerbrewery.common.util.Constants
import com.fresco.beerbrewery.common.views.SeekArc
import com.fresco.beerbrewery.databinding.BeerWeighFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerWeighFragment : Fragment() {

    private val viewModel by viewModels<BeerWeighViewModel>()
    private lateinit var binding: BeerWeighFragmentBinding

    private var hops: Hop? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.beer_weigh_fragment, container, false
        )
        setUpUI()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.seekBarWeigh.setOnSeekArcChangeListener(object : SeekArc.OnSeekArcChangeListener {
            override fun onProgressChanged(seekArc: SeekArc?, progress: Int, fromUser: Boolean) {
                viewModel.weighUiState.seekBarValue.set(progress)
            }

            override fun onStartTrackingTouch(seekArc: SeekArc?) {}
            override fun onStopTrackingTouch(seekArc: SeekArc?) {}
        })
    }

    private fun setUpUI() {
        (activity as BeerActivity).supportActionBar?.title = getString(R.string.beer_weigh)
        hops = arguments?.getParcelable(Constants.KEY_MALT_HOPS)
        viewModel.weighDetails = hops
        binding.data = hops
        binding.viewModel = viewModel
        binding.buttonDone.setOnClickListener {
            hops?.amount?.value = viewModel.weighUiState.seekBarValue.get()?.toDouble()
            hops?.isWeighed = true
            viewModel.updateMaltsOrHop(hops)
            val action =
                BeerWeighFragmentDirections.actionBeerWeighFragmentToBeerDetailsFragment()
            findNavController().navigate(action)
        }
    }
}