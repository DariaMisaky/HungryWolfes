package com.example.hungrywolfes.ui.internetScreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hungrywolfes.databinding.FragmentInternetBinding

private const val TAG = "InternetFragment"
class InternetFragment : Fragment() {
    private lateinit var binding: FragmentInternetBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInternetBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonTryAgain.setOnClickListener {
            Log.d(TAG, "onViewCreated: ")
            Toast.makeText(context,"Trying to connect",Toast.LENGTH_SHORT).show()

        }
    }
}