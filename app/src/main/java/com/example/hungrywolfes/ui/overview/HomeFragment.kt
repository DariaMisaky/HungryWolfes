package com.example.hungrywolfes.ui.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfes.R
import com.example.hungrywolfes.databinding.HomeBinding
import com.example.hungrywolfes.ui.CategoryAdapter
import com.example.hungrywolfes.ui.ImagesAdapter


class HomeFragment : Fragment() {

    private var binding: HomeBinding? = null
    private val viewModel: OverviewViewModel by viewModels()

    private val categoryAdapter = CategoryAdapter { item -> viewModel.getCategoryMeals(item) }
    private val imagesAdapter = ImagesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = HomeBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@HomeFragment.viewLifecycleOwner
            this@HomeFragment.binding = binding
            recyclerViewMeals.adapter = categoryAdapter
            recyclerViewPhotos.adapter = imagesAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel = viewModel
        setupDivider()
        setupObservers()
    }

    private fun setupDivider() {
        val divider = DividerItemDecoration(requireContext(), RecyclerView.HORIZONTAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)?.let {
            divider.setDrawable(it)
        }
        binding?.recyclerViewMeals?.addItemDecoration(divider)
        binding?.recyclerViewPhotos?.addItemDecoration(divider)
    }

    private fun setupObservers() {
        viewModel.mealCategory.observe(viewLifecycleOwner) {
            categoryAdapter.setData(it.meals)
        }
        viewModel.mealImages.observe(viewLifecycleOwner) {
            imagesAdapter.setDataImages(it.meals)
            binding?.recyclerViewPhotos?.scrollToPosition(0)
        }

    }
}