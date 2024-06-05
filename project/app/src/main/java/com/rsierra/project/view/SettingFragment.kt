package com.rsierra.project.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.rsierra.project.R
import com.rsierra.project.databinding.FragmentSettingBinding
import com.rsierra.project.databinding.FragmentTodoListBinding
import com.rsierra.project.viewModel.SettingViewModel

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding
    private val viewModel: SettingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        binding.fabBack.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_todoListFragment)
        }
        viewModel.canEditTask.observe(viewLifecycleOwner, Observer { canEdit ->
            binding.switchEditarTarea.isChecked = canEdit
        })

        viewModel.canCompleteTask.observe(viewLifecycleOwner, Observer { canComplete ->
            binding.switchCompletarTarea.isChecked = canComplete
        })

        viewModel.canViewDetails.observe(viewLifecycleOwner, Observer { canView ->
            binding.switchVerDetalle.isChecked = canView
        })

        // Configura los listeners para actualizar el ViewModel
        binding.switchEditarTarea.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setCanEditTask(isChecked)
        }

        binding.switchCompletarTarea.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setCanCompleteTask(isChecked)
        }

        binding.switchVerDetalle.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setCanViewDetails(isChecked)
        }
        return binding.root
    }
}