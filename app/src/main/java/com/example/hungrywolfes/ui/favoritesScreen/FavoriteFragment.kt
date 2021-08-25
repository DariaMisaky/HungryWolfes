package com.example.hungrywolfes.ui.favoritesScreen

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
import com.example.hungrywolfes.databinding.FragmentFavoriteBinding
import com.example.hungrywolfes.ui.FavoriteAdapter

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()
    private val favoriteAdapter by lazy {
        FavoriteAdapter(viewModel::onRemoveItem) { item ->
            navigateToFragment(
                item
            )
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setUpObservers()
    }

    private fun setupRecyclerView() {
        val divider = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider10)?.let {
            divider.setDrawable(it)
        }
        binding.recyclerViewFavorites.adapter = favoriteAdapter
        binding.recyclerViewFavorites.addItemDecoration(divider)
    }

    private fun setUpObservers() {
        viewModel.favoriteMealList.observe(viewLifecycleOwner) {
            viewModel.favoriteMealList.value?.let { it1 -> favoriteAdapter.setDataImages(it1) }
        }
    }

    private fun navigateToFragment(id: String) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailsFragment(id)
        findNavController().navigate(action)
    }
}