package com.example.hungrywolfes.ui.FavoritesScreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfes.R
import com.example.hungrywolfes.databinding.FragmentFavoriteBinding
import com.example.hungrywolfes.ui.FavoriteAdapter
import com.example.hungrywolfes.ui.detailsScreen.DetailsViewModel

private const val TAG = "FavoriteFragment"

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()
   // private val viewModel:ActivityViewModel by activityViewModels()
    private val favoriteAdapter = FavoriteAdapter()

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
        Log.d(TAG, "onViewCreated: ")
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
//        viewModel.idImgLiveData.observe(viewLifecycleOwner) {
//            favoriteAdapter.setDataImages(viewModel.favoriteMealList)
//        }
        viewModel.favoriteMealList.observe(viewLifecycleOwner){
            Log.d(TAG, "setUpObservers: am observat schimbarea")
        }
    }
}