package com.example.hungrywolfes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.hungrywolfes.databinding.FragmentHomeScreenBinding
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfes.overview.OverviewViewModel


class HomeScreenFragment : Fragment() {
    private var binding: FragmentHomeScreenBinding? = null
    private val viewModel:OverviewViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding?.homeScreen = this
        binding?.viewModel=viewModel
        binding?.recyclerViewMeals?.adapter=CategoryAdapter()
        binding?.recyclerViewPhotos?.adapter=ImagesAdapter()
    }

    fun search() {
        findNavController().navigate(R.id.action_homeScreen_to_fragment_search_screen)
    }

}