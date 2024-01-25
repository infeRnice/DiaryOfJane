package com.diary.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.diary.R
import com.diary.app.DiaryApp
import com.diary.databinding.MainScreenFragmentBinding
import com.diary.ui.adapters.DiaryAdapter
import com.diary.ui.viewmodels.MainViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject


class MainScreenFragment: Fragment() {

    private var _binding: MainScreenFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var diaryAdapter: DiaryAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as DiaryApp).diaryAppComponent.injectToMainFragment(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


        diaryAdapter = DiaryAdapter()
        binding.recyclerViewTasks.adapter = diaryAdapter
        binding.recyclerViewTasks.layoutManager = LinearLayoutManager(context)

        binding.btnDatePicker.setOnClickListener {
            viewModel.toggleCalendarVisibility()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.calendarVisibility.collect { isVisible ->
                binding.calendarView.visibility = if (isVisible) View.VISIBLE else View.GONE
            }
        }

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val date = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }.time
            viewModel.loadTasksForDate(date)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tasks.collect { tasks ->
                diaryAdapter.setItems(tasks)
            }
        }

        binding.fabAddTask.setOnClickListener {
            findNavController().navigate(R.id.createTaskScreen)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}