package com.rsierra.project.view

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rsierra.project.R
import com.rsierra.project.database.entity.Task
import com.rsierra.project.databinding.FragmentDetailBinding
import com.rsierra.project.databinding.FragmentTodoListBinding
import com.rsierra.project.viewModel.DetailViewModel

class DetailFragment : Fragment() {

    private var taskId: Int? = null
    private lateinit var taskTitle: TextView
    private lateinit var taskDescription: TextView
    private lateinit var closeTaskButton: Button
    private lateinit var binding: FragmentDetailBinding
    private lateinit var statusView: View

    private lateinit var taskSelected: Task
    private lateinit var viewModel: DetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.let {
            taskId = it.getInt("task_id")
        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        taskTitle = binding.taskTitle
        taskDescription = binding.taskDescription
        closeTaskButton = binding.closeTaskButton
        statusView = binding.statusView

        taskId?.let { id ->
            viewModel.getTaskById(id)
        }

        viewModel.task.observe(viewLifecycleOwner, Observer { task ->
            task?.let {
                taskTitle.text = it.title
                taskDescription.text = it.description
                val colorDrawable = getPriorityColor(requireContext(), it.priority)
                statusView.background = colorDrawable
                taskSelected = it
            }
        })

        binding.fabBack.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_todoListFragment)
        }
        binding.closeTaskButton.setOnClickListener {
            viewModel.deleteTask(taskSelected)
            findNavController().navigate(R.id.action_detailFragment_to_todoListFragment)
        }
        return binding.root
    }

    private fun getPriorityColor(context: Context, priority: Int): Drawable? {
        return when (priority) {
            1 -> ContextCompat.getDrawable(context, R.drawable.circle_status_green)
            2 -> ContextCompat.getDrawable(context, R.drawable.circle_status_yellow)
            else -> ContextCompat.getDrawable(context, R.drawable.circle_status_red)
        }
    }
}