package com.fresco.beerbrewery.beer.ui.beerList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fresco.beerbrewery.R
import com.fresco.beerbrewery.beer.listeners.BeerClickListener
import com.fresco.beerbrewery.beer.model.BeerItem
import com.fresco.beerbrewery.beer.ui.adapter.BeerAdapter
import com.fresco.beerbrewery.beer.ui.weigh.SharedBeerViewModel
import com.fresco.beerbrewery.databinding.BeerListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerListFragment : Fragment(), BeerClickListener {

    private val viewModel by viewModels<BeerListViewModel>()
    private val sharedBeerViewModel: SharedBeerViewModel by viewModels()
    private lateinit var binding: BeerListFragmentBinding
    private var isLoading = false

    private val adapter: BeerAdapter by lazy {
        BeerAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.beer_list_fragment, container, false
        )
        val view: View = binding.root
        binding.viewModel = viewModel
        binding.beerAdapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initScrollListener()
        viewModel.beerData.observe(viewLifecycleOwner) {
            adapter.updateItems(it)
        }
        sharedBeerViewModel.mutableBeerData.observe(viewLifecycleOwner) {
            viewModel.updateBeerItem(it)
        }
    }

    private fun initScrollListener() {
        binding.recyclerViewBeer.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() >= adapter?.itemCount!! - 2) {
                        viewModel.loadBeerData()
                    }
                }
            }
        })
    }

    override fun onBeerClick(beerItem: BeerItem) {
        val action =
            BeerListFragmentDirections.actionBeerListFragmentToBeerDetailsFragment(beerItem)
        findNavController().navigate(action)
    }
}