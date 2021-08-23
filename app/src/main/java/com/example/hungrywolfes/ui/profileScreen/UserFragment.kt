package com.example.hungrywolfes.ui.profileScreen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hungrywolfes.R
import com.example.hungrywolfes.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.onBackButton.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
        viewModel.onFavoriteButton.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_userFragment_to_favoriteFragment)
        }
        viewModel.onTermsAndConditions.observe(viewLifecycleOwner) {
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wolfpack-digital.com/privacy"))
            startActivity(intent)
        }
    }
}