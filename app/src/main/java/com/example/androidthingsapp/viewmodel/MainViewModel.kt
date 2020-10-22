package com.example.androidthingsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidthingsapp.repo.TeamRepo

class MainViewModel(private val teamRepo: TeamRepo) : ViewModel() {

    class Factory(private val teamRepo: TeamRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MainViewModel(teamRepo) as T
    }

    val teams = teamRepo.getTeams()
}