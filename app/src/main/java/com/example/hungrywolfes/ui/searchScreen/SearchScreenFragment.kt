package com.example.hungrywolfes.ui.searchScreen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfes.databinding.FragmentSearchScreenBinding
import com.example.hungrywolfes.R
import com.example.hungrywolfes.ui.adapters.SearchedImagesAdapter
import com.example.hungrywolfes.ui.overview.HomeFragmentDirections

private const val TAG = "SearchScreenFragment"

class SearchScreenFragment : Fragment() {

    private lateinit var binding: FragmentSearchScreenBinding
    private val viewModel: SearchViewModel by viewModels()
    private val searchedImagesAdapter = SearchedImagesAdapter{item -> navigateToDetailsFragment(item)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        setupRecyclerViews()
        searchImages()
        setupObservers()
    }
    fun navigateToDetailsFragment(Mealid:String){
        val action= SearchScreenFragmentDirections.actionFragmentSearchScreenToDetailsFragment(Mealid)
        findNavController().navigate(action)
    }
    private fun searchImages() {
        binding.searchMeal.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                hideKeyboard()
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null) {
                    viewModel.getSearchedMeal(p0)
                }
                return false
            }
        })
    }

    override fun onResume() {
        super.onResume()
        showKeyboard()
    }

    private fun setupRecyclerViews() {
        val verticalDivider = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val horizontalDivider = DividerItemDecoration(requireContext(), RecyclerView.HORIZONTAL)

        setDividerDrawable(verticalDivider)
        setDividerDrawable(horizontalDivider)

        binding.recyclerViewSearched.adapter = searchedImagesAdapter
        binding.recyclerViewSearched.addItemDecoration(horizontalDivider)
        binding.recyclerViewSearched.addItemDecoration(verticalDivider)
    }

    private fun setDividerDrawable(divider: DividerItemDecoration) {
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)?.let {
            divider.setDrawable(it)
        }
    }

    private fun getNumberOfResults(results: Int): String {
        return resources.getQuantityString(R.plurals.results, results, results)
    }

    private fun setupObservers() {
        viewModel.searchMeal.observe(viewLifecycleOwner) {
            searchedImagesAdapter.setDataImages(it.meals)
            binding.results.text = getNumberOfResults(it.meals.size)
        }
        viewModel.onBackButton.observe(viewLifecycleOwner) {
            hideKeyboard()
            findNavController().popBackStack()
        }
    }

    private fun showKeyboard() {
        binding.searchMeal.onActionViewExpanded()
    }

    private fun hideKeyboard() {
        binding.searchMeal.clearFocus()
    }
}