package com.rsierra.project.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rsierra.project.R
import com.rsierra.project.databinding.FragmentAboutPageBinding

class AboutPageFragment : Fragment() {
    lateinit var binding: FragmentAboutPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutPageBinding.inflate(inflater, container, false);
        binding.fabBack.setOnClickListener {
            findNavController().navigate(R.id.action_aboutPageFragment_to_todoListFragment)
        }
        return binding.root;
    }
}