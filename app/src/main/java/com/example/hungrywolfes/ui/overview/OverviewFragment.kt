package com.example.hungrywolfes.ui.overview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfes.OverviewBinding
import com.example.hungrywolfes.R
import com.example.hungrywolfes.network.ListMealCategory
import com.example.hungrywolfes.ui.CategoryAdapter
import com.example.hungrywolfes.ui.ImagesAdapter


class OverviewFragment : Fragment() {

    private var binding: OverviewBinding? = null
    private val viewModel: OverviewViewModel by activityViewModels()

    private val categoryAdapter = CategoryAdapter { item -> viewModel.mealSelected(item) }
    private val imagesAdapter = ImagesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = OverviewBinding.inflate(inflater, container, false)
        binding.apply {
            fragment = this@OverviewFragment
            viewModel = this@OverviewFragment.viewModel
            lifecycleOwner = this@OverviewFragment.viewLifecycleOwner
            this@OverviewFragment.binding = binding
            recyclerViewMeals.adapter = categoryAdapter
            recyclerViewPhotos.adapter = imagesAdapter
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDivider()
        setupObservers()
    }

    private fun setupDivider() {
        val divider = DividerItemDecoration(requireContext(), RecyclerView.HORIZONTAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider).let {
            if (it != null) {
                divider.setDrawable(it)
            }
        }
        binding?.recyclerViewMeals?.addItemDecoration(divider)
        binding?.recyclerViewPhotos?.addItemDecoration(divider)
    }

    private fun setupObservers() {
        viewModel.mealCategory.observe(viewLifecycleOwner) {
            categoryAdapter.setData(it.meals)
        }
        Log.d("data", "OBSERVEEEEE")
        viewModel.mealImages.observe(viewLifecycleOwner) {
            imagesAdapter.setDataImages(it.meals)
            binding?.recyclerViewPhotos?.scrollToPosition(0)

        }

    }

    fun searchClicked() {
        findNavController().navigate(R.id.action_homeScreen_to_fragment_search_screen)
    }

}