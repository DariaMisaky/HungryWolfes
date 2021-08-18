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

    private lateinit var binding: HomeBinding
    private val viewModel: OverviewViewModel by viewModels()

    private val categoryAdapter = CategoryAdapter { item -> viewModel.getCategoryMeals(item) }
    private val imagesAdapter = ImagesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        setupRecyclerViews()
        setupObservers()
    }

    private fun setupRecyclerViews() {
        val divider = DividerItemDecoration(requireContext(), RecyclerView.HORIZONTAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)?.let {
            divider.setDrawable(it)
        }
        binding.recyclerViewMeals.addItemDecoration(divider)
        binding.recyclerViewPhotos.addItemDecoration(divider)

        binding.recyclerViewMeals.adapter = categoryAdapter
        binding.recyclerViewPhotos.adapter = imagesAdapter
    }

    private fun setupObservers() {
        viewModel.mealCategory.observe(viewLifecycleOwner) {
            categoryAdapter.setData(it.meals)
        }
        viewModel.mealImages.observe(viewLifecycleOwner) {
            imagesAdapter.setDataImages(it.meals)
            binding.recyclerViewPhotos.scrollToPosition(0)
        }
        viewModel.goToSearch.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_homeScreen_to_fragment_search_screen)
        }
    }
}
