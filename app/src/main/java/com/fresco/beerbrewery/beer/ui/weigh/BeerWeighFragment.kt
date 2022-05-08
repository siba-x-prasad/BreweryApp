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
import com.fresco.beerbrewery.common.views.SeekArc
import com.fresco.beerbrewery.databinding.BeerWeighFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerWeighFragment : Fragment() {

    private val viewModel by viewModels<BeerWeighViewModel>()
    private lateinit var binding: BeerWeighFragmentBinding

    var hops: Hop? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.beer_weigh_fragment, container, false
        )
        hops = arguments?.getParcelable<Hop>("maltOrHops")
        viewModel.weighDetails = hops
        val view: View = binding.root
        binding.data = hops
        binding.viewModel = viewModel
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.seekBarWeigh.setOnSeekArcChangeListener(object : SeekArc.OnSeekArcChangeListener {
            override fun onProgressChanged(seekArc: SeekArc?, progress: Int, fromUser: Boolean) {
                viewModel.weighUiState.seekBarValue.set(progress)
            }

            override fun onStartTrackingTouch(seekArc: SeekArc?) {
            }

            override fun onStopTrackingTouch(seekArc: SeekArc?) {
            }
        })

        binding.buttonDone.setOnClickListener {
            val action =
                BeerWeighFragmentDirections.actionBeerWeighFragmentToBeerDetailsFragment()
            findNavController().navigate(action)
        }
    }
}