package com.diary.ui.fragments

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.diary.app.DiaryApp
import com.diary.ui.compose_components.TaskCreationScreen
import com.diary.ui.theme.DiaryOfJaneTheme
import com.diary.ui.viewmodels.MainViewModel
import javax.inject.Inject

class TaskCreationFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity().application as DiaryApp).diaryAppComponent.injectToTaskCreationFragment(
            this
        )
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        // setContent для Jetpack Compose
        return ComposeView(requireContext()).apply {
            setContent {
                DiaryOfJaneTheme{
                    TaskCreationScreen(onTaskCreated = { task ->
                        viewModel.createTask(task)
                        findNavController().popBackStack() // Возвращение назад после создания задачи
                    })
                }
            }
        }
    }
}
