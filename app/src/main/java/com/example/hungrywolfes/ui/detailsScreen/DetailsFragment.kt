package com.example.hungrywolfes.ui.detailsScreen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hungrywolfes.DetailBinding
import com.example.hungrywolfes.R
import com.example.hungrywolfes.ui.DetailsAdapter
import com.example.hungrywolfes.ui.detailsScreen.DetailsViewModel

class DetailsFragment : Fragment() {
    private lateinit var binding: DetailBinding
    private val viewModel: DetailsViewModel by viewModels()
    val args: DetailsFragmentArgs by navArgs()
    private val detailsAdapter = DetailsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@DetailsFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgumentsFromSearchScreen()
        getArgumentsFromHomeScreen()
        setupRecyclerView()
        setupObservers()
    }

    private fun getArgumentsFromHomeScreen() {
        var idMealHome=args.idMeal
        arguments?.let { idMealHome = it.getString("idMeal").toString() }
        viewModel.getDetailsMeal(idMealHome.toInt())
    }

    private fun getArgumentsFromSearchScreen() {
        var idMealSearch=args.idMeal
        arguments?.let { idMealSearch = it.getString("idMeal").toString() }
        viewModel.getDetailsMeal(idMealSearch.toInt())
    }

    private fun setupRecyclerView() {
        val divider = DividerItemDecoration(requireContext(), RecyclerView.HORIZONTAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider10)?.let {
            divider.setDrawable(it)
        }
        binding.recyclerViewTags.adapter = detailsAdapter
        binding.recyclerViewTags.addItemDecoration(divider)
    }

    private fun setupObservers() {
        viewModel.onBackButton.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
        viewModel.detailsMeal.observe(viewLifecycleOwner) {
            Glide.with(binding.mealImage)
                .load(it.strMealThumb)
                .placeholder(R.drawable.loading_img)
                .error(R.drawable.ic_connection_error)
                .into(binding.mealImage)
        }
        viewModel.stringTags.observe(viewLifecycleOwner) {
            detailsAdapter.setDataTags(it)
        }
        viewModel.addItemToFavorite.observe(viewLifecycleOwner) {
            binding.favoriteButton.setBackgroundResource(R.drawable.selector_add_to_favorite)
        }
    }
}