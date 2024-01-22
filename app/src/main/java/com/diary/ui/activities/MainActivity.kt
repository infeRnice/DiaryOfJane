package com.diary.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.diary.R
import com.diary.app.DiaryApp
import com.diary.di.factory.ViewModelFactory
import com.diary.ui.fragments.MainScreenFragment
import com.diary.ui.viewmodels.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as DiaryApp).diaryAppComponent.injectToActivity(this)

        mainViewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainScreenFragment())
                .commit()
        }
    }
}

